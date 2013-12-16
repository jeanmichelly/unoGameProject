package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.EmptyStackException;
import java.util.Stack;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;

/**
 * 
 * Cette classe represente le talon du jeu.
 *
 */
public class DiscardPileModel extends DeckModel<Stack<CardModel>> {

    private static DiscardPileModel uniqueInstance = null;
    private boolean applyEffectLastCard;
    private ColorModel colorBeforeWildDrawFour; 

    private DiscardPileModel () {
        super.cards = new Stack<CardModel>();
        try {
            this.push(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
    }

    public static DiscardPileModel getUniqueInstance () {
        if ( uniqueInstance == null ) {
            uniqueInstance = new DiscardPileModel();
        }
        return uniqueInstance;
    }

    public CardModel push (CardModel card) {
        this.applyEffectLastCard = false;
        try {
            if ( this.peek().getCompositeEffects().containsWildEffect() ) // Reinitialise les couleurs des cartes joker
                this.peek().setColor(null);
        } catch (EmptyStackException e){ }
        if ( card.getSymbol() == SymbolModel.WILD_DRAW_FOUR )
            colorBeforeWildDrawFour = this.peek().getColor();
        
        return super.cards.push(card);
    }

    public CardModel peek () {
        return super.cards.peek();
    }
    
    public CardModel pop () { // Public pour reprendre la carte si un poseur de +4 a bluffé
        return super.cards.pop();
    }
    
    public Stack<CardModel> reshuffled () {
        CardModel topCard = this.pop();
        Stack<CardModel> remainingCards = super.cards; 
        super.cards = new Stack<CardModel>();
        super.add(topCard);

        return remainingCards;
    }
    
    public boolean hasApplyEffectLastCard () {
        return this.applyEffectLastCard;
    }
    
    public ColorModel getColorBeforeWildDrawFour () {
        return this.colorBeforeWildDrawFour;
    }

    public void setApplyEffectLastCard (boolean applyEffectLastCard) {
        this.applyEffectLastCard = applyEffectLastCard;
    }
        
}