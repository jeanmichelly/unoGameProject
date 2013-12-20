package fr.utt.isi.lo02.unoGame.view.gui.card;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.view.gui.utils.AtlasLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureLoader;

public class GuiCardView extends Actor {

    public final static int NATIVE_CARD_WIDTH = 200;
    public final static int NATIVE_CARD_HEIGHT = 288;

    private Sprite pattern;
    private Sprite symbol;
    private TextureAtlas atlas;
    private TextureRegion symbolRegion;
    private CardModel cardModel;

    private boolean flipped = false;
    private boolean upped = false;

    public GuiCardView(CardModel cardModel){
        this.cardModel = cardModel;
        this.setSize(GuiCardView.NATIVE_CARD_WIDTH, GuiCardView.NATIVE_CARD_HEIGHT);
    }

    public void toggleUpped(){
        this.upped = !this.upped;
        if(this.upped){
            super.setY(super.getY() + 40);
        } else {
            super.setY(super.getY() - 40);
        }
    }

    public void flipCard(){
        this.flipped = !this.flipped;
    }

    public void build(){
        this.atlas = AtlasLoader.ATLAS_SYMBOLS;
        this.symbolRegion = atlas.findRegion(Character.toString(this.cardModel.getSymbol().getLabel()));
        this.symbol = new Sprite(symbolRegion);
    }

    public void resize(float factor){
        this.setSize(super.getWidth() * factor, super.getHeight() * factor);
    }

    public void draw(SpriteBatch spriteBatch, float v) {
        if(flipped){
            this.pattern = new Sprite(TextureLoader.TEXTURE_CARD_PATTERN_FLIPPED);
            pattern.setSize(super.getWidth(), super.getHeight());
            pattern.setPosition(super.getX(), super.getY());
            pattern.setOrigin(super.getOriginX(), super.getOriginY());
            pattern.setRotation(super.getRotation());
            pattern.draw(spriteBatch);
        } else {
            this.pattern = new Sprite(TextureLoader.TEXTURE_CARD_PATTERN);
            pattern.setSize(super.getWidth(), super.getHeight());
            pattern.setPosition(super.getX(), super.getY());
            pattern.setOrigin(super.getOriginX(), super.getOriginY());
            pattern.setRotation(super.getRotation());

            Vector2 symbolSize = new Vector2(symbolRegion.getRegionWidth() / (NATIVE_CARD_WIDTH / super.getWidth()), symbolRegion.getRegionHeight() / (NATIVE_CARD_HEIGHT / super.getHeight()));
            symbol.setSize(symbolSize.x, symbolSize.y);
            symbol.setPosition(super.getX() + super.getWidth() / 2 - symbol.getWidth() / 2 , super.getY() + super.getHeight() / 2 - symbol.getHeight() / 2);
            symbol.setOrigin(super.getOriginX(), super.getOriginY());
            symbol.setRotation(super.getRotation());

            if(cardModel.getColor() != null){
                symbol.setColor(cardModel.getColor().getColor());
            }
            pattern.draw(spriteBatch);
            symbol.draw(spriteBatch);
        }

    }
}
