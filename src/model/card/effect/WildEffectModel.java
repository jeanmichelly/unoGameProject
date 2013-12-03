package model.card.effect;

import model.BoardModel;

public class WildEffectModel extends ComponentEffectModel {

    public WildEffectModel () {
        super((byte)30);
    }

    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().getPlayer().chooseColor();
    }
    
}
