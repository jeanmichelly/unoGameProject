package model.card.effect;

import model.BoardModel;

public class SkipEffectModel extends ComponentEffectModel {
    
    public SkipEffectModel () {
        super((byte)0);
    }
    
    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().moveCursorToNextPlayer();
    }
    
}
