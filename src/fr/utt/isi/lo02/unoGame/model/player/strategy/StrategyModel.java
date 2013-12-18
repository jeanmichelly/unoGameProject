package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * <u>Pattern Strategy : </u> </br>
 *  Definit le modele commun des strategies
 */
public abstract class StrategyModel {

    /**
     * Execute une strategie 
     * 
     * @return true si la strategie a pu faire une action :
     *  <ul>
     *      <li>Poser une carte jouable correspondant a la strategie</li>
     *      <li>Passer son tour</li>    
     *  </ul>, 
     *  false sinon.
     * @throws InvalidActionPickCardException
     * @throws InvalidActionPutDownCardException
     */
    public abstract boolean execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException;
    
    /**
     * Recherche l'indice d'une carte en particulier
     * @param choiceCard la carte que l'on souhaite obtenir son indice
     * @return l'indice de la carte souhaite
     */
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
