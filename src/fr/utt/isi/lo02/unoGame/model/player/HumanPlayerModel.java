package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;

/**
 * 
 * Represente les joueurs humains. Elle requiert donc une presence humaine pour effectuer les actions de jeu.
 *
 */
public class HumanPlayerModel extends PlayerModel {
    
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
    public void chooseColor () { // Changer pour interface (Afficher les couleurs a choisir)
        ConsolePlayerHandView.ConsolePlayerHandController.chooseColor();
    }

    /**
     * Permet au joueur humain de contester si un autre joueur a pose une carte +4 contre lui
     * @throws InvalidActionPickCardException
     * @throws InvalidColorModelException
     */
    public void challengeAgainstWildDrawFourCard () throws  InvalidActionPickCardException, 
                                                            InvalidColorModelException {
        
        ConsolePlayerHandView.ConsolePlayerHandController.challengeAgainstWildDrawFourCard();
    }
    
}
