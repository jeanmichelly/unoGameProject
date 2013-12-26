package fr.utt.isi.lo02.unoGame.view.gui.deck;

import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;

public class GuiDrawPileView extends GuiPacketView {
    
    public GuiDrawPileView () {
        super(DrawPileModel.getUniqueInstance().getCards());
        this.setFlipped(true);
    }

}
