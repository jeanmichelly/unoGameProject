package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;

/**
 * 
 * Cette classe represente les mains des joueurs.
 *
 */
public class PlayerHandModel extends DeckModel<ArrayList<CardModel>> {
    
    public PlayerHandModel () {
        super.cards = new ArrayList<CardModel>();
    }
    
    public boolean hasPlayableCard () {
        Iterator<CardModel> iter = super.cards.iterator();
        while ( iter.hasNext() )
            if ( iter.next().isPlayableCard() )
                return true;
        return false;
    }

    public CardModel remove (int index) {
    	    return super.cards.remove(index);
    }
    
    public CardModel get (int index) {
        return super.cards.get(index);
    }
    
}
