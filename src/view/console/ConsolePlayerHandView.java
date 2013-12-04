package view.console;

import model.BoardModel;

public class ConsolePlayerHandView {
    
    protected static String getHeads () {
        return ConsoleDeckView.getHeads();
    }
    
    protected static String getMiddles () {
        return ConsoleDeckView.getMiddles();
    }
    
    protected static String getTails () {
        return ConsoleDeckView.getTails();
    }
    
    protected static String getBelowTails () {
        return ConsoleDeckView.getBelowTails();
    }
    
    public static String build () {
        return ConsoleDeckView.build(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards());
    }
    
}