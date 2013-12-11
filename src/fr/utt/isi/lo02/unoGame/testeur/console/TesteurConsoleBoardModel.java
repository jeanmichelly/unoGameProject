package fr.utt.isi.lo02.unoGame.testeur.console;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.UserModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidPlayException;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * 
 * Cette classe permet de tester et debugger l'application.
 * 
 */
public class TesteurConsoleBoardModel {

    public static void launchGame () throws InvalidPlayException, 
                                            InvalidActionPutDownCardException, 
                                            InvalidGameRulesException {

        Stack<String>playersWinnerGame = new Stack<String>();
        int numberGame = 4;

        while ( BoardModel.getUniqueInstance().getNumberGame() <= numberGame ) {
            System.out.println(ConsoleBoardView.build());
            try {
                BoardModel.getUniqueInstance().getPlayer().play();
            } catch (InvalidActionPutDownCardException e) {
                throw e;
            } catch (Exception e) {
                InvalidPlayException ipe = new InvalidPlayException();
                ipe.initCause(e);
                throw ipe;
            }

            if (!BoardModel.getUniqueInstance().getPlayer().getPlayerHand().isEmpty()) {

                if (!DiscardPileModel.getUniqueInstance().hasApplyEffectLastCard()) // Appliquer l'effet d'une carte posé une seule fois
                    BoardModel.getUniqueInstance().applyCardEffect();
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            } else {
                BoardModel.getUniqueInstance().applyCardEffect(); // Le joueur a forcément posé une carte
                String playerWinnerRound = BoardModel.getUniqueInstance().getPlayer().getPseudonym(); 
                try {
                    BoardModel.getUniqueInstance().getGameRules().countScore();
                    if ( BoardModel.getUniqueInstance().getGameRules().isWinner() ) {
                        System.out.println(ConsoleBoardView.build());
                        BoardModel.getUniqueInstance().nextRound();
                        System.out.println(playerWinnerRound + "a gagne la manche !!!");
                    }
                    else {
                        playersWinnerGame.push(playerWinnerRound);
                        System.out.println(ConsoleBoardView.build());
                        BoardModel.getUniqueInstance().nextGame();
                        for (int i=0; i<playersWinnerGame.size(); i++)
                            System.out.println("Gagnant de la partie n°"+(i+1)+" : "+playersWinnerGame.get(i));
                        System.out.println(playersWinnerGame.peek() + " a gagne cette partie !!!");
                    }
                } catch (Exception e) {
                    InvalidGameRulesException igre = new InvalidGameRulesException();
                    igre.initCause(e);
                    throw igre;
                }        
            }
            //try {
              //  Thread.sleep(1000);
        //    } catch (InterruptedException e) { }
        }
    }

    public static void main(String[] args) throws   InvalidPlayException, 
                                                    InvalidActionPutDownCardException, 
                                                    InvalidActionPickCardException, 
                                                    InvalidGameRulesException {
        
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
