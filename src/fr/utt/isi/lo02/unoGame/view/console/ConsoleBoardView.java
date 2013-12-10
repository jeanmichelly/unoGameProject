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
        String antiClockWiseDirection = "";
        String clockWiseDirection = "";
        String marginSupp = "";
        String playerCursor=" ";

        if ( BoardModel.getUniqueInstance().getDirectionOfPlay() == 1 )
            clockWiseDirection = "---->";
        else {
            antiClockWiseDirection = "<----   ";   
            marginSupp = "        ";
        }
        
        if (BoardModel.getUniqueInstance().getPlayerCursor() != 0)
            for (int i=0; i<BoardModel.getUniqueInstance().getPlayerCursor(); i++)
                playerCursor += "        ";
        
        playerCursor += "~•~";
        System.out.println(BoardModel.getUniqueInstance().getPlayerCursor());
        return  "|     "+marginSupp+ConsolePlayersView.getAboveHeads()+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getHeads()+"\n"+
                "|     "+antiClockWiseDirection+ConsolePlayersView.getMiddles()+clockWiseDirection+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getTails()+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getBelowTails()+"\n"+
                "|     "+playerCursor+"\n"+
                "|\n"+
                "|                              "+ConsoleDiscardPileView.buildHead()+"     "+ConsoleDrawPileView.buildHead()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildMiddle()+"     "+ConsoleDrawPileView.buildMiddle()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildTail()+"     "+ConsoleDrawPileView.buildTail()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildBelowTail((DiscardPileModel.getUniqueInstance().peek().getColor()==null) ? '•' : DiscardPileModel.getUniqueInstance().peek().getColor().getLabel())+"     "+ConsoleDrawPileView.buildBelowTail()+"                              \n"+
                "|\n"+
                "|     Au tour de "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+"\n"+
                "|     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n"+
                "|     "+ConsolePlayerHandView.getHeads()+"\n"+
                "|     "+ConsolePlayerHandView.getMiddles()+"\n"+
                "|     "+ConsolePlayerHandView.getTails()+"\n"+
                "|     "+ConsolePlayerHandView.getBelowTails()+"\n"+
                "|     "+ConsolePlayerHandView.getIndexs()+"\n";
    }
    
    public static String buildTail () {
        return "|________________________________________________________________________________\n"+
               "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
    }
    
    public static String build () {
        ConsolePlayerHandView.build();
        ConsolePlayersView.build();
        String build = buildHead()+buildMiddle()+buildTail();
        
        return clear()+build;
    }
    
}
