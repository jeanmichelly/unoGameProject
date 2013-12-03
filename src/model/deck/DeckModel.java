package model.deck;

import java.util.Collections;
import java.util.List;

import model.card.CardModel;

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
