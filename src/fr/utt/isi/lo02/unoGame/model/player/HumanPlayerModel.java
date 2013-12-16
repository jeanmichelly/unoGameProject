package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;

/**
 * 
 * Cette classe represente les joueurs humains. Elle requiert donc une présence humaine pour effectuer les actions de jeu.
 *
 */
public class HumanPlayerModel extends PlayerModel {
    
    public HumanPlayerModel (String pseudonym) {
        super(pseudonym);
    }
    
    @Override
    public void play () throws InvalidActionPickCardException, InvalidColorModelException { // Changer pour interface
        ConsolePlayerHandView.ConsolePlayerHandController.playHumanPlayerModel();
    }
    
    @Override
    public void chooseColor () { // Changer pour interface (Afficher les couleurs à choisir)
        ConsolePlayerHandView.ConsolePlayerHandController.chooseColor();
    }

    @Override
    public void againstUno () throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().applyPenaltyAgainstUno();        
    }

    public void challengeAgainstWildDrawFourCard () {
        
    }
    
}
