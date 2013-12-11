package fr.utt.isi.lo02.unoGame.model.deck;

<<<<<<< HEAD
import java.util.EmptyStackException;
import java.util.Stack;

=======
>>>>>>> 04eac78af5ac873baaf07a474726a975474bb914
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;

<<<<<<< HEAD
/**
 * 
 * Cette classe represente le talon du jeu.
 *
 */
=======
import java.util.Stack;

>>>>>>> 04eac78af5ac873baaf07a474726a975474bb914
public class DiscardPileModel extends DeckModel<Stack<CardModel>> {

	private static DiscardPileModel uniqueInstance = null;
	private boolean applyEffectLastCard;

	private DiscardPileModel () {
		super.cards = new Stack<CardModel>();
		try {
            push(DrawPileModel.getUniqueInstance().pop());
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
	    applyEffectLastCard = false;
	    try {
	        if ( peek().getCompositeEffects().containsWildEffect() )
	            this.peek().setColor(null);
	    } catch (EmptyStackException e){ }
		return super.cards.push(card);
	}

	public CardModel peek () {
        return super.cards.peek();
	}
	
	private CardModel pop () {
	    return super.cards.pop();
	}
	
<<<<<<< HEAD
	public Stack<CardModel> reshuffled () {
	    CardModel topCard = pop();
	    Stack<CardModel> restCards = super.cards; 
	    super.cards = new Stack<CardModel>();
	    super.add(topCard);

	    return restCards;
=======
	public Stack<CardModel> reshuffle () {
        Stack<CardModel> remainingCards = new Stack<CardModel>();
        remainingCards.addAll((Stack<CardModel>)super.cards);
        CardModel topCard = pop();
        super.cards.clear();
        super.cards.add(topCard);
	    return remainingCards;
>>>>>>> 04eac78af5ac873baaf07a474726a975474bb914
	}
	
	public boolean hasApplyEffectLastCard () {
	    return this.applyEffectLastCard;
	}

	public void setApplyEffectLastCard (boolean applyEffectLastCard) {
		this.applyEffectLastCard = applyEffectLastCard;
	}
	
}