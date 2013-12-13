package fr.utt.isi.lo02.unoGame.model.card;

import java.util.Iterator;
import fr.utt.isi.lo02.unoGame.model.card.effect.ComponentEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;

/**
 * 
 * Cette classe permet de representer les cartes du jeu.
 *
 */
public class CardModel {
    
    private SymbolModel symbol;
	private ColorModel color;
	private byte score;
	private CompositeEffectModel compositeEffects;

	public CardModel (SymbolModel symbol, ColorModel color, byte score, CompositeEffectModel effects) {
	    this.symbol = symbol;
	    this.color = color;
	    this.score = score;
	    this.compositeEffects = effects;
	}
	
	public boolean isPlayableCard () {
        if ( this.color==null || this.symbol.equals(DiscardPileModel.getUniqueInstance().peek().getSymbol()) || this.color.equals(DiscardPileModel.getUniqueInstance().peek().getColor()) )
            return true;
        return false;
    }
    
    public boolean isPlayableCard (CardModel card) {
        if ( this.color==null || this.symbol.equals(card.getSymbol()) || this.color.equals(card.getColor()) )
            return true;
        return false;
    }

    public boolean equals (CardModel card) {
        if ( this.symbol == card.getSymbol() && this.color == card.getColor() && ((Byte)this.score).equals((Byte)card.getScore()) && this.compositeEffects.getEffects().size() == card.getCompositeEffects().getEffects().size() ) {
          	Iterator<ComponentEffectModel> thisIter = this.compositeEffects.getEffects().iterator();
          	Iterator<ComponentEffectModel> cardIter = card.getCompositeEffects().getEffects().iterator();
          	while ( thisIter.hasNext() )
              	if ( !(thisIter.next().getPriority() == cardIter.next().getPriority()) )
                  	return false;
            return true;
        }   
        return false;
    }

	public SymbolModel getSymbol () {
		return this.symbol;
	}

	public ColorModel getColor () {
		return this.color;
	}

	public byte getScore () {
		return this.score;
	}
	
	public CompositeEffectModel getCompositeEffects () {
	    return this.compositeEffects;
	}
	
	public String getCard () {
        return this.getSymbol()+" "+this.getColor()+" "+this.getScore()+" "+this.getEffects();
	}

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
	
	public void setSymbol (SymbolModel symbol) {
	    this.symbol = symbol;
	}
	
	public void setColor (ColorModel color) {
	    this.color = color;
	}
    
}