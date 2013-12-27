package fr.utt.isi.lo02.unoGame.model.card;

import java.io.Serializable;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.card.effect.ComponentEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.WildEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;

/**
 * Permet de representer les cartes du jeu. Une carte est caracterisée par : 
 * <ul>
 *      <li>Un symbole</li>
 *      <li>Une couleur</li>
 *      <li>Un score</li>
 *      <li>Un ensemble d'effet qui peut être vide</li>
 * </ul>
 * @see GameRulesModel
 * @see CompositeEffectModel
 */
public class CardModel implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private SymbolModel symbol;
	private ColorModel color;
	private byte score;
	private CompositeEffectModel compositeEffects;

	/**
	 * Construit une carte du jeu
	 * @param symbol identifie la carte avec un symbole
	 * @param color identifie la carte avec une couleur
	 * @param score identifie la carte avec un score
	 * @param effects identifie la carte avec des effets ou pas
	 */
	public CardModel (SymbolModel symbol, ColorModel color, byte score, CompositeEffectModel effects) {
	    this.symbol = symbol;
	    this.color = color;
	    this.score = score;
	    this.compositeEffects = effects;
	}
	
	/**
	 * Indique si la carte est jouable par rapport au talon
	 * @see DiscardPileModel
	 * @return true si la carte est jouable, false sinon
	 */
	public boolean isPlayableCard () {
        if ( this.color==null || this.symbol.equals(DiscardPileModel.getUniqueInstance().peek().getSymbol()) 
                || this.color.equals(DiscardPileModel.getUniqueInstance().peek().getColor()) )
            return true;
        return false;
    }
    
	/**
	 * Indique si la carte est jouable par rapport à une carte en particulier
	 * @param card comparer par rapport à une carte en particulier
	 * @return true si la carte est jouable, false sinon
	 */
    public boolean isPlayableCard (CardModel card) {
        if ( this.color==null || this.symbol.equals(card.getSymbol()) || this.color.equals(card.getColor()) )
            return true;
        return false;
    }
    
    public boolean hasWildEffect () {
        for ( ComponentEffectModel effect : compositeEffects.getEffects() )
            if ( effect instanceof WildEffectModel )
                return true;
        return false;
    }

    /**
     * Indique si la carte est égale à une carte en particulier
     * @param card comparer par rapport à une carte en particulier
     * @return true si la carte est jouable, false sinon
     */
    public boolean equals (CardModel card) {
        if ( this.symbol == card.getSymbol() && this.color == card.getColor() && ((Byte)this.score).equals((Byte)card.getScore()) 
                && this.compositeEffects.getEffects().size() == card.getCompositeEffects().getEffects().size() ) {
          	Iterator<ComponentEffectModel> thisIter = this.compositeEffects.getEffects().iterator();
          	Iterator<ComponentEffectModel> cardIter = card.getCompositeEffects().getEffects().iterator();
          	
          	while ( thisIter.hasNext() )
              	if ( !(thisIter.next().getPriority() == cardIter.next().getPriority()) )
                  	return false;
            return true;
        }   
        return false;
    }

    /**
     * Informe sur le symbole caractérisant la carte
     * @return le symbole caracterisant la carte
     */
	public SymbolModel getSymbol () {
		return this.symbol;
	}

	/**
     * Informe sur la couleur caractérisant la carte
     * @return la couleur caractérisant la carte
     */
	public ColorModel getColor () {
		return this.color;
	}

	/**
     * Informe sur le score caractérisant la carte
     * @return le score caractérisant la carte
     */
	public byte getScore () {
		return this.score;
	}
	
	/**
     * Informe sur l'effet caractérisant la carte (liste d'effet de bas niveau)
     * @return l'effet caractérisant la carte (liste d'effet de bas niveau)
     */
	public CompositeEffectModel getCompositeEffects () {
	    return this.compositeEffects;
	}
	
	/**
	 * Représente la description de l'ensemble de la carte sous une chaine de caractère
	 * (Symbol Couleur Score Effet)
	 * @return une chaine de caractere représentant la description de l'ensemble de la carte
	 */
	public String getCard () {
        return this.getSymbol()+" "+this.getColor()+" "+this.getScore()+" "+this.getEffects();
	}

	/**
	 * Représente la description de l'ensemble des effets de bas niveau d'un effet d'une carte du jeu
	 * @return une chaine de caractere représentant la description de l'effet
	 */
	public String getEffects () {
	    Iterator<ComponentEffectModel> iter = this.compositeEffects.getEffects().iterator();
	    ComponentEffectModel effect;
	    String effects="";
	    while ( iter.hasNext() ) {
	        effect = iter.next();
	        effects += effect.getClass().getSimpleName()+" "+effect.getPriority();
	    }
	    return effects;
	}
	
	/**
	 * Modifier le symbole caractérisant la carte
	 * @param symbol symbole caractérisant la carte
	 */
	public void setSymbol (SymbolModel symbol) {
	    this.symbol = symbol;
	}
	
	/**
	 * Modifier la couleur caractérisant la carte
	 * @param color couleur caractérisant la carte
	 */
	public void setColor (ColorModel color) {
	    this.color = color;
	}
    
}