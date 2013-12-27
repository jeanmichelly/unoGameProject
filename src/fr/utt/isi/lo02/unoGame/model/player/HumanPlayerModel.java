package fr.utt.isi.lo02.unoGame.model.player;

import java.io.Serializable;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;

/**
 * Représente les joueurs humains. Elle requiert donc une présence humaine pour effectuer les actions de jeu.
 * @see PlayerModel
 */
public class HumanPlayerModel extends PlayerModel implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construit un joueur humain 
     * @param pseudonym represente le pseudo du joueur
     */
    public HumanPlayerModel (String pseudonym) {
        super(pseudonym);
    }
    
    /**
     * Permet au joueur humain de jouer.
     */
    public void play () throws InvalidActionPickCardException, InvalidColorModelException { 
        ConsolePlayerHandView.ConsolePlayerHandController.playHumanPlayerModel();
    }
    
    /**
     * Permet au joueur humain de choisir une couleur
     */
    public void chooseColor () { 
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers(new String("CC"));
    }

    /**
     * Permet au joueur humain de contester si un autre joueur a posé une carte +4 contre lui
     * @throws InvalidActionPickCardException
     * @throws InvalidColorModelException
     */
    public void challengeAgainstWildDrawFourCard () throws  InvalidActionPickCardException, 
                                                            InvalidColorModelException {
        
        ConsolePlayerHandView.ConsolePlayerHandController.challengeAgainstWildDrawFourCard();
    }
    
}
