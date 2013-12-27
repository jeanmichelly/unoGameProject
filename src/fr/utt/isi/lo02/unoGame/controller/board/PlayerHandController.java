package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.deck.GuiRibbonView;

public class PlayerHandController extends InputListener {
        
    private PlayerHandModel playerHandModel;
    private GuiRibbonView cardRibbon;
    
    public PlayerHandController (PlayerHandModel playerHandModel, GuiRibbonView cardRibbon) {
        this.playerHandModel = playerHandModel;
        this.cardRibbon = cardRibbon;
    }

    @Override
    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
        Actor actor = this.cardRibbon.hit(x, y, true);
        if ( actor instanceof GuiCardView ) {
            GuiCardView cardView = (GuiCardView) actor;
            
            if ( this.cardRibbon.getUppedCard() != null && this.cardRibbon.getUppedCard().equals(actor) 
                    && this.cardRibbon.isSelected(cardView) && cardView.isHighlited() ) {
                try {
                    BoardModel.getUniqueInstance().getPlayer().putDownCard(cardView.getZIndex());
                    DrawPileModel.getUniqueInstance().setDrawable(true);
                    BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                    BoardModel.getUniqueInstance().setChanged();
                    BoardModel.getUniqueInstance().notifyObservers();
                } catch (InvalidActionPutDownCardException e) {
                    e.printStackTrace();
                }
            }
            else if ( this.cardRibbon.getUppedCard() != null && this.cardRibbon.getUppedCard().equals(actor) 
                    && !this.cardRibbon.isSelected(cardView) ) {
                this.cardRibbon.setSelected(cardView);
            } else if ( cardRibbon.getUppedCard() != null && this.cardRibbon.getUppedCard().equals(actor) 
                    && this.cardRibbon.isSelected(cardView) ) {
                this.cardRibbon.resetCardSelected();
            } else {
                this.cardRibbon.resetCardUpped();
            }
        }
        return super.touchDown(event, x, y, pointer, button);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public void enter (InputEvent event, float x, float y, int pointer, Actor fromActor) {
        Actor enteredActor = this.cardRibbon.hit(x,y,true);
        if ( enteredActor instanceof GuiCardView ) {
            GuiCardView cardView = (GuiCardView) enteredActor;
            if ( this.cardRibbon.getUppedCard() == null ) {
                this.cardRibbon.setCardUpped(cardView);
            }
        }
    }

    @Override
    public void exit (InputEvent event, float x, float y, int pointer, Actor toActor) {
        if ( this.cardRibbon.getUppedCard() != null && !this.cardRibbon.hasSelectedCard() ) {
            this.cardRibbon.resetCardUpped();
        }
    }
    
}
