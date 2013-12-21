package fr.utt.isi.lo02.unoGame.view.gui.tween;

import aurelienribon.tweenengine.TweenAccessor;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;

public class GuiCardViewAccessor implements TweenAccessor<GuiCardView> {

    public static final int POSITION_Y = 0;

    @Override
    public int getValues(GuiCardView actor, int i, float[] floats) {
        switch(i){
            case POSITION_Y : floats[0] = actor.getY();
                return 1;
            default :
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(GuiCardView actor, int i, float[] floats) {
        switch(i){
            case POSITION_Y : actor.setY(floats[0]);
                break;
            default :
                assert false;
        }
    }
}
