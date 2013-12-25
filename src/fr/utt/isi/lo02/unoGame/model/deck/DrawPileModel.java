package fr.utt.isi.lo02.unoGame.model.deck;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * <u>Pattern Singleton : </u> </br> 
 * Représente la pioche du jeu par une pile de cartes. Elle n'est instanciable qu'une seule fois.
 * @see DeckModel
 */
public class DrawPileModel extends DeckModel<Stack<CardModel>> implements Serializable {
     
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Rend la pioche instanciable une seule fois 
     */
    private static DrawPileModel uniqueInstance = null;

    /**
     * Construit l'unique pioche et ne peut être appelé qu'une seule fois. 
     * Lors de la création de la pioche, on construit toute les cartes du jeu puis on les mélange.
     */
    private DrawPileModel () {
        super.cards = new Stack<CardModel>();

        this.initCardsAppearsOneTimeWithSymbolAndColor();
        this.initCardsAppearsTwoTimeWithSymbolAndColor();
        this.initCardsAppearsOneTimeWithSymbolAndWithoutColor();

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
     * Génération des cartes presentes 1 fois avec symbole et couleur (0)
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
     * Génération des cartes présentes 2 fois avec symbole et couleur(Tout sauf 0, joker, +4)
     */
    private void initCardsAppearsTwoTimeWithSymbolAndColor () {
        int i = 0;
        
        for ( int j=0; j<2; j++, i=0 ) {
            for ( SymbolModel symbol : GameRulesModel.SYMBOLS2 ) {
                for ( ColorModel color : GameRulesModel.COLORS ) {
                    CompositeEffectModel effects;
                    switch ( symbol ) {
                        case REVERSE : 
                            effects = new CompositeEffectModel(CompositeEffectModel.getReverseEffect());
                            break;
                        case DRAW_TWO :
                            effects = new CompositeEffectModel(CompositeEffectModel.getDrawTwoEffect());
                            break;
                        case SKIP :
                            effects = new CompositeEffectModel(CompositeEffectModel.getSkipEffect());
                            break;
                        default:
                            effects = new CompositeEffectModel();
                            break;
                    }
                    this.push(new CardModel(symbol, color, GameRulesModel.SCORES2[i], effects));
                }
                i++;
            }
        }
    }
    
    /**
     * Génération des cartes présentes 4 fois avec symbole et sans couleur (joker, +4)
     */
    private void initCardsAppearsOneTimeWithSymbolAndWithoutColor () {
        int i = 0;
        
        for ( SymbolModel symbol : GameRulesModel.SYMBOLS4 ) {
            for ( int j=0; j<4; j++ ) {
                CompositeEffectModel effects;
                switch ( symbol ) {
                    case WILD_DRAW_FOUR :
                        effects = new CompositeEffectModel(CompositeEffectModel.getWildDrawFourEffect());
                        break;
                    case WILD :
                        effects = new CompositeEffectModel(CompositeEffectModel.getWildEffect());
                        break;
                    default:
                        effects = new CompositeEffectModel();
                        break;
                }
                this.push(new CardModel(symbol, null, GameRulesModel.SCORES4[i], effects));
            }
            i++;
        }
    }
        
    /**
     * Permet le remélange de la pioche lorsqu'elle n'a plus assez de carte
     */
    public void reshuffled () {
        super.addCards(DiscardPileModel.getUniqueInstance().reshuffled());
        super.shuffle();
    }

    /**
     * Ajoute une carte à la pioche
     * @param card carte à ajouter à la pioche
     * @return la carte ajouté à la pioche
     */
    private CardModel push (CardModel card) {
        return super.cards.push(card);
    }
        
    /**
     * Ajoute un ensemble de cartes à la pioche
     * @param cards ensemble de carte à ajouter à la pioche
     * @return true si les ajouts des cartes ont bien aboutie, false sinon
     */
    public boolean addCards (ArrayList<CardModel> cards) {
        return super.cards.addAll(cards);
    }

    /**
     * Retire la carte présente sur le sommet de la pioche
     * S'il n'y a pas assez de carte, on remélange la pioche grâce au talon.
     * S'il n'y a pas assez de carte suite au remélange avec le talon, on ignore en capturant l'exception
     * @return la carte présente sur le sommet de la pioche
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
    
    public void loadDrawPileModel () {
        uniqueInstance = BoardModel.getUniqueInstance().getDrawPile();
    }
    
}