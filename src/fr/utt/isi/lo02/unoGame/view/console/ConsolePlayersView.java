package fr.utt.isi.lo02.unoGame.view.console;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

public class ConsolePlayersView {
    
    private static StringBuffer aboveHeadsCards = new StringBuffer();
    private static StringBuffer headsCards = new StringBuffer();
    private static StringBuffer middlesCards = new StringBuffer();
    private static StringBuffer tailsCards = new StringBuffer();
    private static StringBuffer belowTailsCards = new StringBuffer();
    
    public static void clear () {
        aboveHeadsCards = new StringBuffer();
        headsCards = new StringBuffer();
        middlesCards = new StringBuffer();
        tailsCards = new StringBuffer();
        belowTailsCards = new StringBuffer();
    }
    
    protected static void buildAboveHeads (int s) {
        aboveHeadsCards.append((s>9) ? " "+s+"   " : "  "+s+"     ");
    }
    
    protected static void buildHeads () {
        headsCards.append(" /|\\    ");
    }
    
    protected static void buildMiddles () {
        middlesCards.append("®®®®®   ");
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
        int playerCursor = BoardModel.getUniqueInstance().getPlayerCursor();
        PlayerModel [] players = BoardModel.getUniqueInstance().getPlayers();
        if (BoardModel.getUniqueInstance().getDirectionOfPlay() == 1) {
            for ( int i=playerCursor+1; i<players.length; i++ ) {
                buildAboveHeads(players[i].getScore());
                buildHeads();
                buildMiddles();
                buildTails();
                buildBelowTails(players[i].getPlayerHand().size());
            }
            for ( int i=0; i<playerCursor; i++ ) {
                buildAboveHeads(players[i].getScore());
                buildHeads();
                buildMiddles();
                buildTails();
                buildBelowTails(players[i].getPlayerHand().size());
            }
        } else {
            for ( int i=playerCursor-1; i>=0; i-- ) {
                buildAboveHeads(players[i].getScore());
                buildHeads();
                buildMiddles();
                buildTails();
                buildBelowTails(players[i].getPlayerHand().size());
            }
            for ( int i=players.length-1; i>playerCursor; i-- ) {
                buildAboveHeads(players[i].getScore());
                buildHeads();
                buildMiddles();
                buildTails();
                buildBelowTails(players[i].getPlayerHand().size());
            }
        }
        return headsCards+"\n"+
               middlesCards+"\n"+
               tailsCards+"\n"+
               belowTailsCards;
    }
    
}
