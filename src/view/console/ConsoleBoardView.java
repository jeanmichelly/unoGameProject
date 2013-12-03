package view.console;

import model.BoardModel;
import model.deck.DiscardPileModel;

public class ConsoleBoardView {
    
    public static String clear () {
        return "\n\n";
    }
    
    public static String buildHead () {
        return "*********************************************************************************\n"+
               "*************************************UNO GAME************************************\n"+
               "*********************************************************************************\n";
    }
   
    public static String buildMiddle () {
        return  "****                                                                         ****\n"+
                "****   "+ConsolePlayersView.getHeads()+"\n"+
                "****   "+ConsolePlayersView.getMiddles()+"\n"+
                "****   "+ConsolePlayersView.getTails()+"\n"+
                "****   "+ConsolePlayersView.getBelowTails()+"\n"+
                "****                                                                         ****\n"+
                "****                            "+ConsoleDiscardPileView.buildHead()+"     "+ConsoleDrawPileView.buildHead()+"                            ****\n"+
                "****                            "+ConsoleDiscardPileView.buildMiddle()+"     "+ConsoleDrawPileView.buildMiddle()+"                            ****\n"+
                "****                            "+ConsoleDiscardPileView.buildTail()+"     "+ConsoleDrawPileView.buildTail()+"                            ****\n"+
                "****                            "+ConsoleDiscardPileView.buildBelowTail((DiscardPileModel.getUniqueInstance().peek().getColor()==null) ? '•' : DiscardPileModel.getUniqueInstance().peek().getColor().getLabel())+"     "+ConsoleDrawPileView.buildBelowTail()+"                              ****\n"+
                "****                                                                         ****\n"+
                "****   Au tour de "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+"\n"+
                "****   "+ConsolePlayerHandView.getHeads()+"\n"+
                "****   "+ConsolePlayerHandView.getMiddles()+"\n"+
                "****   "+ConsolePlayerHandView.getTails()+"\n"+
                "****   "+ConsolePlayerHandView.getBelowTails()+"\n"+
                "****                                                                         ****\n";
    }
    
    public static String buildTail () {
        return "*********************************************************************************\n"+
               "*********************************************************************************\n"+
               "*********************************************************************************";
    }
    
    public static String build () {
        ConsolePlayerHandView.build();
        ConsolePlayersView.build();
        
        return clear()+buildHead()+buildMiddle()+buildTail();
    }

}
