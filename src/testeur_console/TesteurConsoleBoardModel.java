package testeur_console;

import model.BoardModel;
import view.console.ConsoleBoardView;

public class TesteurConsoleBoardModel {

    public static void launchGame () {
        BoardModel.getUniqueInstance().initRound();
        System.out.println(ConsoleBoardView.build());
        boolean hasWinner = false;

        while ( !hasWinner ) {
            BoardModel.getUniqueInstance().getPlayer().play();
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().isEmpty() ) {
                BoardModel.getUniqueInstance().getGameRules().countScore();
                hasWinner = BoardModel.getUniqueInstance().getGameRules().isWinner();
            }
        } 
    }
    
}
