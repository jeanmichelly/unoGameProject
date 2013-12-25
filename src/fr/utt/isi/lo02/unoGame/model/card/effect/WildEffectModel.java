package fr.utt.isi.lo02.unoGame.model.card.effect;

import java.io.Serializable;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * Permet d'effectuer un effet joker et donc, assure le choix de la couleur.
 * @see ComponentEffectModel
 */
public class WildEffectModel extends ComponentEffectModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public WildEffectModel () {
        super((byte)30);
    }

    /**
     * Applique l'effet pour choisir une couleur
     */      public void applyEffect () throws InvalidColorModelException {
        BoardModel.getUniqueInstance().getPlayer().chooseColor();
    }
    
}
