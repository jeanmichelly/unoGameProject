package fr.utt.isi.lo02.unoGame.view.console;


import java.util.List;
import java.util.Observable;
import java.util.Observer;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;

public class ConsoleDeckView implements Observer {
    
    private static StringBuffer headsCards = new StringBuffer();
    private static StringBuffer middlesCards = new StringBuffer();
    private static StringBuffer tailsCards = new StringBuffer();
    private static StringBuffer belowTailsCards = new StringBuffer();
    private static StringBuffer indexCards = new StringBuffer();
    
    public ConsoleDeckView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    public static void clear () {
        headsCards = new StringBuffer();
        middlesCards = new StringBuffer();
        tailsCards = new StringBuffer();
        belowTailsCards = new StringBuffer();
        indexCards = new StringBuffer();
    }
    
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
    
    protected static void buildIndexs (int i) {
        indexCards.append("  (" + i + ")     ");
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

    protected static String getIndexs () {
        return indexCards.toString();
    }
    
    protected static String build (List<CardModel> deck) {
        int i = 0;
        for ( CardModel card : deck ) {
            buildHeads();
            buildMiddles(card.getSymbol().getLabel());
            buildTails();
            buildBelowTails((card.getColor()==null) ? '•' : card.getColor().getLabel());
            buildIndexs(i);
            i++;
        }
        return headsCards+"\n"+
               middlesCards+"\n"+
               tailsCards+"\n"+
               belowTailsCards +
               indexCards;
    }

    @Override
    public void update (Observable o, Object arg) { }
    
}
