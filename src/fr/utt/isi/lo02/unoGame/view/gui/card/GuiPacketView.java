package fr.utt.isi.lo02.unoGame.view.gui.card;

import com.badlogic.gdx.scenes.scene2d.Group;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;

import java.util.Stack;

public class GuiPacketView extends Group {

    private Stack<CardModel> cardPacket;
    private float customScale;
    private boolean flipped;

    public GuiPacketView(Stack<CardModel> cardPacket) {
        this.cardPacket = cardPacket;
    }

    public void setCustomScale(float scale){
        this.customScale = scale;
    }

    public void build(){
        GuiCardView cardView = new GuiCardView(cardPacket.peek());
        cardView.setPosition(super.getX(), super.getY());
        cardView.resize(customScale);
        if(flipped){
            cardView.flipCard();
        }
        cardView.build();
        this.addActor(cardView);
    }

    public void setFlipped(boolean flipped) {
        this.flipped = flipped;
    }
}
