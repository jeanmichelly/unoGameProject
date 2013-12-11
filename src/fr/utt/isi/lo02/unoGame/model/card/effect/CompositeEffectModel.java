package fr.utt.isi.lo02.unoGame.model.card.effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;

/**
 * 
 * Cette classe permet d'avoir un ensemble d'effet. Elle concerne par exemple une carte +4 composee de draw*4, skip et wild.
 *
 */
public class CompositeEffectModel extends ComponentEffectModel {

	private ArrayList<ComponentEffectModel> effects;

	public CompositeEffectModel () {
	    super((byte)-1);
		effects = new ArrayList<ComponentEffectModel>();
	}

    @Override
    public void applyEffect () throws InvalidActionPickCardException {
        Iterator<ComponentEffectModel> iter = effects.iterator();
        while (iter.hasNext())
            iter.next().applyEffect();
    }

    public boolean addEffect (ComponentEffectModel effect) {
    	    return effects.add(effect);
    }
    
    public boolean addEffect (ComponentEffectModel effect, int factor) {
        boolean response = true;
        for ( int i=0; i<factor; i++ ) {
            response = addEffect(effect);               
            if ( !response )
                return response;
        }
        return response;
    }

    public boolean hasEffect () {
        return !effects.isEmpty();
    }
    
    public boolean containsWildEffect () {
        Iterator<ComponentEffectModel> iter = effects.iterator();
        while (iter.hasNext()) {
            if ( iter.next() instanceof WildEffectModel )
                return true;
        }
        return false;
    }
    
    public void sortEffectsByPriority () {
        Collections.sort(this.effects, new Comparator<ComponentEffectModel>() {
            @Override
            public int compare (ComponentEffectModel o1, ComponentEffectModel o2) {
                return o2.getPriority() - o1.getPriority();
            }
        });
    }
    
    public ArrayList<ComponentEffectModel> getEffects () {
        return effects;
    }
    
}
