package fr.utt.isi.lo02.unoGame.view.gui.deck;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.tween.GuiCardViewAccessor;

public class GuiRibbonView extends Group {

    private PlayerHandModel playerHandModel;
    private float customScale;
    private GuiCardView uppedCard;
    private GuiCardView selectedCard;
    private TweenManager tweenManager;

    public GuiRibbonView (PlayerHandModel playerHandModel) {
        this.playerHandModel = playerHandModel;
        this.setBounds(Gdx.graphics.getWidth()/2 - 200, 0, 400, 180);
        this.setCustomScale(.5f);
    }
    
    public GuiRibbonView (PlayerHandModel playerHandModel, TweenManager tween) {
        this(playerHandModel);
        this.tweenManager = tween;
    }
    
    public void build () {
        int cardNumber = this.playerHandModel.getCards().size();
        float spaceBetweenCards = (((7 * GuiCardView.NATIVE_CARD_WIDTH * this.customScale ) - super.getWidth()) / cardNumber);
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

    public void resetCardUpped () {
        this.tweenManager.killTarget(this.uppedCard);
        Tween.to(this.uppedCard, GuiCardViewAccessor.POSITION_Y, .2f).target(0).start(this.tweenManager);
        this.uppedCard = null;
        if ( this.selectedCard != null ) {
            this.selectedCard.setHighlited(false);
            this.selectedCard = null;
        }
    }

    public void resetCardSelected () {
        this.selectedCard.setHighlited(false);
        this.selectedCard = null;
    }
    
    public boolean isSelected (GuiCardView cardView) {
        return cardView.equals(this.selectedCard);
    }

    public boolean hasSelectedCard () {
        return this.selectedCard != null;
    }
    
    public GuiCardView getUppedCard() {
        return this.uppedCard;
    }
    
    public void setCustomScale (float scale) {
        this.customScale = scale;
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
    
    public void setSelected (GuiCardView cardView) {
        if ( cardView.getModel().isPlayableCard() && !cardView.equals(selectedCard) ) {
            this.selectedCard = cardView;
            this.selectedCard.setHighlited(true);
        }
    }
    
    public void setTweenManager (TweenManager tween) {
        this.tweenManager = tween;
    }
    
}