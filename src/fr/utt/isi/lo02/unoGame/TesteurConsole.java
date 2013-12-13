package fr.utt.isi.lo02.unoGame;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.UserModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidPlayException;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayersView;

/**
 * 
 * Cette classe permet de tester et debugger l'application.
 * 
 */
public class TesteurConsole {

    public static void launchGame () throws InvalidPlayException, 
                                            InvalidActionPutDownCardException, 
                                            InvalidGameRulesException, InvalidActionPickCardException {
        
        ConsoleBoardView consoleBoardView = new ConsoleBoardView(); 
        ConsolePlayerHandView consolePlayerHandView = new ConsolePlayerHandView();
        ConsolePlayersView consolePlayersView = new ConsolePlayersView();
     
        ConsoleBoardView.ConsoleBoardController.play();
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
        TesteurConsole.launchGame();
    }

}