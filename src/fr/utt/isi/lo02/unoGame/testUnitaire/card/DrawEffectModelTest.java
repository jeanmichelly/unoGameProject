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
import fr.utt.isi.lo02.unoGame.model.card.effect.DrawEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;

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