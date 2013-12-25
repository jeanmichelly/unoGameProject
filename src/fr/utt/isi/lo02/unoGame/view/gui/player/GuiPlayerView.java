package fr.utt.isi.lo02.unoGame.view.gui.player;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

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
        this.randomColor = new Color((float) (0.5 + (Math.random() * ((1 - 0.5) + 1))), (float) (0.5 + (Math.random() * ((1 - 0.5) + 1))), (float) (0.5 + (Math.random() * ((1 - 0.5) + 1))), 1);
    }

    public void draw (SpriteBatch spriteBatch, float v) {
        this.avatar.setSize(super.getWidth(), super.getHeight());
        this.avatar.setOrigin(super.getOriginX(), super.getOriginY());
        this.avatar.setPosition(super.getX(), super.getY());
        this.avatar.setColor(randomColor);
        this.avatar.draw(spriteBatch);
    }
    
}