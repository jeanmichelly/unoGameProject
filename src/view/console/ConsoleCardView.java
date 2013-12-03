package view.console;

import model.card.CardModel;

public class ConsoleCardView {
    
    public static String buildHead () {
        return "|¯¯¯¯¯|";
    }
    
    public static String buildMiddle (char s) {
        return "|  " + s + "  |";
    }
    
    public static String buildTail () {
        return "|_____|";
    }
    
    public static String buildBelowTail (char c) {
        return "  ·" + c + "·  ";
    }
    
    public static String build (CardModel card) {
        return buildHead()+"\n"+
               buildMiddle(card.getSymbol().getLabel())+"\n"+
               buildTail()+"\n"+
               buildBelowTail( (card.getColor()==null) ? '•' : card.getColor().getLabel() );
    }
    
}
