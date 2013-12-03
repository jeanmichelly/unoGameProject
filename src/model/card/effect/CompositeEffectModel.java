package model.card.effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CompositeEffectModel extends ComponentEffectModel {

	private ArrayList<ComponentEffectModel> effects;

	public CompositeEffectModel () {
	    super((byte)-1);
		effects = new ArrayList<ComponentEffectModel>();
	}

    @Override
    public void applyEffect () {
        for (ComponentEffectModel effect : effects) {
            effect.applyEffect();
        }
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
