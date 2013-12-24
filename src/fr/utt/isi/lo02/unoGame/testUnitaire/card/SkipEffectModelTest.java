package fr.utt.isi.lo02.unoGame.testUnitaire.card;

import static org.junit.Assert.assertEquals;

import java.util.Stack;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.SkipEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

public class SkipEffectModelTest {

    @Test
    public void applyEffectTest () throws InvalidActionPickCardException, InvalidColorModelException {
        BoardModel board = BoardModel.getUniqueInstance();
        ConsoleGameSettingsModel.setNumberPlayers((byte)3);
        ConsoleGameSettingsModel.setNumberHumanPlayers((byte)3);
        Stack<String> pseudonymsHumanPlayers = new Stack<String>();
        pseudonymsHumanPlayers.push("Pablo");
        pseudonymsHumanPlayers.push("JM");
        pseudonymsHumanPlayers.push("Mario");
        ConsoleGameSettingsModel.setPseudonymsHumanPlayers(pseudonymsHumanPlayers);
        board.createPlayers();
        board.initHumanPlayers();
        board.initComputerPlayers();
        board.setPlayerCursor((byte)2);         
        board.initRound();
        
        CompositeEffectModel effects = new CompositeEffectModel();
        effects.addEffect(new SkipEffectModel());
        CardModel skipCard = new CardModel(SymbolModel.SKIP, ColorModel.GREEN, (byte)20, effects);
        
        DiscardPileModel.getUniqueInstance().push(skipCard);
        board.applyCardEffect();
        
        assertEquals(0, board.getPlayerCursor()); // Verifie si le curseur se déplace bien 
        board.setDirectionOfPlay();
        board.setPlayerCursor((byte)0);         
        board.applyCardEffect();
        assertEquals(2, board.getPlayerCursor());
    }

}
