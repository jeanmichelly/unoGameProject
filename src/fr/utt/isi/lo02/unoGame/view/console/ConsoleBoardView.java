package fr.utt.isi.lo02.unoGame.view.console;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;

public class ConsoleBoardView {
    
    public static String clear () {
        ConsoleDeckView.clear();
        ConsolePlayersView.clear();
        return "\n\n";
    }
    
    public static String buildHead () {
        return "*********************************************************************************\n"+
               "§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§   UNO GAME   §§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§\n"+
               "*********************************************************************************\n";
    }
   
    public static String buildMiddle () {
        return  "\n"+
                "     "+ConsolePlayersView.getAboveHeads()+"\n"+
                "     "+ConsolePlayersView.getHeads()+"\n"+
                "     "+ConsolePlayersView.getMiddles()+"\n"+
                "     "+ConsolePlayersView.getTails()+"\n"+
                "     "+ConsolePlayersView.getBelowTails()+"\n"+
                "\n"+
                "                              "+ConsoleDiscardPileView.buildHead()+"     "+ConsoleDrawPileView.buildHead()+"                            \n"+
                "                              "+ConsoleDiscardPileView.buildMiddle()+"     "+ConsoleDrawPileView.buildMiddle()+"                            \n"+
                "                              "+ConsoleDiscardPileView.buildTail()+"     "+ConsoleDrawPileView.buildTail()+"                            \n"+
                "                              "+ConsoleDiscardPileView.buildBelowTail((DiscardPileModel.getUniqueInstance().peek().getColor()==null) ? '•' : DiscardPileModel.getUniqueInstance().peek().getColor().getLabel())+"     "+ConsoleDrawPileView.buildBelowTail()+"                              \n"+
                "\n"+
                "\n"+
                "     Au tour de "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+"\n"+
                "     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n"+
                "     "+ConsolePlayerHandView.getHeads()+"\n"+
                "     "+ConsolePlayerHandView.getMiddles()+"\n"+
                "     "+ConsolePlayerHandView.getTails()+"\n"+
                "     "+ConsolePlayerHandView.getBelowTails()+"\n"+
                " \n";
    }
    
    public static String buildTail () {
        return "_________________________________________________________________________________\n"+
               "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
    }
    
    public static String build () {
        ConsolePlayerHandView.build();
        ConsolePlayersView.build();
        String build = buildHead()+buildMiddle()+buildTail();
        
        return clear()+build;
    }
    
}
