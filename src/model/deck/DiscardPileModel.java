package model.deck;

import java.util.Stack;
import model.card.CardModel;

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
	
	public Stack<CardModel> reshuffled () {
	    CardModel topCard = pop();
	    Stack<CardModel> restCards = (Stack<CardModel>)super.cards; 
	    super.add(topCard);
	    return restCards;
	}
	
	public boolean hasApplyEffectLastCard () {
	    return applyEffectLastCard;
	}

	public void setApplyEffectLastCard (boolean applyEffectLastCard) {
		this.applyEffectLastCard = applyEffectLastCard;
	}
	
}
