package fr.utt.isi.lo02.unoGame.model.card.effect;

import java.io.Serializable;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;

/**
 * Permet d'effectuer un effet pour inverser le sens du jeu.
 * @see ComponentEffectModel
 */
public class ReverseEffectModel extends ComponentEffectModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * Construit un effet pour inverser le sens du jeu avec une priorité de 10
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
