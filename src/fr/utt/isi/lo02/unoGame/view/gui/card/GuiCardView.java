package fr.utt.isi.lo02.unoGame.view.gui.card;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;

public class GuiCardView extends Actor {

    private Sprite pattern;
    private Sprite symbol;
    private TextureAtlas atlas;
    private Shape cardColor;
    private float rotation;

    public GuiCardView(CardModel cardModel){
        atlas = new TextureAtlas("ressources/img/card/symbols.pack");
        pattern = new Sprite(new Texture(Gdx.files.internal("ressources/img/card/elements/cardPattern.png")));
        symbol = new Sprite(atlas.findRegion("0"));
    }

    public void draw(SpriteBatch spriteBatch, float v) {
        pattern.setPosition(super.getX() - pattern.getRegionWidth()/2, super.getY() - pattern.getRegionHeight()/2);
//        pattern.setRotation(super.getRotation());
        pattern.setSize(super.getWidth(), super.getHeight());
        symbol.setPosition(super.getX() - symbol.getRegionWidth()/2, super.getY() - symbol.getRegionHeight()/2);
//        symbol.setRotation(super.getRotation());
        symbol.setSize(super.getWidth(), super.getHeight());
        pattern.draw(spriteBatch);
        symbol.draw(spriteBatch);
    }
}
