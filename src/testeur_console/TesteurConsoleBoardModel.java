package testeur_console;

import model.BoardModel;
import view.console.ConsoleBoardView;

public class TesteurConsoleBoardModel {

    public static void launchGame () {
        BoardModel.getUniqueInstance().initRound();
        System.out.println(ConsoleBoardView.build());
        boolean hasWinner = false;
        String playerWinner = "";

        while ( !hasWinner ) {
            BoardModel.getUniqueInstance().getPlayer().play();
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().isEmpty() ) {
                BoardModel.getUniqueInstance().getGameRules().countScore();
                hasWinner = BoardModel.getUniqueInstance().getGameRules().isWinner();
                playerWinner = BoardModel.getUniqueInstance().getPlayer().getPseudonym();
            }
            else {
                BoardModel.getUniqueInstance().applyCardEffect();
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
            ConsoleBoardView.build();
        } 
        System.out.println(playerWinner+"a gagné la partie !!!");
    }
    
}
