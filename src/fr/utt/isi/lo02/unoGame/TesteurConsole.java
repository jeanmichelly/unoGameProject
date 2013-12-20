package fr.utt.isi.lo02.unoGame;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidPlayException;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayersView;

/**
 * 
 * Cette classe permet de tester et debugger l'application.
 * 
 */
public class TesteurConsole {

    public static void launchGame () throws InvalidActionPickCardException, 
                                            InvalidColorModelException, 
                                            InvalidGameRulesException, 
                                            InvalidPlayException, 
                                            DrawPileIsEmptyAfterReshuffledException {
        
        ConsoleBoardView consoleBoardView = new ConsoleBoardView(); 
        ConsolePlayerHandView consolePlayerHandView = new ConsolePlayerHandView();
        ConsolePlayersView consolePlayersView = new ConsolePlayersView();
     
        CardModel initCardOfDiscardPileModel = DiscardPileModel.getUniqueInstance().peek();
        
        if ( initCardOfDiscardPileModel.getCompositeEffects().hasEffect() ) {
            BoardModel.getUniqueInstance().setChanged();
            BoardModel.getUniqueInstance().notifyObservers();
            if ( initCardOfDiscardPileModel.getCompositeEffects().isWildDrawFourEffect() ) {
                while( DiscardPileModel.getUniqueInstance().peek().getCompositeEffects().isWildDrawFourEffect() ) {
                    DiscardPileModel.getUniqueInstance().addCard(DrawPileModel.getUniqueInstance().pop());
                    BoardModel.getUniqueInstance().setChanged();
                    BoardModel.getUniqueInstance().notifyObservers();
                }
            }
            
            BoardModel.getUniqueInstance().applyCardEffect();
            if ( DiscardPileModel.getUniqueInstance().peek().getSymbol() == SymbolModel.DRAW_TWO ) {
                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers();
            }
        }
        ConsoleBoardView.ConsoleBoardController.play();
    }

    public static void main(String[] args) throws   InvalidActionPickCardException, 
                                                    InvalidColorModelException, 
                                                    InvalidGameRulesException, 
                                                    InvalidPlayException, 
                                                    DrawPileIsEmptyAfterReshuffledException {
        
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