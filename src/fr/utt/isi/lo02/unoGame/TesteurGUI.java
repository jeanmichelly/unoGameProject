package fr.utt.isi.lo02.unoGame;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.utt.isi.lo02.unoGame.controller.board.BoardController;
import fr.utt.isi.lo02.unoGame.controller.board.GameSettingsController;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiBoardScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiGameSettingsScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.menu.GuiLanguagesScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.menu.GuiMainMenuScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.menu.GuiScoringScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.menu.GuiSettingsScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.menu.GuiSplashScreenView;

public class TesteurGUI {

    public final static int APPLICATION_WIDTH = 1280;
    public final static int APPLICATION_HEIGHT = 720;

    private static ArrayList<Screen> screens = new ArrayList<Screen>();

    public static void launchApplication () {
        GuiSplashScreenView splashScreen = new GuiSplashScreenView(); // 0
        screens.add(splashScreen);
        GuiMainMenuScreenView mainMenuScreen = new GuiMainMenuScreenView(); // 1
        screens.add(mainMenuScreen);
        
        GameSettingsController gameSettingsController = new GameSettingsController (GameSettingsModel.getUniqueInstance());
        GuiGameSettingsScreenView gameSettingsView = new GuiGameSettingsScreenView(
                GameSettingsModel.getUniqueInstance(), gameSettingsController
                ); // 2
        screens.add(gameSettingsView);
        
        BoardController boardController = new BoardController(BoardModel.getUniqueInstance());
        GuiBoardScreenView boardView = new GuiBoardScreenView(
                BoardModel.getUniqueInstance(), boardController); // 3
        screens.add(boardView);
        
        GuiSettingsScreenView settingsScreen = new GuiSettingsScreenView(); // 4
        screens.add(settingsScreen);
        GuiLanguagesScreenView languagesScreen = new GuiLanguagesScreenView(); // 5
        screens.add(languagesScreen);
        GuiScoringScreenView scoringScreen = new GuiScoringScreenView(); // 6
        screens.add(scoringScreen);
        
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