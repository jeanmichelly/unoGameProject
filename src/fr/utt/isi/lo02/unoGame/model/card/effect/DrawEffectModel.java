package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.BoardModel;

public class DrawEffectModel extends ComponentEffectModel {

    public DrawEffectModel () {
        super((byte)20);
    }
    
    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().getNextPlayer().pickCard();
    }
    
}
