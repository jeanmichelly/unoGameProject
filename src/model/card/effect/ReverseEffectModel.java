package model.card.effect;

import model.BoardModel;

public class ReverseEffectModel extends ComponentEffectModel {

	public ReverseEffectModel () {
		super((byte)10);
	}

    @Override
    public void applyEffect () {
        BoardModel.getUniqueInstance().setDirectionOfPlay();
    }

}
