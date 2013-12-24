package fr.utt.isi.lo02.unoGame;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameParametersModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.*;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiGameCreationScreenView;


public class TesteurGUI {

    public final static int APPLICATION_WIDTH = 1280;
    public final static int APPLICATION_HEIGHT = 720;

    private static ArrayList<Screen> screens = new ArrayList<Screen>();

    public static void launchApplication () {
        GuiSplashScreenView splashScreen = new GuiSplashScreenView(); // 0
        screens.add(splashScreen);
        GuiMenuScreenView menuScreen = new GuiMenuScreenView(); // 1
        screens.add(menuScreen);
        GuiGameCreationScreenView gameSettingsScreen = new GuiGameCreationScreenView(); // 2
        screens.add(gameSettingsScreen);
        GuiBoardScreenView gameScreen = new GuiBoardScreenView(); // 3
        screens.add(gameScreen);
        GuiSettingsScreenView generalSettingsScreen = new GuiSettingsScreenView(); // 4
        screens.add(generalSettingsScreen);
        GuiLanguagesScreenView languagesScreen = new GuiLanguagesScreenView(); // 5
        screens.add(languagesScreen);
     

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UNO Game";
        cfg.useGL20 = true;
        cfg.width = APPLICATION_WIDTH;
        cfg.height = APPLICATION_HEIGHT;

        new LwjglApplication(new GuiMasterView(screens), cfg);
    }

    public static void initUnoGame () throws InvalidActionPickCardException {


    }

    public static void main (String[] args) throws InvalidActionPickCardException {
        Expression.initExpression();
        initUnoGame();
        launchApplication();
    }

}