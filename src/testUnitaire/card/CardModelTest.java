package testUnitaire.card;

import static org.junit.Assert.*;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.*;
import org.junit.Test;

public class CardModelTest {

    @Test
    public void equalsTest() {
        CompositeEffectModel effects1 = new CompositeEffectModel();
        effects1.addEffect(new DrawEffectModel(), 4);
        effects1.addEffect(new SkipEffectModel());
        effects1.addEffect(new WildEffectModel());
        CardModel c1 = new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, effects1);
        
        CompositeEffectModel effects2 = new CompositeEffectModel();
        effects2.addEffect(new DrawEffectModel(), 4);
        effects2.addEffect(new SkipEffectModel());
        effects2.addEffect(new WildEffectModel());
        CardModel c2 = new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, effects2);
        
        assertEquals(true, c1.equals(c2));     
        c2.getCompositeEffects().getEffects().remove(5);
        assertEquals(false, c1.equals(c2));     
        c2.getCompositeEffects().getEffects().add(new WildEffectModel());
        c2.getCompositeEffects().getEffects().add(new WildEffectModel());
        assertEquals(false, c1.equals(c2));
        c2.getCompositeEffects().getEffects().remove(5);
        c2.setColor(ColorModel.BLUE);
        assertEquals(false, c1.equals(c2));     
        c2.setColor(null);
        c2.setSymbol(SymbolModel.DRAW_TWO);
        assertEquals(false, c1.equals(c2));
    }
    
    @Test
    public void hasPlayableCardTest() {
        CompositeEffectModel effects1 = new CompositeEffectModel();
        effects1.addEffect(new DrawEffectModel(), 2);
        effects1.addEffect(new SkipEffectModel());
        CardModel c1 = new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, effects1);
        
        CompositeEffectModel effects2 = new CompositeEffectModel();
        effects2.addEffect(new DrawEffectModel(), 2);
        effects2.addEffect(new SkipEffectModel());
        CardModel c2 = new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, effects2);
        
        assertEquals(true, c1.isPlayableCard(c2));
        
        c2.setSymbol(SymbolModel.REVERSE);
        assertEquals(true, c1.isPlayableCard(c2));
        
        c2.setColor(ColorModel.GREEN);
        assertEquals(false, c1.isPlayableCard(c2));
        
        c2.setSymbol(SymbolModel.DRAW_TWO);
        assertEquals(true, c1.isPlayableCard(c2));
        
        c2.setColor(null);
        assertEquals(true, c1.isPlayableCard(c2));
    }

}
