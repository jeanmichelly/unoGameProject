package fr.utt.isi.lo02.unoGame.testUnitaire.card;

import static org.junit.Assert.*;

import java.util.Stack;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.UserModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.ReverseEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

public class ReverseEffectModelTest {

    @Test
    public void applyEffectTest () throws InvalidActionPickCardException, InvalidColorModelException {
		BoardModel board = BoardModel.getUniqueInstance();
	    UserModel.setNumberPlayers((byte)3);
	    UserModel.setNumberHumanPlayers((byte)3);
	    Stack<String> pseudonymsHumanPlayers = new Stack<String>();
	    pseudonymsHumanPlayers.push("Pablo");
	    pseudonymsHumanPlayers.push("JM");
	    pseudonymsHumanPlayers.push("Mario");
	    UserModel.setPseudonymsHumanPlayers(pseudonymsHumanPlayers);
	    board.createPlayers();
	    board.initHumanPlayers();
	    board.initComputerPlayers();
	    board.setPlayerCursor((byte)0);			
	    board.initRound();
	    byte directionOfPlay = BoardModel.getUniqueInstance().getDirectionOfPlay();

	    CompositeEffectModel effects = new CompositeEffectModel();
	    effects.addEffect(new ReverseEffectModel());
	    CardModel reverseCard = new CardModel(SymbolModel.REVERSE, ColorModel.GREEN, (byte)20, effects);
	    
	    DiscardPileModel.getUniqueInstance().push(reverseCard);
	    board.applyCardEffect();
	    
	    assertEquals(-directionOfPlay, BoardModel.getUniqueInstance().getDirectionOfPlay()); // Verifie l'inversion du sens du jeu
	    assertEquals(2, board.moveCursorToNextPlayer()); // Verifie si le curseur se déplace bien en inversion du sens du jeu
	   	assertEquals(1, board.moveCursorToNextPlayer());
    }

}
