package fr.utt.isi.lo02.unoGame.testUnitaire.player.strategy;

import java.util.Iterator;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.DrawEffectModel;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

public class PriorityColorStrategyModelTest {

    @Test
    public void test() {
        ComputerPlayerModel computerPlayer = new ComputerPlayerModel("Computer");
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.ZERO, ColorModel.BLUE, (byte)0, new CompositeEffectModel()));
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.EIGHT, ColorModel.BLUE, (byte)8, new CompositeEffectModel()));
        
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, new CompositeEffectModel()));

        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.SEVEN, ColorModel.GREEN, (byte)6, new CompositeEffectModel()));
        
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.NINE, ColorModel.YELLOW, (byte)9, new CompositeEffectModel()));
        CompositeEffectModel wildDrawEffect = new CompositeEffectModel();
        wildDrawEffect.addEffect(new DrawEffectModel());
        
        computerPlayer.getPlayerHand().addCard( new CardModel(SymbolModel.NINE, ColorModel.YELLOW, (byte)9, new CompositeEffectModel()));

        
        
        Iterator<CardModel> iter =  computerPlayer.getPlayerHand().getCards().iterator();
        
        while( iter.hasNext() ) {
            System.out.println(iter.next().getCard());
        }
        //computerPlayer.getStrategy(0);
        
    }

}
