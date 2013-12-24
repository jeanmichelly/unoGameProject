package fr.utt.isi.lo02.unoGame.testUnitaire.player;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

public class HumanPlayerModelTest {

    @Test
    public void playTest () throws InvalidActionPickCardException, InvalidActionPutDownCardException, InvalidColorModelException {
        CardModel c1 = new CardModel(SymbolModel.ONE, ColorModel.BLUE, (byte)1, new CompositeEffectModel());
        CardModel c2 = new CardModel(SymbolModel.TWO, ColorModel.GREEN, (byte)2, new CompositeEffectModel());
        CardModel c3 = new CardModel(SymbolModel.THREE, ColorModel.YELLOW, (byte)3, new CompositeEffectModel());
        CardModel c9 = new CardModel(SymbolModel.NINE, ColorModel.GREEN, (byte)9, new CompositeEffectModel());


        GameSettingsModel.getUniqueInstance().initNumberPlayers((byte)3);
        GameSettingsModel.getUniqueInstance().initNumberHumanPlayers((byte)3);
        GameSettingsModel.getUniqueInstance().initPseudonymsHumanPlayers();
        BoardModel.getUniqueInstance().createPlayers();
        BoardModel.getUniqueInstance().initHumanPlayers();
        BoardModel.getUniqueInstance().chooseRandomDealer();
        DiscardPileModel.getUniqueInstance().push(c9);
        
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard(c1);
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard(c2);
        BoardModel.getUniqueInstance().getPlayer().play();
        DiscardPileModel.getUniqueInstance().push(c3);
        BoardModel.getUniqueInstance().getPlayer().play();
    }

}
