package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.BoardModel;

public class SkipEffectModel extends ComponentEffectModel {
    
    public SkipEffectModel () {
        super((byte)0);
    }
    
    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().moveCursorToNextPlayer();
    }
    
}
