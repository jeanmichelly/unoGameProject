package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.testeur.console.TesteurConsoleHumanPlayerModel;

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
    public void play () {
        TesteurConsoleHumanPlayerModel.play();
    }

    public void putDownCard () {
        TesteurConsoleHumanPlayerModel.putDownCard();
    }
    
    public void chooseColor () {
        TesteurConsoleHumanPlayerModel.chooseColor();
    }
    
    public void notToPlay () {
        pickCard();
    }

    @Override
    public void signalUno () {
        // TODO Auto-generated method stub
    }

    @Override
    public void againstUno () {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void challengeAgainstWildDrawFourCard () {
        // TODO Auto-generated method stub
        
    }
    
}
