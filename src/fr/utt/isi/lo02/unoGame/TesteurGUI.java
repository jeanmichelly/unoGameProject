package fr.utt.isi.lo02.unoGame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.*;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiGameScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiMenuScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSplashScreenView;

import java.util.ArrayList;

public class TesteurGUI {

    private ArrayList<Screen> screens = new ArrayList<Screen>();

    public TesteurGUI(){
        GuiSplashScreenView splashScreen = new GuiSplashScreenView();
        screens.add(splashScreen);
        GuiMenuScreenView menuScreen = new GuiMenuScreenView();
        screens.add(menuScreen);
        GuiGameScreenView gameScreen = new GuiGameScreenView();
        screens.add(gameScreen);

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UNO Game";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;
        new LwjglApplication(new GuiMasterView(screens), cfg);
    }

    public static void main(String[] args){
        new TesteurGUI();
    }
}
