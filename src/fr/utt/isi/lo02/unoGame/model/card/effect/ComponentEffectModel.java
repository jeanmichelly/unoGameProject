package fr.utt.isi.lo02.unoGame.model.card.effect;

public abstract class ComponentEffectModel {
    
    protected byte priority;
    
    public ComponentEffectModel (byte priority) {
        this.priority = priority;
    }

    public byte getPriority () {
    	    return priority;
    }
    
    public abstract void applyEffect ();
    
}