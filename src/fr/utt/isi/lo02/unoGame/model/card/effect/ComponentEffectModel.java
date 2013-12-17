package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * Represente le modele des effets de bas niveau
 */
public abstract class ComponentEffectModel {
    
    /**
     * La priorite d'un effet doit etre defini car l'execution d'un composite d'effet peut changer en fonction de l'ordre des effets
     * @see CompositeEffectModel
     */
    protected byte priority;
    
    /**
     * Initialiser un effet de bas niveau
     * @param priority definit la priorite d'un effet
     */
    protected ComponentEffectModel (byte priority) {
        this.priority = priority;
    }

    /**
     * Obtenir la priorite affecte a un effet de bas niveau
     * @return la priorite effecte a un effet de bas niveau
     */
    public byte getPriority () {
    	    return this.priority;
    }
    
    /**
     * Appliquer l'effet
     * @throws InvalidActionPickCardException
     * @throws InvalidColorModelException
     */
    protected abstract void applyEffect () throws InvalidActionPickCardException, InvalidColorModelException;
    
}