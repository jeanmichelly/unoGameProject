package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiBoardScreenView;

public class HumanPlayerController extends ClickListener {

    HumanPlayerModel humanPlayerModel;

    public HumanPlayerController(HumanPlayerModel humanPlayerModel) {
        this.humanPlayerModel = humanPlayerModel;
    }
    
    public void clicked (InputEvent event, float x, float y) {
        Actor actor = event.getListenerActor();
        switch ( String.valueOf(actor) ) {
            case "NTP" :
                actor.setVisible(false);
                DrawPileModel.getUniqueInstance().setDrawable(true);
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers();
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
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers();
                break; 
            case "BU" :
                GuiBoardScreenView.uno = true;
                BoardModel.getUniqueInstance().getPlayer().setUno(false);
                break;
            case "BAU" :
            try {
                BoardModel.getUniqueInstance().getPlayer().againstUno();
            } catch (InvalidActionPickCardException e) {
                e.printStackTrace();
            }
                break;
        }
    }
    
}
