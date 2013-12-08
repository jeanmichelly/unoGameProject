package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.BoardModel;

public class ReverseEffectModel extends ComponentEffectModel {

	public ReverseEffectModel () {
		super((byte)10);
	}

    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().setDirectionOfPlay();
    }

}
