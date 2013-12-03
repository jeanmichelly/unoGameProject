package test_unitaires.card;

import static org.junit.Assert.*;
import java.util.Stack;
import model.BoardModel;
import model.UserModel;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.CompositeEffectModel;
import model.card.effect.WildEffectModel;
import model.deck.DiscardPileModel;
import model.game_rules.GameRulesModel;
import org.junit.Test;

public class WildEffectModelTest {

    @Test
    public void applyEffectTest() {
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
        effects.addEffect(new WildEffectModel());
        CardModel wildCard = new CardModel(SymbolModel.WILD, null, (byte)50, effects);
        
        DiscardPileModel.getUniqueInstance().push(wildCard);
        
        assertNull(DiscardPileModel.getUniqueInstance().peek().getColor());
        
        board.applyCardEffect();
        
        assertNotNull(DiscardPileModel.getUniqueInstance().peek().getColor());
        
        boolean existColor = false;
        for ( ColorModel color : GameRulesModel.COLORS ) { // Verifie la couleur
            if (color == DiscardPileModel.getUniqueInstance().peek().getColor())
                existColor = true;
        }
        assertEquals(true, existColor);   
    }

}
