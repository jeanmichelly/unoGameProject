package test_unitaires.player;

import model.BoardModel;
import model.UserModel;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.CompositeEffectModel;
import model.deck.DiscardPileModel;
import org.junit.Test;

public class HumanPlayerModelTest {

    @Test
    public void playTest () {
        CardModel c1 = new CardModel(SymbolModel.ONE, ColorModel.BLUE, (byte)1, new CompositeEffectModel());
        CardModel c2 = new CardModel(SymbolModel.TWO, ColorModel.GREEN, (byte)2, new CompositeEffectModel());
        CardModel c3 = new CardModel(SymbolModel.THREE, ColorModel.YELLOW, (byte)3, new CompositeEffectModel());
        CardModel c9 = new CardModel(SymbolModel.NINE, ColorModel.GREEN, (byte)9, new CompositeEffectModel());


        UserModel.initNumberPlayers();
        UserModel.initNumberHumanPlayers();
        UserModel.initPseudonymsHumanPlayers();
        BoardModel.getUniqueInstance().createPlayers();
        BoardModel.getUniqueInstance().initHumanPlayers();
        BoardModel.getUniqueInstance().chooseRandomDealer();
        DiscardPileModel.getUniqueInstance().push(c9);
        
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().add(c1);
        BoardModel.getUniqueInstance().getPlayer().getPlayerHand().add(c2);
        BoardModel.getUniqueInstance().getPlayer().play();
        
        DiscardPileModel.getUniqueInstance().push(c3);
        BoardModel.getUniqueInstance().getPlayer().play();
    }

}
