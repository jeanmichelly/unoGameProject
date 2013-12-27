package fr.utt.isi.lo02.unoGame.view.gui.deck;

import aurelienribon.tweenengine.TweenManager;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;

public class GuiDrawPileView extends GuiPacketView {

    public GuiDrawPileView () {
        super(DrawPileModel.getUniqueInstance().getCards());
        this.setFlipped(true);
    }
    
    public void build () {
        super.build();
        if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
            setHighlited(true);
        }
    }
    
    public void setHighlited (boolean highlited) {
        super.topCardView.setTweenManager(new TweenManager());
        super.topCardView.setHighlited(highlited);
    }

}
