package fr.utt.isi.lo02.unoGame.testUnitaire.player.strategy;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameParametersModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PriorityNumberStrategyModelTest {

    @Test
    public void executeTest () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        // Mettre que des joueurs ordinateurs pour pouvoir tester
        BoardModel board = BoardModel.getUniqueInstance();
        ConsoleGameParametersModel.initNumberPlayers();
        board.createPlayers();
        board.initComputerPlayers();
        board.setPlayerCursor((byte)0);

        DiscardPileModel.getUniqueInstance().addCard(new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.NINE, ColorModel.BLUE, (byte)9, new CompositeEffectModel()));
        assertEquals(false, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(1).execute());
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        assertEquals(true, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(1).execute());
        assertEquals(true, DiscardPileModel.getUniqueInstance().peek().equals(new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, new CompositeEffectModel())));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.EIGHT, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.ONE, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));

        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel()));
        assertEquals(true, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(1).execute());

        assertEquals(true, DiscardPileModel.getUniqueInstance().peek().equals(new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel())));

    }

}
