package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
 
/**
 * Correspond a l'une des stratégies que peut utiliser un joueur ordinateur.
 * Elle permet de jouer en priorité les cartes jouables avec des nombres avec la couleur la plus fréquente.
 */
public class PriorityNumberStrategyModel extends StrategyModel {

    /**
     * Exécute la stratégie qui donne la priorité aux nombres
     * @throws InvalidActionPutDownCardException 
     */
    public boolean execute () throws InvalidActionPutDownCardException {   
        CardModel playableNumberCard = getPlayableNumberCardWithColorFrequentHighest();
        
        if ( playableNumberCard == null )
            return false;
        
        int indexPlayingCard = super.researchIndexPlayingCard (playableNumberCard);
        if ( indexPlayingCard == -1 )
            throw new InvalidActionPutDownCardException();
        
        BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard); 
        return true;
    }
    
    private CardModel getPlayableNumberCardWithColorFrequentHighest () {
        Iterator<CardModel> iterPlayerHand = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards().iterator();
        ArrayList<CardModel> playableNumberCards = new ArrayList<CardModel>();
        byte [] countColor = new byte [4];

        while ( iterPlayerHand.hasNext() ) {
            CardModel card = iterPlayerHand.next();
            if ( card.getColor() != null ) // Compte le nombre de carte par couleur
                countColor[card.getColor().getId()]++;
            
            // Stock les cartes avec le même symbole nombre que le talon 
            if ( card.getSymbol() == DiscardPileModel.getUniqueInstance().peek().getSymbol() 
                    && !card.getCompositeEffects().hasEffect() )
                playableNumberCards.add(card);
        }
        
        Iterator<CardModel> iterPlayableNumberCards = playableNumberCards.iterator();
        byte maxColorCardsForNumber = 0;
        CardModel playableNumberCard = null;
        
        // Recupère la carte symbole nombre où il y a le plus de carte couleur
        while ( iterPlayableNumberCards.hasNext() ) {
            CardModel c = iterPlayableNumberCards.next();
            if ( countColor[c.getColor().getId()] > maxColorCardsForNumber ) {
                maxColorCardsForNumber = countColor[c.getColor().getId()]; 
                playableNumberCard = c;
            }
        }
        
        if ( playableNumberCards.size() != 0 )
            return playableNumberCard;
        
        return null;
    }
    
}
