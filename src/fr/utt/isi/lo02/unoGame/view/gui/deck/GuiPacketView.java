package fr.utt.isi.lo02.unoGame.view.gui.deck;

import java.util.Stack;

import com.badlogic.gdx.scenes.scene2d.Group;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;

public abstract class GuiPacketView extends Group {

    private Stack<CardModel> cardPacket;
    private float customScale;
    private boolean flipped;

    protected GuiPacketView(Stack<CardModel> cardPacket) {
        this.cardPacket = cardPacket;
        this.setCustomScale(.5f);
        this.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
    }

    public void setCustomScale (float scale) {
        this.customScale = scale;
    }

    public void build () {
        GuiCardView cardView = new GuiCardView(this.cardPacket.peek());
        cardView.setPosition(super.getX(), super.getY());
        cardView.resize(customScale);
        if ( this.flipped ) {
            cardView.flipCard();
        }
        cardView.build();
        this.addActor(cardView);
    }

    public void setFlipped (boolean flipped) {
        this.flipped = flipped;
    }

}