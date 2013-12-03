package model.card.effect;

import model.BoardModel;

public class DrawEffectModel extends ComponentEffectModel {

    public DrawEffectModel () {
        super((byte)20);
    }
    
    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().getNextPlayer().pickCard();
    }
    
}
