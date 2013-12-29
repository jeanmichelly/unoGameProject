package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;

public class GameSettingsController {
    
    private static GameSettingsModel gameSettingsModel;
    
    public GameSettingsController(GameSettingsModel gameSettingsModel) {
        GameSettingsController.gameSettingsModel = gameSettingsModel;
    }
    
    public ButtonController getButtonController () {
        return ButtonController.getUniqueInstance(); 
    }
    
    public SelectBoxController getSelectBoxController () {
        return SelectBoxController.getUniqueInstance(); 
    }
    
    public CheckBoxController getCheckBoxController () {
        return CheckBoxController.getUniqueInstance(); 
    }

    private static class ButtonController extends ClickListener {
        
        private static ButtonController buttonController = null;
        
        private static ButtonController getUniqueInstance () {
            if ( buttonController == null )
                buttonController = new ButtonController();
            return buttonController;
        }
        
        public void clicked (InputEvent event, float x, float y) {
            switch ( String.valueOf(event.getListenerActor()) ) {
                case "BSG" :
                    try {
                        createGame();
                    } catch (InvalidActionPickCardException e) {
                        e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                    GuiMasterView.setScreen(3);   
                    break;
                case "BMMR" :
                    GuiMasterView.setScreen(1);
                    break;
            }
        }
        
        public void createGame() throws InvalidActionPickCardException {
            gameSettingsModel.initPseudonymsHumanPlayers();
            BoardModel boardModel = BoardModel.getUniqueInstance();
            boardModel.initGameRules();
            boardModel.createPlayers();
            // Permet de réinitialiser les cartes selon le mode de jeu
            DrawPileModel.getUniqueInstance().initDrawPileModel();
            try {
                boardModel.initHumanPlayers();
            } catch ( ArrayIndexOutOfBoundsException e ) {
                gameSettingsModel.setNumberHumanPlayers((byte)1);
                boardModel.initHumanPlayers();
            }
            boardModel.initComputerPlayers();
            boardModel.chooseRandomDealer();
            boardModel.dispenseCards();
            boardModel.initGame();
        }
    }
    
    private static class SelectBoxController extends ChangeListener {

        private static SelectBoxController selectBoxController = null;
        
        private static SelectBoxController getUniqueInstance () {
            if ( selectBoxController == null )
                selectBoxController = new SelectBoxController();
            return selectBoxController;
        }
        
        @Override
        public void changed(ChangeEvent changeEvent, Actor actor) {
            if ( actor instanceof SelectBox ) {
                switch ( actor.getName() ) {
                    case "NP" :
                        SelectBox selectBoxNumberPlayers = (SelectBox) actor;
                        int numberOfPlayers = Integer.parseInt(selectBoxNumberPlayers.getSelection());
                        if ( numberOfPlayers < gameSettingsModel.getNumberPlayers() && 
                                gameSettingsModel.getNumberHumanPlayers() > numberOfPlayers ) {
                            gameSettingsModel.initNumberHumanPlayers((byte)numberOfPlayers);
                        }
                        gameSettingsModel.initNumberPlayers((byte)numberOfPlayers);
                        gameSettingsModel.setChanged();
                        gameSettingsModel.notifyObservers();
                        break;
                    case "NHP" :
                        SelectBox selectBoxNumberHumanPlayers = (SelectBox) actor;
                        int numberHumanOfPlayers = Integer.parseInt(selectBoxNumberHumanPlayers.getSelection());
                        gameSettingsModel.initNumberHumanPlayers((byte)numberHumanOfPlayers);
                        break;
                }
            }
        }
        
    }
    
    private static class CheckBoxController extends InputListener {
        
        private static CheckBoxController checkBoxController = null;
        
        private static CheckBoxController getUniqueInstance () {
            if ( checkBoxController == null )
                checkBoxController = new CheckBoxController();
            return checkBoxController;
        }
        
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if ( event.getListenerActor() instanceof CheckBox ) {
                CheckBox checkbox = (CheckBox) event.getListenerActor();
                gameSettingsModel.initChoiceGameRules(checkbox.getName().charAt(0));
                System.out.println(checkbox.isChecked()+"\n"+checkbox.getName());
            }
            return true;                    
        }
    }
    
}
