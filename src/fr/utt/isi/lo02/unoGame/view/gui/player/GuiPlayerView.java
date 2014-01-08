package fr.utt.isi.lo02.unoGame.view.gui.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureLoader;

public class GuiPlayerView extends Actor {

    private Sprite avatar;
    private Color randomColor;
    private PlayerModel playerModel;

    public GuiPlayerView (PlayerModel playerModel) {
        this.playerModel = playerModel;
        this.setSize(70,60);
        this.build();
    }

    public void build () {
        this.avatar = new Sprite(TextureLoader.TEXTURE_PLAYER_AVATAR_DEFAULT);
    }

    public void draw (SpriteBatch spriteBatch, float v) {
        this.avatar.setSize(super.getWidth(), super.getHeight());
        this.avatar.setOrigin(super.getOriginX(), super.getOriginY());
        this.avatar.setPosition(super.getX(), super.getY());
         
        if ( BoardModel.getUniqueInstance().getPlayer().getPseudonym().equals(playerModel.getPseudonym()) )
            this.avatar.setColor(new Color(0, 1f, 0, 1f));
        else
            this.avatar.setColor(new Color(1f, 0, 0, 1f));
            
        
        this.avatar.draw(spriteBatch);
    }
    
}