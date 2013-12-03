package view.console;

import model.deck.DiscardPileModel;

public class ConsoleDiscardPileView {

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
    
}
