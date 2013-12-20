package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

/**
 * 
 * Correspond a l'une des strategies que peut utiliser un joueur ordinateur. 
 * Elle permet, par exemple, de jouer agressif lorsqu'il reste peu de carte au prochain joueur. 
 * Elle passe en priorite les cartes avec des effets.
 */
public class PrioritySpecialityStrategyModel extends StrategyModel {

    /**
     * Execute la strategie qui donne la priorite aux cartes avec des effets
     * @throws InvalidActionPutDownCardException 
     */
    public boolean execute () throws InvalidActionPutDownCardException { 
        CardModel playableSpecialityCard = getPlayableSpecialityCard(); 
        
        if ( playableSpecialityCard == null )
            return false;
        
        int indexPlayingCard = super.researchIndexPlayingCard (playableSpecialityCard);
        if ( indexPlayingCard == -1 )
            throw new InvalidActionPutDownCardException();
        
        BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard); 
        return true;
    }
    
    private CardModel getPlayableSpecialityCard () {
        Iterator<CardModel> iterPlayerHand = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards().iterator();
        ArrayList<CardModel> playableSpecialityCards = new ArrayList<CardModel>();
        
        while ( iterPlayerHand.hasNext() ) {
            CardModel card = iterPlayerHand.next();
            
            // Donne la priorite aux cartes +2 
            if ( card.isPlayableCard() && card.getCompositeEffects().numberEffects() == 3 )
                return card;
            else if ( card.isPlayableCard() && card.getCompositeEffects().hasEffect() ) {
                playableSpecialityCards.add(card);
            }
        }
        
        int sizePlayableSpecialityCards = playableSpecialityCards.size();
        
        if ( sizePlayableSpecialityCards == 0 )
            return null;

        return playableSpecialityCards.get((int)(Math.random() * (sizePlayableSpecialityCards)));
    }
        
}
