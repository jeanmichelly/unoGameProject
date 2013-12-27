package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.view.gui.deck.GuiDrawPileView;

public class DrawPileController extends InputListener {
    
    DrawPileModel drawPileModel;
    GuiDrawPileView guiDrawPileView;

    public DrawPileController(DrawPileModel drawPileModel, GuiDrawPileView guiDrawPileView) {
        this.drawPileModel = drawPileModel;
        this.guiDrawPileView = guiDrawPileView;
    }
    
    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        try {
            if ( drawPileModel.isDrawable() ) {
                BoardModel.getUniqueInstance().getPlayer().pickCard();
                guiDrawPileView.setHighlited(false);
                drawPileModel.setDrawable(false);
            }
        } catch (InvalidActionPickCardException e) {
            e.printStackTrace();
        }
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers(); 
        return super.touchDown(event, x, y, pointer, button);    //To change body of overridden methods use File | Settings | File Templates.
    }
    
}
