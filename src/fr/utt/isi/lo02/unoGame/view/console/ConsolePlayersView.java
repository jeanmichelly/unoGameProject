package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

public class ConsolePlayersView implements Observer {
    
    private static StringBuffer aboveHeadsCards = new StringBuffer();
    private static StringBuffer headsCards = new StringBuffer();
    private static StringBuffer middlesCards = new StringBuffer();
    private static StringBuffer tailsCards = new StringBuffer();
    private static StringBuffer belowTailsCards = new StringBuffer();
    
    public ConsolePlayersView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    public static void clear () {
        aboveHeadsCards = new StringBuffer();
        headsCards = new StringBuffer();
        middlesCards = new StringBuffer();
        tailsCards = new StringBuffer();
        belowTailsCards = new StringBuffer();
    }
    
    protected static void buildAboveHeads (int s) {
        String marginLeft = "", marginRight = "";
        if ( s < 10 ) {
            marginLeft = "  ";
            marginRight = "     ";
        } else if ( s < 100 ) {
            marginLeft = " ";
            marginRight = "     ";
        } else {
            marginLeft = " ";
            marginRight = "    ";
        }
        aboveHeadsCards.append(marginLeft+s+marginRight);
    }
    
    protected static void buildHeads () {
        headsCards.append(" /|\\    ");
    }
    
    protected static void buildMiddles () {
        middlesCards.append("| | |   ");
    }
    
    protected static void buildTails () {
        tailsCards.append(" \\|/    ");
    }
    
    public static void buildBelowTails (int s) {
        belowTailsCards.append((s>9) ? "  "+s+"    " : "  "+s+"     ");
    }
    
    protected static String getAboveHeads () {
        return aboveHeadsCards.toString();
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
    
    public static String build () {
        for ( PlayerModel player: BoardModel.getUniqueInstance().getPlayers() ) {
            buildAboveHeads(player.getScore());
            buildHeads();
            buildMiddles();
            buildTails();
            buildBelowTails(player.getPlayerHand().numberCards());
        }
        return headsCards+"\n"+
               middlesCards+"\n"+
               tailsCards+"\n"+
               belowTailsCards;
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsolePlayersView.build();
    }
    
}
