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

    public GuiRibbonView(PlayerHandModel playerHandModel) {
        this.playerHandModel = playerHandModel;
    }

    public void setTweenManager(TweenManager tween){
        tweenManager = tween;
    }

    public boolean isSelected(GuiCardView cardView){
        return cardView.equals(this.selectedCard);
    }

    public boolean hasSelectedCard(){
        return selectedCard != null;
    }

    public void setSelected(GuiCardView cardView){
        this.selectedCard = cardView;
        this.selectedCard.setHighlited(true);
    }

    public void setCustomScale(float scale){
        this.customScale = scale;
    }

    public void resetCardUpped(){
        tweenManager.killTarget(this.uppedCard);
        Tween.to(this.uppedCard, GuiCardViewAccessor.POSITION_Y, .2f).target(0).start(tweenManager);
        this.uppedCard = null;
    }

    public void resetCardSelected(){
        this.selectedCard.setHighlited(false);
        this.selectedCard = null;
    }

    public boolean setCardUpped(GuiCardView cardView){
        if(this.uppedCard == null){
            this.uppedCard = cardView;
            tweenManager.killTarget(this.uppedCard);
            Tween.to(this.uppedCard, GuiCardViewAccessor.POSITION_Y, .2f).target(40).start(tweenManager);
            return true;
        }
        return false;
    }

    public GuiCardView getUppedCard(){
        return uppedCard;
    }

    public void build(){
        int cardNumber = this.playerHandModel.getCards().size();
        float spaceBetweenCards = (((cardNumber * GuiCardView.NATIVE_CARD_WIDTH * customScale) - super.getWidth()) / cardNumber);
        int a = 0;
        for(CardModel cardModel : this.playerHandModel.getCards()) {
            GuiCardView cardView = new GuiCardView(cardModel);
            Vector2 cardPosition = new Vector2();
            cardPosition.x = a * spaceBetweenCards ;
            cardView.setPosition(cardPosition.x, cardPosition.y);
            cardView.resize(customScale);
            cardView.setTouchable(Touchable.enabled);
            cardView.setTweenManager(tweenManager);
            cardView.build();

            this.addActor(cardView);
            a++;
        }
    }
}
