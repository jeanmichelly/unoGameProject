package testUnitaire.deck;

import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Stack;
import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.CompositeEffectModel;
import model.card.effect.DrawEffectModel;
import model.card.effect.ReverseEffectModel;
import model.card.effect.SkipEffectModel;
import model.card.effect.WildEffectModel;
import model.deck.DrawPileModel;

import org.junit.Test;

public class DrawPileModelTest {

    @Test
    public void DrawPileModelTest () {

        final int NUMBER_CARDS = 108;

        CompositeEffectModel emptyEffects = new CompositeEffectModel();    

        CompositeEffectModel reverseEffects = new CompositeEffectModel();
            reverseEffects.addEffect(new ReverseEffectModel());

        CompositeEffectModel skipEffects = new CompositeEffectModel();
            skipEffects.addEffect(new SkipEffectModel());

        CompositeEffectModel drawTwoEffects = new CompositeEffectModel();
            drawTwoEffects.addEffect(new DrawEffectModel(), 2);   
            drawTwoEffects.addEffect(new SkipEffectModel());     
            drawTwoEffects.sortEffectsByPriority();

        CompositeEffectModel wildEffects = new CompositeEffectModel();
            wildEffects.addEffect(new WildEffectModel());                

        CompositeEffectModel wildDrawFourEffects = new CompositeEffectModel();
            wildDrawFourEffects.addEffect(new DrawEffectModel(), 4);
            wildDrawFourEffects.addEffect(new SkipEffectModel());
            wildDrawFourEffects.addEffect(new WildEffectModel()); 
            wildDrawFourEffects.sortEffectsByPriority();
        

        // k: Symbole/Couleur - v: Carte reelle correspondante
        HashMap<String, CardModel> expectedCardsDrawPile = new HashMap<String, CardModel>();
        // k: Symbole/Couleur - v: Nombre d'occurence de la carte dans la pioche
        HashMap<String, Integer> expectedOccCardsDrawPile = new HashMap<String, Integer>();


        // Le symbole ZERO est present 1 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("ZERO/YELLOW", new CardModel(SymbolModel.ZERO, ColorModel.YELLOW, (byte)0, emptyEffects));
        expectedOccCardsDrawPile.put("ZERO/YELLOW", 1);
        
        expectedCardsDrawPile.put("ZERO/GREEN", new CardModel(SymbolModel.ZERO, ColorModel.GREEN, (byte)0, emptyEffects));
        expectedOccCardsDrawPile.put("ZERO/GREEN", 1);
           
        expectedCardsDrawPile.put("ZERO/RED", new CardModel(SymbolModel.ZERO, ColorModel.RED, (byte)0, emptyEffects));
        expectedOccCardsDrawPile.put("ZERO/RED", 1);

        expectedCardsDrawPile.put("ZERO/BLUE", new CardModel(SymbolModel.ZERO, ColorModel.BLUE, (byte)0, emptyEffects));
        expectedOccCardsDrawPile.put("ZERO/BLUE", 1);


        // Le symbole ONE est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("ONE/YELLOW", new CardModel(SymbolModel.ONE, ColorModel.YELLOW, (byte)1, emptyEffects));
        expectedOccCardsDrawPile.put("ONE/YELLOW", 2);
        
        expectedCardsDrawPile.put("ONE/GREEN", new CardModel(SymbolModel.ONE, ColorModel.GREEN, (byte)1, emptyEffects));
        expectedOccCardsDrawPile.put("ONE/GREEN", 2);

        expectedCardsDrawPile.put("ONE/RED", new CardModel(SymbolModel.ONE, ColorModel.RED, (byte)1, emptyEffects));
        expectedOccCardsDrawPile.put("ONE/RED", 2);

        expectedCardsDrawPile.put("ONE/BLUE", new CardModel(SymbolModel.ONE, ColorModel.BLUE, (byte)1, emptyEffects));
        expectedOccCardsDrawPile.put("ONE/BLUE", 2);


        // Le symbole TWO est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("TWO/YELLOW", new CardModel(SymbolModel.TWO, ColorModel.YELLOW, (byte)2, emptyEffects));
        expectedOccCardsDrawPile.put("TWO/YELLOW", 2);
        
        expectedCardsDrawPile.put("TWO/GREEN", new CardModel(SymbolModel.TWO, ColorModel.GREEN, (byte)2, emptyEffects));
        expectedOccCardsDrawPile.put("TWO/GREEN", 2);

        expectedCardsDrawPile.put("TWO/RED", new CardModel(SymbolModel.TWO, ColorModel.RED, (byte)2, emptyEffects));
        expectedOccCardsDrawPile.put("TWO/RED", 2);

        expectedCardsDrawPile.put("TWO/BLUE", new CardModel(SymbolModel.TWO, ColorModel.BLUE, (byte)2, emptyEffects));
        expectedOccCardsDrawPile.put("TWO/BLUE", 2);


        // Le symbole THREE est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("THREE/YELLOW", new CardModel(SymbolModel.THREE, ColorModel.YELLOW, (byte)3, emptyEffects));
        expectedOccCardsDrawPile.put("THREE/YELLOW", 2);
        
        expectedCardsDrawPile.put("THREE/GREEN", new CardModel(SymbolModel.THREE, ColorModel.GREEN, (byte)3, emptyEffects));
        expectedOccCardsDrawPile.put("THREE/GREEN", 2);

        expectedCardsDrawPile.put("THREE/RED", new CardModel(SymbolModel.THREE, ColorModel.RED, (byte)3, emptyEffects));
        expectedOccCardsDrawPile.put("THREE/RED", 2);

        expectedCardsDrawPile.put("THREE/BLUE", new CardModel(SymbolModel.THREE, ColorModel.BLUE, (byte)3, emptyEffects));
        expectedOccCardsDrawPile.put("THREE/BLUE", 2);


        // Le symbole FOUR est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("FOUR/YELLOW", new CardModel(SymbolModel.FOUR, ColorModel.YELLOW, (byte)4, emptyEffects));
        expectedOccCardsDrawPile.put("FOUR/YELLOW", 2);
        
        expectedCardsDrawPile.put("FOUR/GREEN", new CardModel(SymbolModel.FOUR, ColorModel.GREEN, (byte)4, emptyEffects));
        expectedOccCardsDrawPile.put("FOUR/GREEN", 2);

        expectedCardsDrawPile.put("FOUR/RED", new CardModel(SymbolModel.FOUR, ColorModel.RED, (byte)4, emptyEffects));
        expectedOccCardsDrawPile.put("FOUR/RED", 2);

        expectedCardsDrawPile.put("FOUR/BLUE", new CardModel(SymbolModel.FOUR, ColorModel.BLUE, (byte)4, emptyEffects));
        expectedOccCardsDrawPile.put("FOUR/BLUE", 2);


        // Le symbole FIVE est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("FIVE/YELLOW", new CardModel(SymbolModel.FIVE, ColorModel.YELLOW, (byte)5, emptyEffects));
        expectedOccCardsDrawPile.put("FIVE/YELLOW", 2);
        
        expectedCardsDrawPile.put("FIVE/GREEN", new CardModel(SymbolModel.FIVE, ColorModel.GREEN, (byte)5, emptyEffects));
        expectedOccCardsDrawPile.put("FIVE/GREEN", 2);

        expectedCardsDrawPile.put("FIVE/RED", new CardModel(SymbolModel.FIVE, ColorModel.RED, (byte)5, emptyEffects));
        expectedOccCardsDrawPile.put("FIVE/RED", 2);

        expectedCardsDrawPile.put("FIVE/BLUE", new CardModel(SymbolModel.FIVE, ColorModel.BLUE, (byte)5, emptyEffects));
        expectedOccCardsDrawPile.put("FIVE/BLUE", 2);


        // Le symbole SIX est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("SIX/YELLOW", new CardModel(SymbolModel.SIX, ColorModel.YELLOW, (byte)6, emptyEffects));
        expectedOccCardsDrawPile.put("SIX/YELLOW", 2);
        
        expectedCardsDrawPile.put("SIX/GREEN", new CardModel(SymbolModel.SIX, ColorModel.GREEN, (byte)6, emptyEffects));
        expectedOccCardsDrawPile.put("SIX/GREEN", 2);

        expectedCardsDrawPile.put("SIX/RED", new CardModel(SymbolModel.SIX, ColorModel.RED, (byte)6, emptyEffects));
        expectedOccCardsDrawPile.put("SIX/RED", 2);

        expectedCardsDrawPile.put("SIX/BLUE", new CardModel(SymbolModel.SIX, ColorModel.BLUE, (byte)6, emptyEffects));
        expectedOccCardsDrawPile.put("SIX/BLUE", 2);


        // Le symbole SEVEN est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("SEVEN/YELLOW", new CardModel(SymbolModel.SEVEN, ColorModel.YELLOW, (byte)7, emptyEffects));
        expectedOccCardsDrawPile.put("SEVEN/YELLOW", 2);
        
        expectedCardsDrawPile.put("SEVEN/GREEN", new CardModel(SymbolModel.SEVEN, ColorModel.GREEN, (byte)7, emptyEffects));
        expectedOccCardsDrawPile.put("SEVEN/GREEN", 2);

        expectedCardsDrawPile.put("SEVEN/RED", new CardModel(SymbolModel.SEVEN, ColorModel.RED, (byte)7, emptyEffects));
        expectedOccCardsDrawPile.put("SEVEN/RED", 2);

        expectedCardsDrawPile.put("SEVEN/BLUE", new CardModel(SymbolModel.SEVEN, ColorModel.BLUE, (byte)7, emptyEffects));
        expectedOccCardsDrawPile.put("SEVEN/BLUE", 2);


        // Le symbole EIGHT est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("EIGHT/YELLOW", new CardModel(SymbolModel.EIGHT, ColorModel.YELLOW, (byte)8, emptyEffects));
        expectedOccCardsDrawPile.put("EIGHT/YELLOW", 2);
        
        expectedCardsDrawPile.put("EIGHT/GREEN", new CardModel(SymbolModel.EIGHT, ColorModel.GREEN, (byte)8, emptyEffects));
        expectedOccCardsDrawPile.put("EIGHT/GREEN", 2);

        expectedCardsDrawPile.put("EIGHT/RED", new CardModel(SymbolModel.EIGHT, ColorModel.RED, (byte)8, emptyEffects));
        expectedOccCardsDrawPile.put("EIGHT/RED", 2);

        expectedCardsDrawPile.put("EIGHT/BLUE", new CardModel(SymbolModel.EIGHT, ColorModel.BLUE, (byte)8, emptyEffects));
        expectedOccCardsDrawPile.put("EIGHT/BLUE", 2);


        // Le symbole NINE est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("NINE/YELLOW", new CardModel(SymbolModel.NINE, ColorModel.YELLOW, (byte)9, emptyEffects));
        expectedOccCardsDrawPile.put("NINE/YELLOW", 2);
        
        expectedCardsDrawPile.put("NINE/GREEN", new CardModel(SymbolModel.NINE, ColorModel.GREEN, (byte)9, emptyEffects));
        expectedOccCardsDrawPile.put("NINE/GREEN", 2);

        expectedCardsDrawPile.put("NINE/RED", new CardModel(SymbolModel.NINE, ColorModel.RED, (byte)9, emptyEffects));
        expectedOccCardsDrawPile.put("NINE/RED", 2);

        expectedCardsDrawPile.put("NINE/BLUE", new CardModel(SymbolModel.NINE, ColorModel.BLUE, (byte)9, emptyEffects));
        expectedOccCardsDrawPile.put("NINE/BLUE", 2);
        

        // Le symbole DRAW_TWO est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("DRAW_TWO/YELLOW", new CardModel(SymbolModel.DRAW_TWO, ColorModel.YELLOW, (byte)20, drawTwoEffects));
        expectedOccCardsDrawPile.put("DRAW_TWO/YELLOW", 2);
        
        expectedCardsDrawPile.put("DRAW_TWO/GREEN", new CardModel(SymbolModel.DRAW_TWO, ColorModel.GREEN, (byte)20, drawTwoEffects));
        expectedOccCardsDrawPile.put("DRAW_TWO/GREEN", 2);

        expectedCardsDrawPile.put("DRAW_TWO/RED", new CardModel(SymbolModel.DRAW_TWO, ColorModel.RED, (byte)20, drawTwoEffects));
        expectedOccCardsDrawPile.put("DRAW_TWO/RED", 2);

        expectedCardsDrawPile.put("DRAW_TWO/BLUE", new CardModel(SymbolModel.DRAW_TWO, ColorModel.BLUE, (byte)20, drawTwoEffects));
        expectedOccCardsDrawPile.put("DRAW_TWO/BLUE", 2);  


        // Le symbole SKIP est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("SKIP/YELLOW", new CardModel(SymbolModel.SKIP, ColorModel.YELLOW, (byte)20, skipEffects));
        expectedOccCardsDrawPile.put("SKIP/YELLOW", 2);
        
        expectedCardsDrawPile.put("SKIP/GREEN", new CardModel(SymbolModel.SKIP, ColorModel.GREEN, (byte)20, skipEffects));
        expectedOccCardsDrawPile.put("SKIP/GREEN", 2);

        expectedCardsDrawPile.put("SKIP/RED", new CardModel(SymbolModel.SKIP, ColorModel.RED, (byte)20, skipEffects));
        expectedOccCardsDrawPile.put("SKIP/RED", 2);

        expectedCardsDrawPile.put("SKIP/BLUE", new CardModel(SymbolModel.SKIP, ColorModel.BLUE, (byte)20, skipEffects));
        expectedOccCardsDrawPile.put("SKIP/BLUE", 2); 


        // Le symbole REVERSE est present 2 fois pour chaque couleur (jaune, vert, rouge, bleu)
        expectedCardsDrawPile.put("REVERSE/YELLOW", new CardModel(SymbolModel.REVERSE, ColorModel.YELLOW, (byte)20, reverseEffects));
        expectedOccCardsDrawPile.put("REVERSE/YELLOW", 2);
        
        expectedCardsDrawPile.put("REVERSE/GREEN", new CardModel(SymbolModel.REVERSE, ColorModel.GREEN, (byte)20, reverseEffects));
        expectedOccCardsDrawPile.put("REVERSE/GREEN", 2);

        expectedCardsDrawPile.put("REVERSE/RED", new CardModel(SymbolModel.REVERSE, ColorModel.RED, (byte)20, reverseEffects));
        expectedOccCardsDrawPile.put("REVERSE/RED", 2);

        expectedCardsDrawPile.put("REVERSE/BLUE", new CardModel(SymbolModel.REVERSE, ColorModel.BLUE, (byte)20, reverseEffects));
        expectedOccCardsDrawPile.put("REVERSE/BLUE", 2);


        // Le symbole WILD est present 4 fois mais n'ayant pas de couleur
        expectedCardsDrawPile.put("WILD/null", new CardModel(SymbolModel.WILD, null, (byte)50, wildEffects));
        expectedOccCardsDrawPile.put("WILD/null", 4);
        
        expectedCardsDrawPile.put("WILD/null", new CardModel(SymbolModel.WILD, null, (byte)50, wildEffects));
        expectedOccCardsDrawPile.put("WILD/null", 4);

        expectedCardsDrawPile.put("WILD/null", new CardModel(SymbolModel.WILD, null, (byte)50, wildEffects));
        expectedOccCardsDrawPile.put("WILD/null", 4);

        expectedCardsDrawPile.put("WILD/null", new CardModel(SymbolModel.WILD, null, (byte)50, wildEffects));
        expectedOccCardsDrawPile.put("WILD/null", 4);


        // Le symbole WILD_DRAW_FOUR est present 4 fois mais n'ayant pas de couleur
        expectedCardsDrawPile.put("WILD_DRAW_FOUR/null", new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, wildDrawFourEffects));
        expectedOccCardsDrawPile.put("WILD_DRAW_FOUR/null", 4);
        
        expectedCardsDrawPile.put("WILD_DRAW_FOUR/null", new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, wildDrawFourEffects));
        expectedOccCardsDrawPile.put("WILD_DRAW_FOUR/null", 4);

        expectedCardsDrawPile.put("WILD_DRAW_FOUR/null", new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, wildDrawFourEffects));
        expectedOccCardsDrawPile.put("WILD_DRAW_FOUR/null", 4);

        expectedCardsDrawPile.put("WILD_DRAW_FOUR/null", new CardModel(SymbolModel.WILD_DRAW_FOUR, null, (byte)50, wildDrawFourEffects));
        expectedOccCardsDrawPile.put("WILD_DRAW_FOUR/null", 4);
        
        
        DrawPileModel drawPile = DrawPileModel.getUniqueInstance();
        Stack<CardModel> cardsDrawPile = drawPile.getCards();
    
        assertEquals(NUMBER_CARDS, cardsDrawPile.size());
        
        HashMap<String, CardModel> realCardsDrawPile = new HashMap<String, CardModel>();
        HashMap<String, Integer> realOccCardsDrawPile = new HashMap<String, Integer>();
        String keyCard;
        
        for ( CardModel card : cardsDrawPile ) {
            keyCard = card.getSymbol()+"/"+card.getColor();
            realCardsDrawPile.put(keyCard, card);
            if ( !realOccCardsDrawPile.containsKey(keyCard) ) {
                realOccCardsDrawPile.put(keyCard, 0);
            }    
            realOccCardsDrawPile.put(keyCard, realOccCardsDrawPile.get(keyCard)+1);
        }
        
        assertEquals(expectedCardsDrawPile.size(), realCardsDrawPile.size());
        assertEquals(expectedOccCardsDrawPile.size(), realOccCardsDrawPile.size());
        
        for ( String key : expectedCardsDrawPile.keySet() ) {
            assertEquals(true, realCardsDrawPile.get(key).equals(expectedCardsDrawPile.get(key))); 
            assertEquals(true, realOccCardsDrawPile.get(key).equals(expectedOccCardsDrawPile.get(key)));
        }  
    }
    
}
