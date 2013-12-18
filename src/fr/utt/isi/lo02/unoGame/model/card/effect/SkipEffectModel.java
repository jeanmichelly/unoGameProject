package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;

/**
 * Permet d'effectuer un effet pour sauter le tour d'un joueur.
 * @see ComponentEffectModel
 */
public class SkipEffectModel extends ComponentEffectModel {
    
    /**
     * Construit un effet pour sauter le tour d'un joueur avec une priorite de 0
     */
    public SkipEffectModel () {
        super((byte)0);
    }
    
    /**
     * Applique l'effet pour sauter le tour d'un joueur
     */  
    public void applyEffect () {
        BoardModel.getUniqueInstance().moveCursorToNextPlayer();
    }
    
}
