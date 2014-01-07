package fr.utt.isi.lo02.unoGame.view.gui;

import java.util.ArrayList;

import aurelienribon.tweenengine.Tween;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;

import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.tween.GuiCardViewAccessor;
import fr.utt.isi.lo02.unoGame.view.gui.tween.SpriteAccessor;

public class GuiMasterView extends Game {

    private static ArrayList<Screen> screens;

    public GuiMasterView(ArrayList<Screen> screens) {
        GuiMasterView.screens = screens;

        Tween.registerAccessor(GuiCardView.class, new GuiCardViewAccessor());
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
    }
    
    public static Screen getScreen (int screenIndex) {
        return screens.get(screenIndex);
    }

    public static void setScreen (int screenIndex) {
        ((Game)Gdx.app.getApplicationListener()).setScreen(GuiMasterView.screens.get(screenIndex));
    }

    @Override
    public void create () {
        GuiMasterView.setScreen(0);
    }
    @Override
    public void dispose () {
        super.dispose();
    }

    @Override
    public void render () {
        super.render();
    }

    @Override
    public void resize (int width, int height) {
        super.resize(width,height);
    }

    @Override
    public void pause () {
        super.pause();
    }

    @Override
    public void resume () {
        super.resume();
    }
    
}