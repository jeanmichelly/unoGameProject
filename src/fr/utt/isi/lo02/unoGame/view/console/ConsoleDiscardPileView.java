package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;

public class ConsoleDiscardPileView implements Observer {

    public ConsoleDiscardPileView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
	public static String buildHead () {
		return ConsoleCardView.buildHead();
	}
    
    public static String buildMiddle () {
		return ConsoleCardView.buildMiddle(DiscardPileModel.getUniqueInstance().peek().getSymbol().getLabel());
    }
    
    public static String buildTail () {
        return ConsoleCardView.buildTail();
    }
    
    public static String buildBelowTail (char c) {
    	    return ConsoleCardView.buildBelowTail(c);
    }
    
    public static String build () {
        return ConsoleCardView.build(DiscardPileModel.getUniqueInstance().peek());
    }

    @Override
    public void update (Observable o, Object arg) { }
    
}
