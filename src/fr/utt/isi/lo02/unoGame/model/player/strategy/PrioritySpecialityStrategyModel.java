package fr.utt.isi.lo02.unoGame.model.player.strategy;

import java.util.ArrayList;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

/**
 * 
 * Correspond a l'une des strategies que peut utiliser un joueur ordinateur. 
 * Elle permet, par exemple, de jouer agressif lorsqu'il reste peu de carte au prochain joueur. 
 * Elle passe en priorite les cartes avec des effets.
 */
public class PrioritySpecialityStrategyModel extends StrategyModel {

    /**
     * Execute la strategie qui donne la priorite aux cartes avec des effets
     */
    public void execute () { 
    }
    
}
