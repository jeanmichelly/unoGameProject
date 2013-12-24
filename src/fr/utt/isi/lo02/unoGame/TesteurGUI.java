package fr.utt.isi.lo02.unoGame;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiBoardScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiLanguagesScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiMenuScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiGameSettingsScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiGeneralSettingsScreenView;
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
        GuiGameSettingsScreenView gameSettingsScreen = new GuiGameSettingsScreenView(); // 2
        screens.add(gameSettingsScreen);
        GuiBoardScreenView gameScreen = new GuiBoardScreenView(); // 3
        screens.add(gameScreen);
        GuiGeneralSettingsScreenView generalSettingsScreen = new GuiGeneralSettingsScreenView(); // 4
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
        ConsoleGameSettingsModel.initNumberPlayers();
        ConsoleGameSettingsModel.initNumberHumanPlayers();
        ConsoleGameSettingsModel.initPseudonymsHumanPlayers();
        ConsoleGameSettingsModel.initChoiceGameRules();

        BoardModel boardModel = BoardModel.getUniqueInstance();
        boardModel.initGameRules();
        boardModel.createPlayers();
        boardModel.initHumanPlayers();
        boardModel.initComputerPlayers();
        boardModel.chooseRandomDealer();
        boardModel.dispenseCards();   
        boardModel.initGame();
    }

    public static void main (String[] args) throws InvalidActionPickCardException {
        Expression.initExpression();
        initUnoGame();
        launchApplication();
    }

}