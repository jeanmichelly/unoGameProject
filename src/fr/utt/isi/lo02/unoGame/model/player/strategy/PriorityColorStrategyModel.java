package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.io.Serializable;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * Correspond à l'une des stratégies que peut utiliser un joueur ordinateur.
 * Elle permet de jouer en priorité les cartes avec une couleur jouables avec le score le plus élevé.
 */
public class PriorityColorStrategyModel extends StrategyModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exécute la stratégie qui donne la priorité aux couleurs
     */
    public boolean execute () throws InvalidActionPutDownCardException {
        CardModel playableColorCard = getPlayableColorCardWithScoreHighestLevel();
        
        if ( playableColorCard == null )
            return false;
        
        int indexPlayingCard = super.researchIndexPlayingCard (playableColorCard);
        if ( indexPlayingCard == -1 )
            throw new InvalidActionPutDownCardException();
        
        BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard);
        return true;
    } 
    
    private CardModel getPlayableColorCardWithScoreHighestLevel () {
        Iterator<CardModel> iter = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards().iterator();
        CardModel playableColorCard = new CardModel(null, null, (byte) -1, null);
        
        while ( iter.hasNext() ) {
            CardModel card = iter.next();
            if ( card.getColor() != null && card.getColor() == DiscardPileModel.getUniqueInstance().peek().getColor() 
                    && playableColorCard.getScore() < card.getScore() ) {
                playableColorCard = card;
            }      
        }
        
        if ( playableColorCard.getScore() != -1 ) // S'il existe au moins 1 carte de la meme couleur
            return playableColorCard;
        
        return null;
    }
    
}
