package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;

public class ConsoleDrawPileView implements Observer {
    
    public ConsoleDrawPileView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    public static String buildHead () {
        return "@@@@@@";
    }
    
    public static String buildMiddle () {
        return "@@@@@@";
    }
    
    public static String buildTail () {
        return "@@@@@@";
    }
    
    public static String buildBelowTail () {
        return "  "+(byte) DrawPileModel.getUniqueInstance().getCards().size();
    }
    
    public static String build () {
        return buildHead()+"\n"+
               buildMiddle()+"\n"+
               buildTail()+"\n"+
               buildBelowTail();
    }

    @Override
    public void update (Observable o, Object arg) { }
    
}
