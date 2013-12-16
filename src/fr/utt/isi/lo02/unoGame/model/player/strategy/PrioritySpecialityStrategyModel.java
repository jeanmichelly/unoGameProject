package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.ArrayList;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

/**
 * 
 * Cette classe correspond a l'une des strategies que peut utiliser un joueur ordinateur. Elle permet, par exemple, de jouer agressif lorsqu'il reste peu de carte au joueur qui le suit. 
 * Elle passe en priorite les cartes avec des effets.
 *
 */
public class PrioritySpecialityStrategyModel extends StrategyModel {

    @Override
    public void execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException { 
        ArrayList<CardModel> playableCards = ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getPlayableCards();
        int sizePlayableCards = playableCards.size();
        int random = (int)(Math.random() * (sizePlayableCards+1));
        
        if ( random == sizePlayableCards && sizePlayableCards > 3 ) {
            BoardModel.getUniqueInstance().getPlayer().notToPlay();
        } else {
            if ( random == sizePlayableCards )
                random--;
            int indexPlayingCard = researchIndexPlayingCard (playableCards.get(random));
            if ( indexPlayingCard == -1 )
                throw new InvalidActionPutDownCardException();
            BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard);
        }
    }
    
}
