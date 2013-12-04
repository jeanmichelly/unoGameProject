package model.card;

import java.util.Iterator;

import model.BoardModel;
import model.card.effect.*;
import model.deck.DiscardPileModel;

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
		return symbol;
	}

	public ColorModel getColor () {
		return color;
	}

	public byte getScore () {
		return score;
	}
	
	public CompositeEffectModel getCompositeEffects () {
	    return compositeEffects;
	}
	
	public String getCard() {
        return getSymbol()+" "+getColor()+" "+getScore()+" "+getEffects();
	}

	public String getEffects () {
	    Iterator<ComponentEffectModel> iter = compositeEffects.getEffects().iterator();
	    ComponentEffectModel effect;
	    String effects="";
	    while( iter.hasNext() ) {
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