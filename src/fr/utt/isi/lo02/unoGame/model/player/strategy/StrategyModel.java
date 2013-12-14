package fr.utt.isi.lo02.unoGame.model.player.strategy;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * 
 * Cette interface a pour but d'utiliser le modele patron strategy pour permettre la gestion du moteur d'un joueur ordinateur.  
 *
 */
public interface StrategyModel {

    public void execute () throws InvalidActionPickCardException, InvalidActionPutDownCardException;
    
}
