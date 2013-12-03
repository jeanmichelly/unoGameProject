package view.console;

import model.BoardModel;
import model.player.PlayerModel;

public class ConsolePlayersView {
    
    private static StringBuffer aboveHeadsCards = new StringBuffer();
    private static StringBuffer headsCards = new StringBuffer();
    private static StringBuffer middlesCards = new StringBuffer();
    private static StringBuffer tailsCards = new StringBuffer();
    private static StringBuffer belowTailsCards = new StringBuffer();
    
    protected static void buildAboveHeads (int s) {
        aboveHeadsCards.append((s>9) ? " "+s+"   " : "  "+s+"     ");
    }
    
    protected static void buildHeads () {
        headsCards.append("#####   ");
    }
    
    protected static void buildMiddles () {
        middlesCards.append("#####   ");
    }
    
    protected static void buildTails () {
        tailsCards.append("#####   ");
    }
    
    public static void buildBelowTails (int s) {
        belowTailsCards.append((s>9) ? " "+s+"   " : "  "+s+"     ");  
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
            buildBelowTails(player.getPlayerHand().size());
        }
        return headsCards+"\n"+
               middlesCards+"\n"+
               tailsCards+"\n"+
               belowTailsCards;
    }
    
}
