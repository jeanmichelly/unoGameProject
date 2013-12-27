package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiBoardScreenView;

public class PlayerController extends ClickListener {

    PlayerModel playerModel;

    public PlayerController(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
    
    public void clicked (InputEvent event, float x, float y) {
        switch ( String.valueOf(event.getListenerActor()) ) {
            case "NTP" :
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                DrawPileModel.getUniqueInstance().setDrawable(true);
                break;
            case "CC" :
                if ( x<100 && y>100 )
                    playerModel.chooseColor(ColorModel.YELLOW);
                else if ( x>100 && y>100 )
                    playerModel.chooseColor(ColorModel.GREEN);
                else if ( x<100 && y<100 )
                    playerModel.chooseColor(ColorModel.BLUE);
                else
                    playerModel.chooseColor(ColorModel.RED);
                GuiBoardScreenView.colors = false;
                break; 
        }
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers();
    }
    
}
