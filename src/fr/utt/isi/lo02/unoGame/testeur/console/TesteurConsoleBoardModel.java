package fr.utt.isi.lo02.unoGame.testeur.console;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.UserModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidPlayException;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

public class TesteurConsoleBoardModel {

    public static void launchGame () throws InvalidPlayException, InvalidGameRulesException {
        BoardModel.getUniqueInstance().initRound();
        boolean hasWinner = false;
        String playerWinner = "";

        // Affichage du plateau de jeu
        System.out.println(ConsoleBoardView.build());

        while ( !hasWinner ) {
            if(BoardModel.getUniqueInstance().getDrawPile().size() == 0){
                BoardModel.getUniqueInstance().getDrawPile().reshuffle(BoardModel.getUniqueInstance().getDiscardPile().reshuffle());
            }
            try {
                BoardModel.getUniqueInstance().getPlayer().play();
            } catch (Exception e) {
                InvalidPlayException ipe = new InvalidPlayException();
                ipe.initCause(e);
                throw ipe;
            }
            if (! BoardModel.getUniqueInstance().getPlayer().getPlayerHand().isEmpty() ) {
                if ( !DiscardPileModel.getUniqueInstance().hasApplyEffectLastCard() ) // Appliquer l'effet d'une carte posée une seule fois
                    BoardModel.getUniqueInstance().applyCardEffect();
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            }
            else {
                BoardModel.getUniqueInstance().applyCardEffect(); // Le joueur a forcément posé une carte
                try {
                    BoardModel.getUniqueInstance().getGameRules().countScore();
                    hasWinner = BoardModel.getUniqueInstance().getGameRules().isWinner();
                } catch (Exception e) {
                    InvalidGameRulesException igre = new InvalidGameRulesException();
                    igre.initCause(e);
                    throw igre;
                }
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

        try {
            TesteurConsoleBoardModel.launchGame();
        } catch (InvalidPlayException e) {
            e.printStackTrace();
        } catch (InvalidGameRulesException e) {
            e.printStackTrace();
        }
    }
    
}
