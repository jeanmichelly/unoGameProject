package model.deck;

import model.card.CardModel;
import model.card.ColorModel;
import model.card.SymbolModel;
import model.card.effect.*;
import model.gameRules.GameRulesModel;

import java.util.Stack;

public class DrawPileModel extends DeckModel<Stack<CardModel>> {
	
	private static DrawPileModel uniqueInstance = null;

	private DrawPileModel () {
		super.cards = new Stack<CardModel>();

		// Generation des cards presentes 1 fois avec symbole et couleur (0)
		int i = 0;
		for ( SymbolModel symbol : GameRulesModel.SYMBOLS1 ) {
		    for ( ColorModel color : GameRulesModel.COLORS ) {
                CompositeEffectModel effects = new CompositeEffectModel();
		        push(new CardModel(symbol, color, GameRulesModel.SCORES1[i], effects));
		    }
		    i++;
		}
		
		i = 0;
		// Generation des cards presentes 2 fois avec symbole et couleur(Tout sauf 0, joker, +4)
		for ( int j=0; j<2; j++, i=0 ) {
		    for ( SymbolModel symbol : GameRulesModel.SYMBOLS2 ) {
		        for ( ColorModel color : GameRulesModel.COLORS ) {
	                CompositeEffectModel effects = new CompositeEffectModel();
	                switch ( symbol ) {
	                    case REVERSE : 
	                        effects.addEffect(new ReverseEffectModel());
	                        break;
	                    case DRAW_TWO :
	                        effects.addEffect(new DrawEffectModel(), 2);
	                    case SKIP :
	                        effects.addEffect(new SkipEffectModel());
	                        break;
	                    default:
	                        break;
	                }
		            push(new CardModel(symbol, color, GameRulesModel.SCORES2[i], effects));
		        }
		        i++;
		    }
		}

		i = 0;
		// Generation des cartes presentes 4 fois avec symbole et sans couleur(joker, +4)
		for ( SymbolModel symbol : GameRulesModel.SYMBOLS4 ) {
			for ( int j=0; j<4; j++ ) {
                CompositeEffectModel effects = new CompositeEffectModel();
                switch ( symbol ) {
                    case WILD_DRAW_FOUR :
                        effects.addEffect(new DrawEffectModel(), 4);
                        effects.addEffect(new SkipEffectModel());
                    case WILD :
                        effects.addEffect(new WildEffectModel());
                        break;
                    default:
                        break;
                }
				push(new CardModel(symbol, null, GameRulesModel.SCORES4[i], effects));
			}
			i++;
		}
		
		// Etape IMPORTANTE : permet de classer les effets par ordre de priorité : deux effets consécutif ( s peuvent avoir un impact dif ( férent si il sont effectués dans un ordre dif ( férent
		for ( CardModel card : super.getCards() ) {
		    card.getCompositeEffects().sortEffectsByPriority();
		}
		
		super.shuffle();

		/* Test */
		// Iterator<CardModel> iter = super.cards.iterator();
		// while ( iter.hasNext() ) {
		//     CardModel card = iter.next();
		// 	System.out.println(card.getSymbol()+" "+card.getColor()+" "+card.getScore()+" "+card.getEffects());
		// }
	}

	public static DrawPileModel getUniqueInstance () {
	    if ( uniqueInstance == null ) {
	        uniqueInstance = new DrawPileModel();
	    }
	    return uniqueInstance;
	}

	private CardModel push (CardModel card) {
		 return super.cards.push(card);
	}

	public CardModel pop () {
		return super.cards.pop();
	}

	public void reshuffled (Stack<CardModel> cardsReshuffledOfDiscardPile) {
	    super.addAll(cardsReshuffledOfDiscardPile);
	    super.shuffle();
	}
	
}
