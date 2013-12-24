package fr.utt.isi.lo02.unoGame.testUnitaire.player.strategy;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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

public class PriorityColorStrategyModelTest {

    @Test
    public void executeTest () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        // Mettre que des joueurs ordinateurs pour pouvoir tester
        BoardModel board = BoardModel.getUniqueInstance();
        ConsoleGameParametersModel.initNumberPlayers((byte) 3);
        board.createPlayers();
        board.initComputerPlayers();
        board.setPlayerCursor((byte)0);
        DiscardPileModel.getUniqueInstance().addCard(new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));
        
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.NINE, ColorModel.YELLOW, (byte)9, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.SEVEN, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.YELLOW, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect())));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.YELLOW, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect())));

        assertEquals(false, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(0).execute());
        
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.ZERO, ColorModel.BLUE, (byte)0, new CompositeEffectModel()));
        assertEquals(true, ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(0).execute());
        assertEquals(true, DiscardPileModel.getUniqueInstance().peek().equals(new CardModel(SymbolModel.ZERO, ColorModel.BLUE, (byte)0, new CompositeEffectModel())));
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard( new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect())));
        ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(0).execute();
        assertEquals(true, DiscardPileModel.getUniqueInstance().peek().equals(new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect()))));
    }

}
