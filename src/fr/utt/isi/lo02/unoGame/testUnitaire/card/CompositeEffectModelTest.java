package fr.utt.isi.lo02.unoGame.testUnitaire.card;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Stack;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.DrawEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.SkipEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.WildEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;

public class CompositeEffectModelTest {

    @Test
    public void applyWildDrawFourEffectsTest () throws InvalidActionPickCardException, InvalidColorModelException {
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
        
        CompositeEffectModel wildDrawFourEffects = new CompositeEffectModel();
        wildDrawFourEffects.addEffect(new DrawEffectModel(), 4);
        wildDrawFourEffects.addEffect(new SkipEffectModel());
        wildDrawFourEffects.addEffect(new WildEffectModel());
        wildDrawFourEffects.sortEffectsByPriority();
        CardModel wildDrawFourCard = new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, wildDrawFourEffects);
       
        DiscardPileModel.getUniqueInstance().push(wildDrawFourCard);
        
        assertNull(DiscardPileModel.getUniqueInstance().peek().getColor());
        
        board.applyCardEffect();
        
        assertEquals(11, board.getPlayer(0).getPlayerHand().numberCards()); // Verifie si le joueur suivant a 4 carte en plus     

        assertNotNull(DiscardPileModel.getUniqueInstance().peek().getColor()); // Verifie la couleur
        boolean existColor = false;
        for ( ColorModel color : GameRulesModel.COLORS ) {
            if (color == DiscardPileModel.getUniqueInstance().peek().getColor())
                existColor = true;
        }
        assertEquals(true, existColor); 
        
        assertEquals(0, board.getPlayerCursor()); // Verifie si le curseur se déplace bien 
    }
    
    @Test
    public void  applyWildDrawTwoEffectsTest () throws InvalidActionPickCardException, InvalidColorModelException {
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
        
        CompositeEffectModel drawTwoEffects = new CompositeEffectModel();
        drawTwoEffects.addEffect(new DrawEffectModel(), 2);
        drawTwoEffects.addEffect(new SkipEffectModel());
        CardModel drawTwoCard = new CardModel(SymbolModel.DRAW_TWO, ColorModel.GREEN, (byte)20, drawTwoEffects);
        
        DiscardPileModel.getUniqueInstance().push(drawTwoCard);
        board.applyCardEffect();
        
        assertEquals(9, board.getPlayer(0).getPlayerHand().numberCards()); // Verifie si le joueur suivant a 2 carte en plus
        assertEquals(0, board.getPlayerCursor()); // Verifie si le curseur se déplace bien 
    }
}
