package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.ArrayList;
import java.util.Stack;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.DrawEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.ReverseEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.SkipEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.WildEffectModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * 
 * Cette classe represente la pioche du jeu.
 *
 */
public class DrawPileModel extends DeckModel<Stack<CardModel>> {
        
    private static DrawPileModel uniqueInstance = null;

    private DrawPileModel () {
        super.cards = new Stack<CardModel>();

        this.initCardsAppearsOneTimeWithSymbolAndColor();
        this.initCardsAppearsTwoTimeWithSymbolAndColor();
        this.initCardsAppearsOneTimeWithSymbolAndWithoutColor();
        
        super.shuffle();
    }

    public static DrawPileModel getUniqueInstance () {
        if ( uniqueInstance == null )
            uniqueInstance = new DrawPileModel();
        
        return uniqueInstance;
    }
        
    private void initCardsAppearsOneTimeWithSymbolAndColor () { // Generation des cards presentes 1 fois avec symbole et couleur (0)
        int i = 0;
        
        for ( SymbolModel symbol : GameRulesModel.SYMBOLS1 ) {
            for ( ColorModel color : GameRulesModel.COLORS ) {
                this.push(new CardModel(symbol, color, GameRulesModel.SCORES1[i], new CompositeEffectModel()));
            }
            i++;
        }
    }
    
    private void initCardsAppearsTwoTimeWithSymbolAndColor () { // Generation des cards presentes 2 fois avec symbole et couleur(Tout sauf 0, joker, +4)
        int i = 0;
        
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
                            effects.sortEffectsByPriority(); // permet de classer les effets par ordre de priorit�� : deux effets cons��cutif ( s peuvent avoir un impact dif ( f��rent si il sont effectu��s dans un ordre dif ( f��rent
                            break;
                        default:
                            break;
                    }
                    this.push(new CardModel(symbol, color, GameRulesModel.SCORES2[i], effects));
                }
                i++;
            }
        }
    }
    
    private void initCardsAppearsOneTimeWithSymbolAndWithoutColor () { // Generation des cartes presentes 4 fois avec symbole et sans couleur(joker, +4)
        int i = 0;
        
        for ( SymbolModel symbol : GameRulesModel.SYMBOLS4 ) {
            for ( int j=0; j<4; j++ ) {
                CompositeEffectModel effects = new CompositeEffectModel();
                switch ( symbol ) {
                    case WILD_DRAW_FOUR :
                        effects.addEffect(new DrawEffectModel(), 4);
                        effects.addEffect(new SkipEffectModel());
                    case WILD :
                        effects.addEffect(new WildEffectModel());
                        effects.sortEffectsByPriority(); // permet de classer les effets par ordre de priorit�� : deux effets cons��cutif ( s peuvent avoir un impact dif ( f��rent si il sont effectu��s dans un ordre dif ( f��rent
                        break;
                    default:
                        break;
                }
                this.push(new CardModel(symbol, null, GameRulesModel.SCORES4[i], effects));
            }
            i++;
        }
    }
        
    public void reshuffled () {
        super.addAll(DiscardPileModel.getUniqueInstance().reshuffled());
        super.shuffle();
    }

    private CardModel push (CardModel card) {
        return super.cards.push(card);
    }
        
    public boolean addAll (ArrayList<CardModel> cards) {
        return super.cards.addAll(cards);
    }

    public CardModel pop () throws DrawPileIsEmptyAfterReshuffledException {
        if ( super.size() == 1 ) {
            ConsoleBoardView.update("Plus de carte dans la pioche, on doit alors remélanger grâce au talon");
            reshuffled();
            if ( super.size() == 1 )
                throw new DrawPileIsEmptyAfterReshuffledException();
        }
        
        return super.cards.pop();
    }
    
}