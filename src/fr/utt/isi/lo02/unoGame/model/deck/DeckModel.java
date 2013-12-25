package fr.utt.isi.lo02.unoGame.model.deck;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;

/**
 * Représente ce que contient un paquet de carte. 
 * Un paquet de carte peut être le talon, la pioche ou les mains des joueurs
 * @param <T> specifie le type de paquet de cartes qui peut être un stack ou un arrayList pour éviter de caster
 * @see fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel
 * @see DrawPileModel
 * @see PlayerHandModel
 */
public abstract class DeckModel <T extends List<CardModel>> implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Ensemble de carte correspondant au paquet
     */
    T cards;

    /**
     * Ajoute une carte au paquet
     * @param card carte à ajouter au paquet
     * @return true si l'ajout de la carte a bien aboutie, false sinon
     */
    public boolean addCard (CardModel card) {
        return this.cards.add(card);
    }
    
    /**
     * Ajoute un ensemble de carte au paquet
     * @param cards ensemble de carte à ajouter au paquet
     * @return true si les ajouts des cartes ont bien aboutie, false sinon
     */
    public boolean addCards (T cards) {
        return this.cards.addAll(cards);
    }
    
    /**
     * Vide le paquet de carte
     */
    public void clear () {
        this.cards.clear();
    }
    
    /**
     * Informe sur le nombre de carte contenu dans le paquet
     * @return le nombre de carte contenu dans le paquet
     */
    public int numberCards () {
        return this.cards.size();
    }
    
    /**
     * Mélange le paquet de carte
     */
    public void shuffle () {
        Collections.shuffle(this.cards);
    }
    
    /**
     * Informe sur l'état (vide ou pas) du paquet
     * @return true si le paquet ne contient pas de carte, false sinon
     */
    public boolean hasNotCard () {
        return this.cards.isEmpty();
    }

    /**
     * Obtenir les cartes du paquet
     * @return les cartes du paquet
     */
    public T getCards () {
        return this.cards;       
    }
    
}
