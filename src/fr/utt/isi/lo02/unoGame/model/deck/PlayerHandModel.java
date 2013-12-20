package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;

/**
 * Représente les mains des joueurs.
 * @see DeckModel
 */
public class PlayerHandModel extends DeckModel<ArrayList<CardModel>> {
    
    /**
     * Initialise la main du joueur 
     */
    public PlayerHandModel () {
        super.cards = new ArrayList<CardModel>();
    }
    
    /**
     * Informe sur la possibilité de jouer
     * @return true si la main du joueur a une carte jouable, false sinon
     */
    public boolean hasPlayableCard () {
        Iterator<CardModel> iter = super.cards.iterator();
        while ( iter.hasNext() )
            if ( iter.next().isPlayableCard() )
                return true;
        return false;
    }

    /**
     * Retire une carte de la main du joueur
     * @param index indice de la carte devant être retiré de la main du joueur
     * @return la carte retiré de la main du joueur
     */
    public CardModel removeCard (int index) {
    	    return super.cards.remove(index);
    }
    
    /**
     * Obtenir une carte en particulier
     * @param index indice de la carte souhaitée
     * @return la carte souhaitée
     */
    public CardModel getCard (int index) {
        return super.cards.get(index);
    }
    
}
