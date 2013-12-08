package testUnitaire.card;

import static org.junit.Assert.*;
import java.util.Stack;
import model.BoardModel;
import model.UserModel;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.CompositeEffectModel;
import model.card.effect.DrawEffectModel;
import model.deck.DiscardPileModel;

import org.junit.Test;

public class DrawEffectModelTest {

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
        board.setPlayerCursor((byte)2); 
        board.dispenseCards();
        board.initRound();
        
        CompositeEffectModel effects = new CompositeEffectModel();
        effects.addEffect(new DrawEffectModel());
        CardModel drawCard = new CardModel(SymbolModel.DRAW_TWO, ColorModel.GREEN, (byte)20, effects);
        
        DiscardPileModel.getUniqueInstance().push(drawCard);
        board.applyCardEffect();
        
        assertEquals(8, board.getNextPlayer().getPlayerHand().size()); // Verifie si le joueur suivant a 1 carte en plus     
    }

}
