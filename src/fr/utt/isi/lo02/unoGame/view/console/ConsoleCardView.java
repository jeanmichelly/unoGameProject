package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;

public class ConsoleCardView implements Observer {
    
    public ConsoleCardView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
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

    @Override
    public void update (Observable o, Object arg) { }
    
}
