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
 * <u>Pattern Singleton : </u> </br> 
 * Represente la pioche du jeu par une pile de carte. Elle n'est instanciable qu'une seule fois.
 */
public class DrawPileModel extends DeckModel<Stack<CardModel>> {
     
    /**
     * Rends la pioche instanciable une seule fois 
     */
    private static DrawPileModel uniqueInstance = null;

    /**
     * Construit l'unique pioche et ne peut etre appele qu'une seule fois. 
     * Lors de la creation de la pioche, on construit toute les cartes du jeu puis on les melange.
     */
    private DrawPileModel () {
        super.cards = new Stack<CardModel>();

        this.initCardsAppearsOneTimeWithSymbolAndColor();
        this.initCardsAppearsTwoTimeWithSymbolAndColor();
        this.initCardsAppearsOneTimeWithSymbolAndWithoutColor();
        this.push(new CardModel(SymbolModel.WILD_DRAW_FOUR, null, new Integer("0").byteValue(), new CompositeEffectModel()));

        super.shuffle();
    }

    /**
     * Obtenir l'instance unique de la pioche
     * @return l'unique pioche
     */
    public static DrawPileModel getUniqueInstance () {
        if ( uniqueInstance == null )
            uniqueInstance = new DrawPileModel();
        
        return uniqueInstance;
    }
        
    /**
     * Generation des cartes presentes 1 fois avec symbole et couleur (0)
     */
    private void initCardsAppearsOneTimeWithSymbolAndColor () { 
        int i = 0;
        
        for ( SymbolModel symbol : GameRulesModel.SYMBOLS1 ) {
            for ( ColorModel color : GameRulesModel.COLORS ) {
                this.push(new CardModel(symbol, color, GameRulesModel.SCORES1[i], new CompositeEffectModel()));
            }
            i++;
        }
    }
    /**
     * Generation des cartes presentes 2 fois avec symbole et couleur(Tout sauf 0, joker, +4)
     */
    private void initCardsAppearsTwoTimeWithSymbolAndColor () {
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
                            effects.sortEffectsByPriority(); // permet de classer les effets par ordre de priorite : deux effets consecutifs peuvent avoir un impact different si il sont effectuees dans un ordre different
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
    
    /**
     * Generation des cartes presentes 4 fois avec symbole et sans couleur (joker, +4)
     */
    private void initCardsAppearsOneTimeWithSymbolAndWithoutColor () {
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
                        effects.sortEffectsByPriority(); // permet de classer les effets par ordre de priorite : deux effets consecutifs peuvent avoir un impact different si il sont effectuees dans un ordre different
                        break;
                    default:
                        break;
                }
                this.push(new CardModel(symbol, null, GameRulesModel.SCORES4[i], effects));
            }
            i++;
        }
    }
        
    /**
     * Permet le remelange de la pioche lorsqu'elle n'a plus assez de carte
     */
    public void reshuffled () {
        super.addCards(DiscardPileModel.getUniqueInstance().reshuffled());
        super.shuffle();
    }

    /**
     * Ajoute une carte a la pioche
     * @param card carte a ajouter a la pioche
     * @return la carte ajoute a la pioche
     */
    private CardModel push (CardModel card) {
        return super.cards.push(card);
    }
        
    /**
     * Ajoute un ensemble de carte a la pioche
     * @param cards ensemble de carte a ajouter a la pioche
     * @return true si les ajouts des cartes ont bien aboutie, false sinon
     */
    public boolean addCards (ArrayList<CardModel> cards) {
        return super.cards.addAll(cards);
    }

    /**
     * Retire la carte presente sur le sommet de la pioche
     * S'il n'y a pas assez de carte, on remelange la pioche grace au talon.
     * S'il n'y a pas assez de carte suite au remelange avec le talon, on ignore en capturant l'exception
     * @return la carte presente sur le sommet de la pioche
     */
    public CardModel pop () throws DrawPileIsEmptyAfterReshuffledException {
        if ( super.numberCards() == 1 ) {
            ConsoleBoardView.update("Plus de carte dans la pioche, on doit alors remelanger grace au talon");
            reshuffled();
            if ( super.numberCards() == 1 )
                throw new DrawPileIsEmptyAfterReshuffledException();
        }
        
        return super.cards.pop();
    }
    
}