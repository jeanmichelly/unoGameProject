package testeurConsole;

import model.BoardModel;
import model.UserModel;
import model.deck.DiscardPileModel;
import view.console.ConsoleBoardView;

public class TesteurConsoleBoardModel {

    public static void launchGame () {
        BoardModel.getUniqueInstance().initRound();
        boolean hasWinner = false;
        String playerWinner = "";

        // Affichage du plateau de jeu
        System.out.println(ConsoleBoardView.build());

        while ( !hasWinner ) {
            BoardModel.getUniqueInstance().getPlayer().play();
            if (! BoardModel.getUniqueInstance().getPlayer().getPlayerHand().isEmpty() ) {
                if ( !DiscardPileModel.getUniqueInstance().hasApplyEffectLastCard() ) // Appliquer l'effet d'une carte posé une seule fois
                    BoardModel.getUniqueInstance().applyCardEffect();
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            }
            else {
                BoardModel.getUniqueInstance().applyCardEffect(); // Le joueur a forcément posé une carte
                BoardModel.getUniqueInstance().getGameRules().countScore();
                hasWinner = BoardModel.getUniqueInstance().getGameRules().isWinner();
                playerWinner = BoardModel.getUniqueInstance().getPlayer().getPseudonym();
            }
//            try {
//                 Thread.sleep(1000);
//            } catch (InterruptedException e) { }
            System.out.println(ConsoleBoardView.build());
        } 
        System.out.println(playerWinner+"a gagn�� la partie !!!");
    }
    
    public static void main (String [] args) {
        BoardModel board = BoardModel.getUniqueInstance();
        UserModel.initNumberPlayers();
        UserModel.initNumberHumanPlayers();
        UserModel.initPseudonymsHumanPlayers();
        UserModel.initChoiceGameRules();
        board.createPlayers();
        board.initHumanPlayers();
        board.initComputerPlayers();
        board.initGameRules();
        board.chooseRandomDealer();
        board.dispenseCards();
        board.initGame();

        TesteurConsoleBoardModel.launchGame();
    }
    
}
