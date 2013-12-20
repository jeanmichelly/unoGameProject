package fr.utt.isi.lo02.unoGame.model.card.effect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * <u>Pattern Composite/Strategy : </u> </br>
 * Permet d'avoir des effets du jeu correspondant à un ensemble d'effet dit de bas niveau. 
 * Par exemple l'effet d'une carte +4 est composée de draw*4, skip et wild.
 * De ce fait, la manipulation des cartes peut se faire de manière très flexible 
 * pour éventuellement prévoir un maximum d'extension possible.
 * Cela permet également d'avoir des effets pour les pénalités.
 * @see DrawEffectModel
 * @see ReverseEffectModel
 * @see SkipEffectModel
 * @see WildEffectModel
 */
public class CompositeEffectModel extends ComponentEffectModel {
    
    /**
     * Liste d'effet correspondant à un effet du jeu officiel
     */
    private static ArrayList<ComponentEffectModel> wildDrawFourEffect = new ArrayList<ComponentEffectModel>();
    private static ArrayList<ComponentEffectModel> wildEffect = new ArrayList<ComponentEffectModel>();
    private static ArrayList<ComponentEffectModel> drawTwoEffect = new ArrayList<ComponentEffectModel>();
    private static ArrayList<ComponentEffectModel> skipEffect = new ArrayList<ComponentEffectModel>();
    private static ArrayList<ComponentEffectModel> reverseEffect = new ArrayList<ComponentEffectModel>();

    /**
     * Liste d'effet 
     */
	private ArrayList<ComponentEffectModel> effects;
		
	/**
	 * Initialise l'ensemble à vide et qui contiendra les effets de bas niveau
	 */
	public CompositeEffectModel () {
	    super((byte)-1);
		this.effects = new ArrayList<ComponentEffectModel>();
	}
	
    public CompositeEffectModel (ArrayList<ComponentEffectModel> effects) {
        super((byte)-1);
        this.effects = effects;
    }
	
	private static void initWildDrawFourEffect() {
	    CompositeEffectModel.wildDrawFourEffect.add(new WildEffectModel());
	    CompositeEffectModel.wildDrawFourEffect.add(new DrawEffectModel());
	    CompositeEffectModel.wildDrawFourEffect.add(new DrawEffectModel());
	    CompositeEffectModel.wildDrawFourEffect.add(new DrawEffectModel());
	    CompositeEffectModel.wildDrawFourEffect.add(new DrawEffectModel());
	    CompositeEffectModel.wildDrawFourEffect.add(new SkipEffectModel());
	}
	
	private static void initWildEffect() {
        CompositeEffectModel.wildEffect.add(new WildEffectModel());
    }
	
	private static void initDrawTwoEffect () {
        CompositeEffectModel.drawTwoEffect.add(new DrawEffectModel());
        CompositeEffectModel.drawTwoEffect.add(new DrawEffectModel());
        CompositeEffectModel.drawTwoEffect.add(new SkipEffectModel());
    }
	
	private static void initSkipEffect () {
        CompositeEffectModel.skipEffect.add(new SkipEffectModel());
    }
	
	private static void initReverseEffect () {
        CompositeEffectModel.reverseEffect.add(new ReverseEffectModel());
    }
	
	public static void initGameEffect () {
	    initWildDrawFourEffect();
	    initWildEffect();
	    initDrawTwoEffect();
	    initSkipEffect();
	    initReverseEffect();
	}

	/**
	 * Applique l'effet d'une carte du jeu
	 */
    public void applyEffect () throws InvalidActionPickCardException, InvalidColorModelException {
        Iterator<ComponentEffectModel> iter = this.effects.iterator();
        while ( iter.hasNext() )
            iter.next().applyEffect();
    }
    
    /**
     * Applique l'effet d'une pénalité du jeu à un joueur en particulier
     * @param index indice du joueur sur lequel l'effet d'une pénalité va s'appliquer 
     * @throws InvalidActionPickCardException
     */
    public void applyEffect (int index) throws InvalidActionPickCardException {
        Iterator<ComponentEffectModel> iter = this.effects.iterator();
        while ( iter.hasNext() )
            ((DrawEffectModel)iter.next()).applyEffect(index);
    }

    /**
     * Ajoute un effet de bas niveau au composite
     * @param effect effet de bas niveau
     * @return true si l'ajout de l'effet a bien aboutie, false sinon
     */
    public boolean addEffect (ComponentEffectModel effect) {
    	    return this.effects.add(effect);
    }
    
    /**
     * Ajoute un effet de bas niveau selon un facteur au composite
     * @param effect effet de bas niveau
     * @param factor determine le nombre d'effet à ajouter
     * @return true si les ajouts de l'effet ont bien aboutie, false sinon
     */
    public boolean addEffect (ComponentEffectModel effect, int factor) {
        boolean response = true;
        for ( int i=0; i<factor; i++ ) {
            response = this.addEffect(effect);               
            if ( !response )
                return response;
        }
        return response;
    }

    /**
     * Determine si le composite est constitué d'effet
     * @return true si le composite a des effets, false sinon
     */
    public boolean hasEffect () {
        return !this.effects.isEmpty();
    }
    
    /**
     * Informe si le composite contient l'effet de choisir une couleur
     * @return true si le composite contient l'effet de choisir une couleur, false sinon
     */
    public boolean containsWildEffect () {
        Iterator<ComponentEffectModel> iter = this.effects.iterator();
        while ( iter.hasNext() ) {
            if ( iter.next() instanceof WildEffectModel )
                return true;
        }
        return false;
    }
    
    /**
     * Trie les effets de bas niveau par ordre de priorité
     */
    public void sortEffectsByPriority () {
        Collections.sort(this.effects, new Comparator<ComponentEffectModel>() {
            @Override
            public int compare (ComponentEffectModel o1, ComponentEffectModel o2) {
                return o2.getPriority() - o1.getPriority();
            }
        });
    }
    
    /**
     * Informe sur le nombre d'effet de bas niveau que contient le composite
     * @return le nombre d'effet de bas niveau que contient le composite
     */
    public int numberEffects () {
        return this.effects.size();
    }
    
    public boolean isWildDrawFourEffect () {
        Iterator<ComponentEffectModel> wildDrawFourEffectIter = wildDrawFourEffect.iterator();
        Iterator<ComponentEffectModel> thisIter = this.getEffects().iterator();

        if ( !thisIter.hasNext() || wildDrawFourEffect.size() != this.getEffects().size() )
            return false;
        
        while ( thisIter.hasNext() )
            if ( !(thisIter.next().getPriority() == wildDrawFourEffectIter.next().getPriority()) )
                return false;
        return true;
    }
    
    /**
     * Obtenir le composite 
     * @return la liste constituée des effets de bas niveau
     */
    public ArrayList<ComponentEffectModel> getEffects () {
        return this.effects;
    }
    
    public static ArrayList<ComponentEffectModel> getWildDrawFourEffect () {
        return CompositeEffectModel.wildDrawFourEffect;
    }
    
    public static ArrayList<ComponentEffectModel> getWildEffect () {
        return CompositeEffectModel.wildEffect;
    }
    
    public static ArrayList<ComponentEffectModel> getDrawTwoEffect () {
        return CompositeEffectModel.drawTwoEffect;
    }
    
    public static ArrayList<ComponentEffectModel> getSkipEffect () {
        return CompositeEffectModel.skipEffect;
    }
    
    public static ArrayList<ComponentEffectModel> getReverseEffect () {
        return CompositeEffectModel.reverseEffect;
    }
    
}
