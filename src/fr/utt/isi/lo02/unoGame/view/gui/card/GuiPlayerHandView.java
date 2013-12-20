package fr.utt.isi.lo02.unoGame.view.gui.card;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;

public class GuiPlayerHandView extends Group {

    private PlayerHandModel playerHandModel;
    private float customScale;

    public GuiPlayerHandView(PlayerHandModel playerHandModel) {
        this.playerHandModel = playerHandModel;
    }

    public void setCustomScale(float scale){
        this.customScale = scale;
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
            cardView.build();
            this.addActor(cardView);
            a++;
        }
    }
}
