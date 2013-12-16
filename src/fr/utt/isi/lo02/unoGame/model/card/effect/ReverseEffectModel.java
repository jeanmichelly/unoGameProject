package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;

/**
 * Permet d'effectuer un effet pour inverser le sens du jeu.
 */
public class ReverseEffectModel extends ComponentEffectModel {

    /**
     * Construit un effet pour inverser le sens du jeu avec une priorite de 10
     */
	public ReverseEffectModel () {
		super((byte)10);
	}

    /**
     * Applique l'effet pour inverser le sens du jeu
     */    
	public void applyEffect () {
        BoardModel.getUniqueInstance().setDirectionOfPlay();
    }

}
