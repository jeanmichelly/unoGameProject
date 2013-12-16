package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * Permet d'effectuer un effet joker et donc, assure le choix de la couleur.
 *
 */
public class WildEffectModel extends ComponentEffectModel {

    public WildEffectModel () {
        super((byte)30);
    }

    /**
     * Applique l'effet pour choisir une couleur
     */      public void applyEffect () throws InvalidColorModelException {
        BoardModel.getUniqueInstance().getPlayer().chooseColor();
    }
    
}
