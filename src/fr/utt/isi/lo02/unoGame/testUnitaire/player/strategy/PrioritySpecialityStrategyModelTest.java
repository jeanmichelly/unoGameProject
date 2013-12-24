package fr.utt.isi.lo02.unoGame.testUnitaire.player.strategy;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

public class PrioritySpecialityStrategyModelTest {

    @Test
    public void executeTest () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        // Mettre que des joueurs ordinateurs pour pouvoir tester
        BoardModel board = BoardModel.getUniqueInstance();
        ConsoleGameSettingsModel.initNumberPlayers();
        board.createPlayers();
        board.initComputerPlayers();
        board.setPlayerCursor((byte)0);
        DiscardPileModel.getUniqueInstance().addCard(new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));
        assertEquals(false, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(2).execute());
        
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect())));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.REVERSE, ColorModel.GREEN, (byte)20, new CompositeEffectModel(CompositeEffectModel.getReverseEffect())));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.THREE, ColorModel.BLUE, (byte)3, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.NINE, ColorModel.BLUE, (byte)9, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)9, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.REVERSE, ColorModel.YELLOW, (byte)20, new CompositeEffectModel(CompositeEffectModel.getReverseEffect())));
        assertEquals(true, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(2).execute());
        assertEquals(true, DiscardPileModel.getUniqueInstance().peek().equals(new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect()))));
        assertEquals(false, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(2).execute());
    }

}
