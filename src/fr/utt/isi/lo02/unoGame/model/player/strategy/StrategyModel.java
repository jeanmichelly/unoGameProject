package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * 
 * Cette interface a pour but d'utiliser le modele patron strategy pour permettre la gestion du moteur d'un joueur ordinateur.  
 *
 */
public abstract class StrategyModel {

    public abstract void execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException;
    
    protected int researchIndexPlayingCard (CardModel choiceCard) {
        int i = 0; 
        Iterator<CardModel> iter = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards().iterator();
        while ( iter.hasNext() ) {
            if ( iter.next().equals(choiceCard) ) {
                return i;
            }
            i++;
        }    
        return -1;
    }
    
}
