package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;

/**
 * 
 * Cette classe permet d'effectuer un effet pour faire piocher des cartes à un joueur.
 *
 */
public class DrawEffectModel extends ComponentEffectModel {

    public DrawEffectModel () {
        super((byte)20);
    }
    
    @Override
    public void applyEffect () throws InvalidActionPickCardException {
            BoardModel.getUniqueInstance().getNextPlayer().pickCard();
    }
    
}
