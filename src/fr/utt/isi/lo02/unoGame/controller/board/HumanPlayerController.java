package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiBoardScreenView;

public class HumanPlayerController extends ClickListener {

    HumanPlayerModel humanPlayerModel;

    public HumanPlayerController(HumanPlayerModel humanPlayerModel) {
        this.humanPlayerModel = humanPlayerModel;
    }
    
    public void clicked (InputEvent event, float x, float y) {
        switch ( String.valueOf(event.getListenerActor()) ) {
            case "NTP" :
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                DrawPileModel.getUniqueInstance().setDrawable(true);
                break;
            case "CC" :
                if ( x<100 && y>100 )
                    this.humanPlayerModel.chooseColor(ColorModel.YELLOW);
                else if ( x>100 && y>100 )
                    this.humanPlayerModel.chooseColor(ColorModel.GREEN);
                else if ( x<100 && y<100 )
                    this.humanPlayerModel.chooseColor(ColorModel.BLUE);
                else
                    this.humanPlayerModel.chooseColor(ColorModel.RED);
                GuiBoardScreenView.colors = false;
                break; 
        }
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers();
    }
    
}