package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

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
    
    public void applyEffect (int i) throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().getPlayer(i).pickCard();
    }
    
}
