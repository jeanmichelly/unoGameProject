package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * Représente le modèle des effets de bas niveau
 */
public abstract class ComponentEffectModel {
    
    /**
     * La priorité d'un effet doit être defini car l'exécution d'un composite d'effet peut changer en fonction de l'ordre des effets
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
     * Obtenir la priorité affecté à un effet de bas niveau
     * @return la priorité affecté à un effet de bas niveau
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