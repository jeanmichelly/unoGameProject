package fr.utt.isi.lo02.unoGame.controller.board;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

public class PlayerController extends ClickListener {

    PlayerModel playerModel;

    public PlayerController(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
    
    public void clicked (InputEvent event, float x, float y) {
        BoardModel.getUniqueInstance().moveCursorToNextPlayer();
        DrawPileModel.getUniqueInstance().setDrawable(true);
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers();
    }
    
}
