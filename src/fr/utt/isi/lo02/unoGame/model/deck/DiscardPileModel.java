package fr.utt.isi.lo02.unoGame.model.deck;

import java.util.EmptyStackException;
import java.util.Stack;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;

/**
 * <u>Pattern Singleton : </u> </br> 
 * Représente le talon du jeu par une pile de cartes dont le sommet est visible. 
 * Elle n'est instanciable qu'une seule fois.
 * @see DeckModel
 */
public class DiscardPileModel extends DeckModel<Stack<CardModel>> {

    /**
     * Rend le talon instanciable une seule fois 
     */
    private static DiscardPileModel uniqueInstance = null;
    
    /**
     * Informe sur l'état de l'effet de la carte du sommet, s'il a deja été appliqué. 
     * Cela permet d'éviter d'appliquer plusieurs fois un effet pour une même carte qui reste sur le sommet à plusieurs tour.  
     */
    private boolean applyEffectLastCard;
    
    /**
     * Informe sur la couleur de la carte du sommet avant qu'une carte +4 a ete jouee.
     * Cela permet la gestion d'une contestation d'une carte +4. 
     */
    private ColorModel colorBeforeWildDrawFour; 

    /**
     * Construit l'unique talon et ne peut être appelé qu'une seule fois. 
     * Lors de la creation du talon, on recupère une carte de la pioche
     */
    private DiscardPileModel () {
        super.cards = new Stack<CardModel>();
        try {
            super.cards.push(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtenir l'instance unique du talon
     * @return l'unique talon
     */
    public static DiscardPileModel getUniqueInstance () {
        if ( uniqueInstance == null ) {
            uniqueInstance = new DiscardPileModel();
        }
        return uniqueInstance;
    }

    /**
     * Ajoute une carte au talon.
     * Initialise l'état de l'effet pour indiquer qu'il n'a pas encore ete appliqué.
     * Si la carte precedente au sommet de cet ajout a l'effet joker, on réinitialise la couleur à null.
     * Si la carte posée est une carte +4 alors on stock l'information de la couleur de la carte précédente 
     * pour prévoir une éventuelle contestation.
     * @param card carte à ajouter au paquet
     * @return la carte ajoutée dans le talon
     */
    public CardModel push (CardModel card) {
        this.applyEffectLastCard = false;
        try {
            if ( this.peek().getCompositeEffects().containsWildEffect() ) // Reinitialise les couleurs des cartes joker
                this.peek().setColor(null);
        } catch (EmptyStackException e){ }
        if ( card.getSymbol() == SymbolModel.WILD_DRAW_FOUR ) {
            System.out.println(this.peek());
            colorBeforeWildDrawFour = this.peek().getColor();
        }
        
        return super.cards.push(card);
    }

    /**
     * Informe sur la carte au sommet du talon
     * @return la carte au sommet du talon
     */
    public CardModel peek () {
        return super.cards.peek();
    }
    
    /**
     * Retire la carte presente sur le sommet du talon
     * @return la carte presente sur le sommet du talon
     */
    public CardModel pop () { // Public pour reprendre la carte si un poseur de +4 a bluffé
        return super.cards.pop();
    }
    
    /**
     * Permet le remélange de la pioche en conservant son sommet
     * @return le reste des cartes présente en dessous du sommet
     */
    public Stack<CardModel> reshuffled () { // Conserve uniquement son sommet
        CardModel topCard = this.pop(); 
        Stack<CardModel> remainingCards = super.cards; 
        super.cards = new Stack<CardModel>();
        super.addCard(topCard);

        return remainingCards;
    }
    
    /**
     * Informe sur l'état de l'effet de la carte du sommet, s'il a deja été appliqué. 
     * @return true si l'effet a deja été appliqué, false sinon
     */
    public boolean hasApplyEffectLastCard () {
        return this.applyEffectLastCard;
    }
    
    /**
     * Obtenir la couleur de la carte du sommet avant qu'une carte +4 a été jouée 
     * @return la couleur de la carte du sommet avant qu'une carte +4 a été jouée
     */
    public ColorModel getColorBeforeWildDrawFour () {
        return this.colorBeforeWildDrawFour;
    }

    /**
     * Modifie l'état de l'effet de la carte du sommet, s'il a deja été appliquée
     * @param applyEffectLastCard 
     *      état de l'effet de la carte du sommet, s'il a deja été appliqué, true s'il a deja été appliqué, false sinon
     */
    public void setApplyEffectLastCard (boolean applyEffectLastCard) {
        this.applyEffectLastCard = applyEffectLastCard;
    }
        
}