package fr.utt.isi.lo02.unoGame;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.utt.isi.lo02.unoGame.controller.board.GameSettingsController;
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.board.GuiBoardView;
import fr.utt.isi.lo02.unoGame.view.gui.board.GuiGameSettingsView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiLanguagesScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiMenuScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSettingsScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSplashScreenView;


public class TesteurGUI {

    public final static int APPLICATION_WIDTH = 1280;
    public final static int APPLICATION_HEIGHT = 720;

    private static ArrayList<Screen> screens = new ArrayList<Screen>();

    public static void launchApplication () {
        GuiSplashScreenView splashScreen = new GuiSplashScreenView(); // 0
        screens.add(splashScreen);
        GuiMenuScreenView menuScreen = new GuiMenuScreenView(); // 1
        screens.add(menuScreen);
        
        GameSettingsController gameSettingsController = new GameSettingsController (GameSettingsModel.getUniqueInstance());
        GuiGameSettingsView gameSettingsView = new GuiGameSettingsView(
                GameSettingsModel.getUniqueInstance(), gameSettingsController
                ); // 2
        screens.add(gameSettingsView);
        
        GuiBoardView board = new GuiBoardView(); // 3
        screens.add(board);
        GuiSettingsScreenView settingsScreen = new GuiSettingsScreenView(); // 4
        screens.add(settingsScreen);
        GuiLanguagesScreenView languagesScreen = new GuiLanguagesScreenView(); // 5
        screens.add(languagesScreen);
     

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UNO Game";
        cfg.useGL20 = true;
        cfg.width = APPLICATION_WIDTH;
        cfg.height = APPLICATION_HEIGHT;

        new LwjglApplication(new GuiMasterView(screens), cfg);
    }

    public static void main (String[] args) throws InvalidActionPickCardException {
        Expression.initExpression();
        launchApplication();
    }

}