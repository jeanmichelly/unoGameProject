package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.Collections;
import java.util.List;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;

/**
 * 
 * Cette classe represente globalement ce que contient un paquet de carte.
 *
 * @param <T>
 */
public abstract class DeckModel <T extends List<CardModel>> {
    
    T cards;

    public boolean add (CardModel card) {
        return this.cards.add(card);
    }
    
    public boolean addAll (T cards) {
        return this.cards.addAll(cards);
    }
    
    public void clear () {
        this.cards.clear();
    }
    
    public int size () {
        return this.cards.size();
    }
    
    public void shuffle () {
        Collections.shuffle(this.cards);
    }
    
    public boolean isEmpty () {
        return this.cards.isEmpty();
    }

    public T getCards () {
        return this.cards;       
    }
    
}
