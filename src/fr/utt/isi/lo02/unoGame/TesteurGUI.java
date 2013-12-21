package fr.utt.isi.lo02.unoGame;

import java.util.ArrayList;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiChangeLanguageScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiGameScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiMenuScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiOptionScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.GuiSplashScreenView;

public class TesteurGUI {

    private static ArrayList<Screen> screens = new ArrayList<Screen>();

    public static void launchApplication(){
        GuiSplashScreenView splashScreen = new GuiSplashScreenView(); // 0
        screens.add(splashScreen);
        GuiMenuScreenView menuScreen = new GuiMenuScreenView(); // 1
        screens.add(menuScreen);
        GuiGameScreenView gameScreen = new GuiGameScreenView(); // 2
        screens.add(gameScreen);
        GuiOptionScreenView optionScreen = new GuiOptionScreenView(); // 3
        screens.add(optionScreen);
        GuiChangeLanguageScreenView changeLanguageScreen = new GuiChangeLanguageScreenView(); // 4
        screens.add(changeLanguageScreen);

        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "UNO Game";
        cfg.useGL20 = true;
        cfg.width = 1280;
        cfg.height = 720;

        new LwjglApplication(new GuiMasterView(screens), cfg);
    }

    public static void initUnoGame(){
        UserModel.initNumberPlayers();
        UserModel.initNumberHumanPlayers();
        UserModel.initPseudonymsHumanPlayers();
        UserModel.initChoiceGameRules();

        BoardModel boardModel = BoardModel.getUniqueInstance();
        boardModel.initGameRules();
        boardModel.createPlayers();
        boardModel.initHumanPlayers();
        boardModel.initComputerPlayers();
        boardModel.chooseRandomDealer();
        try {
            boardModel.dispenseCards();
        } catch (InvalidActionPickCardException e) {
            e.printStackTrace();
        }
        boardModel.initGame();
    }

    public static void main(String[] args){
        Expression.initExpression();
        initUnoGame();
        launchApplication();
    }
}
