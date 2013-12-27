package fr.utt.isi.lo02.unoGame.view.gui.deck;

import java.util.Stack;

import com.badlogic.gdx.scenes.scene2d.Group;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;

public abstract class GuiPacketView extends Group {

    GuiCardView topCardView;
    private Stack<CardModel> cardPacket;
    private float customScale;
    private boolean flipped;

    protected GuiPacketView(Stack<CardModel> cardPacket) {
        this.cardPacket = cardPacket;
        this.setCustomScale(.5f);
        this.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
    }

    public void build () {
        this.topCardView = new GuiCardView(this.cardPacket.peek());
        this.topCardView.setPosition(super.getX(), super.getY());
        this.topCardView.resize(this.customScale);
      
        if ( this.flipped ) {
            this.topCardView.flipCard();
        }
        this.topCardView.build();
        this.addActor(this.topCardView);
    }
    
    public void setCustomScale (float scale) {
        this.customScale = scale;
    }

    public void setFlipped (boolean flipped) {
        this.flipped = flipped;
    }

}