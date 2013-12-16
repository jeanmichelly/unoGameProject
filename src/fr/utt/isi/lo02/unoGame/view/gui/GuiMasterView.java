package fr.utt.isi.lo02.unoGame.view.gui;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSplashView;

public class GuiMasterView extends Game {

    public static void changeScreen(Screen screen){
        ((Game)Gdx.app.getApplicationListener()).setScreen(screen);
    }

    @Override
    public void create() {
        GuiMasterView.changeScreen(new GuiSplashView());
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
