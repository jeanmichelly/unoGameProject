package view.console;

import model.deck.DrawPileModel;

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
        byte sizeDrawPile =(byte) DrawPileModel.getUniqueInstance().getCards().size(); 
        return (sizeDrawPile>9) ? "  "+sizeDrawPile : "  "+sizeDrawPile;
    }
    
    public static String build () {
        return buildHead()+"\n"+
               buildMiddle()+"\n"+
               buildTail()+"\n"+
               buildBelowTail();
    }
    
}
