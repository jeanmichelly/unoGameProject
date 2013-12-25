package fr.utt.isi.lo02.unoGame.controller.board;

import java.io.IOException;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;

public class BoardController {
    
    private static BoardModel boardModel;
    
    public BoardController(BoardModel board) {
        boardModel = board;   
    }
    
    public ButtonController getButtonController () {
        return ButtonController.getUniqueInstance(); 
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
                case "SG" :
                    saveGame();
                    break;
            }
        }
        
        public void saveGame() {
            try {
                boardModel.saveBoardModel();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } 
 
}
