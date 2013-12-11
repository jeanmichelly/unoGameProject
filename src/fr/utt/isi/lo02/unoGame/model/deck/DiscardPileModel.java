package fr.utt.isi.lo02.unoGame.model.deck;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;

import java.util.Stack;

public class DiscardPileModel extends DeckModel<Stack<CardModel>> {

	private static DiscardPileModel uniqueInstance = null;
	private boolean applyEffectLastCard;

	private DiscardPileModel () {
		super.cards = new Stack<CardModel>();
		push(DrawPileModel.getUniqueInstance().pop());
	}

	public static DiscardPileModel getUniqueInstance () {
	    if ( uniqueInstance == null ) {
	        uniqueInstance = new DiscardPileModel();
	    }
	    return uniqueInstance;
	}

	public CardModel push (CardModel card) {
	    applyEffectLastCard = false;
		return super.cards.push(card);
	}

	public CardModel peek () {
        return super.cards.peek();
	}
	
	private CardModel pop () {
	    return super.cards.pop();
	}
	
	public Stack<CardModel> reshuffle () {
        Stack<CardModel> remainingCards = new Stack<CardModel>();
        remainingCards.addAll((Stack<CardModel>)super.cards);
        CardModel topCard = pop();
        super.cards.clear();
        super.cards.add(topCard);
	    return remainingCards;
	}
	
	public boolean hasApplyEffectLastCard () {
	    return applyEffectLastCard;
	}

	public void setApplyEffectLastCard (boolean applyEffectLastCard) {
		this.applyEffectLastCard = applyEffectLastCard;
	}
	
}