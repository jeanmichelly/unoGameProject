package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * Correspond à l'une des stratégies que peut utiliser un joueur ordinateur. 
 * Elle n'a pas de specifité particuliere pour effectuer une action.
 * Elle permet de jouer n'importe quelle carte jouable ou ne pas jouer avec la même probabilité. 
 */
public class RandomStrategyModel extends StrategyModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Exécute la stratégie qui joue aléatoirement
     */
    public boolean execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        ArrayList<CardModel> playableCards = getPlayableCards();
        int sizePlayableCards = playableCards.size();
        
        // Choisit une carte aléatoire parmi les cartes jouables
        int random = (int)(Math.random() * (sizePlayableCards+1)); 

        // Laisse une chance de passer son tour si le joueur d'après a plus de 3 cartes
        if ( random == sizePlayableCards && sizePlayableCards > 3 ) { 
            BoardModel.getUniqueInstance().getPlayer().notToPlay();
            DrawPileModel.getUniqueInstance().setDrawable(false);
        } else {
            if ( random == sizePlayableCards )
                random--;
            
            /*  Recherche l'indice de la carte choisit de la main du joueur par rapport à 
                l'ensemble des cartes jouables
            */
            int indexPlayingCard = super.researchIndexPlayingCard (playableCards.get(random));
            if ( indexPlayingCard == -1 )
                throw new InvalidActionPutDownCardException();
            BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard);
        }
        return true;
    }
    
    /**
     * Obtenir l'ensemble des cartes qui sont jouables.
     * @return les cartes qui sont jouables
     */
    private ArrayList<CardModel> getPlayableCards () {
        Iterator<CardModel> iter = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards().iterator();
        ArrayList<CardModel> playableCards = new ArrayList<CardModel>();
        while ( iter.hasNext() ) {
            CardModel card = iter.next();
            if ( card.isPlayableCard() )
                playableCards.add(card);
        }
        return playableCards;
    }
    
}
