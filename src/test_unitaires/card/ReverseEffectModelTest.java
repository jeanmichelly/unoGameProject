package test_unitaires.card;

import static org.junit.Assert.*;

import java.util.Stack;

import model.BoardModel;
import model.UserModel;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.CompositeEffectModel;
import model.card.effect.ReverseEffectModel;
import model.deck.DiscardPileModel;
import org.junit.Test;

public class ReverseEffectModelTest {

    @Test
    public void applyEffectTest () {
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
