package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;

/**
 * 
 * Cette classe utilise le patron composite pour permettre la gestion des effets d'une carte ou des penalites qu'il peut avoir au cours d'une partie de jeu.
 *
 */
public abstract class ComponentEffectModel {
    
    protected byte priority;
    
    public ComponentEffectModel (byte priority) {
        this.priority = priority;
    }

    public byte getPriority () {
    	    return priority;
    }
    
    public abstract void applyEffect () throws InvalidActionPickCardException;
    
}