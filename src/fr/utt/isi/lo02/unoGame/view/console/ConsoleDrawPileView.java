package fr.utt.isi.lo02.unoGame.view.console;

import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;

public class ConsoleDrawPileView {
    
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
    
}
