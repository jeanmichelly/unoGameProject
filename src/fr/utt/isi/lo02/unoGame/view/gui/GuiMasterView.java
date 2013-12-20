package fr.utt.isi.lo02.unoGame.view.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSplashScreenView;

import java.util.ArrayList;

public class GuiMasterView extends Game {

    private static ArrayList<Screen> screens;

    public GuiMasterView(ArrayList<Screen> screens) {
        GuiMasterView.screens = screens;
    }

    public static void setScreen(int screenIndex){
        ((Game)Gdx.app.getApplicationListener()).setScreen(GuiMasterView.screens.get(screenIndex));
    }

    @Override
    public void create() {
        GuiMasterView.setScreen(0);
    }
    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width,height);
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
