package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.ArrayList;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

/**
 * Correspond a l'une des strategies que peut utiliser un joueur ordinateur. Elle n'a pas de specifite particuliere pour effectuer une action.
 * Elle permet de jouer n'importe quelle carte jouable ou ne pas jouer avec la meme probabilite. 
 */
public class RandomStrategyModel extends StrategyModel {

    /**
     * Execute la strategie qui joue aleatoirement
     */
    public void execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        ArrayList<CardModel> playableCards = ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getPlayableCards();
        int sizePlayableCards = playableCards.size();
        int random = (int)(Math.random() * (sizePlayableCards+1));
        
        if ( random == sizePlayableCards && sizePlayableCards > 3 ) {
            BoardModel.getUniqueInstance().getPlayer().notToPlay();
        } else {
            if ( random == sizePlayableCards )
                random--;
            int indexPlayingCard = super.researchIndexPlayingCard (playableCards.get(random));
            if ( indexPlayingCard == -1 )
                throw new InvalidActionPutDownCardException();
            BoardModel.getUniqueInstance().getPlayer().putDownCard(indexPlayingCard);
        }
    }
    
}
