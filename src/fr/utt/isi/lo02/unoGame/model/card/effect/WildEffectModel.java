package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * 
 * Cette classe permet d'effectuer un effet joker. Il assure le choix de la couleur.
 *
 */
public class WildEffectModel extends ComponentEffectModel {

    public WildEffectModel () {
        super((byte)30);
    }

    @Override
    public void applyEffect () throws InvalidColorModelException {
        BoardModel.getUniqueInstance().getPlayer().chooseColor();
    }
    
}
