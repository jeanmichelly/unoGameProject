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
        return cards.add(card);
    }
    
    public boolean addAll (T cards) {
        return this.cards.addAll(cards);
    }
    
    public int size () {
        return cards.size();
    }
    
    public void shuffle () {
        Collections.shuffle(cards);
    }
    
    public boolean isEmpty () {
        return cards.isEmpty();
    }

    public T getCards () {
        return cards;       
    }
    
}
