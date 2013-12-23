package fr.utt.isi.lo02.unoGame.view.gui.card;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;
import fr.utt.isi.lo02.unoGame.view.gui.tween.GuiCardViewAccessor;

public class GuiRibbonView extends Group {

    private PlayerHandModel playerHandModel;
    private float customScale;
    private GuiCardView uppedCard;
    private GuiCardView selectedCard;
    private TweenManager tweenManager;

    public GuiRibbonView (PlayerHandModel playerHandModel) {
        this.playerHandModel = playerHandModel;
    }

    public void setTweenManager (TweenManager tween) {
        this.tweenManager = tween;
    }

    public boolean isSelected (GuiCardView cardView) {
        return cardView.equals(this.selectedCard);
    }

    public boolean hasSelectedCard () {
        return this.selectedCard != null;
    }

    public void setSelected (GuiCardView cardView) {
        this.selectedCard = cardView;
        this.selectedCard.setHighlited(true);
    }

    public void setCustomScale (float scale) {
        this.customScale = scale;
    }

    public void resetCardUpped () {
        this.tweenManager.killTarget(this.uppedCard);
        Tween.to(this.uppedCard, GuiCardViewAccessor.POSITION_Y, .2f).target(0).start(this.tweenManager);
        this.uppedCard = null;
    }

    public void resetCardSelected () {
        this.selectedCard.setHighlited(false);
        this.selectedCard = null;
    }

    public boolean setCardUpped(GuiCardView cardView) {
        if ( this.uppedCard == null ) {
            this.uppedCard = cardView;
            this.tweenManager.killTarget(this.uppedCard);
            Tween.to(this.uppedCard, GuiCardViewAccessor.POSITION_Y, .2f).target(40).start(this.tweenManager);
            return true;
        }
        return false;
    }

    public GuiCardView getUppedCard() {
        return this.uppedCard;
    }

    public void build () {
        int cardNumber = this.playerHandModel.getCards().size();
        float spaceBetweenCards = (((cardNumber * GuiCardView.NATIVE_CARD_WIDTH * this.customScale) - super.getWidth()) / cardNumber);
        int a = 0;
        for ( CardModel cardModel : this.playerHandModel.getCards() ) {
            GuiCardView cardView = new GuiCardView(cardModel);
            Vector2 cardPosition = new Vector2();
            cardPosition.x = a * spaceBetweenCards ;
            cardView.setPosition(cardPosition.x, cardPosition.y);
            cardView.resize(this.customScale);
            cardView.setTouchable(Touchable.enabled);
            cardView.setTweenManager(this.tweenManager);
            cardView.build();

            this.addActor(cardView);
            a++;
        }
    }
    
}