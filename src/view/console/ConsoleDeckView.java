package view.console;

import java.util.List;
import model.card.CardModel;

public class ConsoleDeckView {
    
    private static StringBuffer headsCards = new StringBuffer();
    private static StringBuffer middlesCards = new StringBuffer();
    private static StringBuffer tailsCards = new StringBuffer();
    private static StringBuffer belowTailsCards = new StringBuffer();
    
    protected static void buildHeads () {
        headsCards.append(ConsoleCardView.buildHead()+"   ");
    }
    
    protected static void buildMiddles (char s) {
        middlesCards.append(ConsoleCardView.buildMiddle(s)+"   ");
    }
    
    protected static void buildTails () {
        tailsCards.append(ConsoleCardView.buildTail()+"   ");
    }
    
    protected static void buildBelowTails (char c) {
        belowTailsCards.append(ConsoleCardView.buildBelowTail(c)+"   ");
    }

    protected static String getHeads () {
        return headsCards.toString();
    }
    
    protected static String getMiddles () {
        return middlesCards.toString();    
    }
    
    protected static String getTails () {
        return tailsCards.toString();    
    }
    
    protected static String getBelowTails () {
        return belowTailsCards.toString();    
    }
    
    protected static String build(List<CardModel> deck) {
        for ( CardModel card : deck ) {
            buildHeads();
            buildMiddles(card.getSymbol().getLabel());
            buildTails();
            buildBelowTails((card.getColor()==null) ? '•' : card.getColor().getLabel());
        }
        return headsCards+"\n"+
               middlesCards+"\n"+
               tailsCards+"\n"+
               belowTailsCards;
    }
    
}
