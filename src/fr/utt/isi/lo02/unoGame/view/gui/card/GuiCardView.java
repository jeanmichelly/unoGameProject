package fr.utt.isi.lo02.unoGame.view.gui.card;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.view.gui.tween.SpriteAccessor;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureLoader;

public class GuiCardView extends Actor {

    public final static int NATIVE_CARD_WIDTH = 220;
    public final static int NATIVE_CARD_HEIGHT = 288;

    private Sprite cardPattern, cardSymbol, cardBackground, cardHighlighted;
    private TextureRegion symbolRegion;
    private CardModel cardModel;
    private TweenManager tweenManager;

    private boolean highlited = false;
    private boolean flipped = false;

    public GuiCardView(CardModel cardModel) {
        this.cardModel = cardModel;
        this.setSize(GuiCardView.NATIVE_CARD_WIDTH, GuiCardView.NATIVE_CARD_HEIGHT);
        TextureAtlas atlas = TextureAtlasLoader.ATLAS_CARD;
        this.symbolRegion = atlas.findRegion(Character.toString(this.cardModel.getSymbol().getLabel()));
        this.cardSymbol = new Sprite(this.symbolRegion);
        this.cardPattern = new Sprite(TextureLoader.TEXTURE_CARD_PATTERN);
        this.cardBackground = new Sprite(TextureLoader.TEXTURE_CARD_BACKGROUND);
        this.cardHighlighted = new Sprite(TextureLoader.TEXTURE_CARD_HIGHLIGHTED);
    }

    @Override
    public void setPosition (float x, float y) {
        super.setPosition(x, y);
    }

    public void setTweenManager (TweenManager tweenManager) {
        this.tweenManager = tweenManager;
    }

    public void flipCard () {
        this.flipped = !this.flipped;
        if ( this.flipped ) {
            this.cardPattern = new Sprite(TextureLoader.TEXTURE_CARD_PATTERN_FLIPPED);
        } else {
            this.cardPattern = new Sprite(TextureLoader.TEXTURE_CARD_PATTERN_FLIPPED);
        }
    }

    public void setHighlited (boolean highlited) {
        this.highlited = highlited;

        this.tweenManager.killTarget(this.cardHighlighted);

        if ( this.highlited ) {
            Tween.set(this.cardHighlighted, SpriteAccessor.ALPHA).target(0).start(this.tweenManager);
            Tween.to(this.cardHighlighted, SpriteAccessor.ALPHA, .15f).target(1).start(this.tweenManager);
        } else {
            Tween.set(this.cardHighlighted, SpriteAccessor.ALPHA).target(1).start(this.tweenManager);
            Tween.to(this.cardHighlighted, SpriteAccessor.ALPHA, .15f).target(0).start(this.tweenManager);
        }
    }

    public void build () {
        if ( this.tweenManager != null )
        Tween.set(this.cardHighlighted, SpriteAccessor.ALPHA).target(0).start(this.tweenManager);
    }

    public void resize (float factor) {
        super.setSize(super.getWidth() * factor, super.getHeight() * factor);
    }

    public void draw (SpriteBatch spriteBatch, float v) {
        this.cardPattern.setSize(super.getWidth(), super.getHeight());
        this.cardPattern.setPosition(super.getX(), super.getY());
        this.cardPattern.setOrigin(super.getOriginX(), super.getOriginY());
        this.cardPattern.setRotation(super.getRotation());

        this.cardBackground.setSize(super.getWidth(), super.getHeight());
        this.cardBackground.setPosition(super.getX(), super.getY());
        this.cardBackground.setOrigin(super.getOriginX(), super.getOriginY());
        this.cardBackground.setRotation(super.getRotation());

        this.cardPattern.setSize(super.getWidth(), super.getHeight());
        this.cardPattern.setPosition(super.getX(), super.getY());
        this.cardPattern.setOrigin(super.getOriginX(), super.getOriginY());
        this.cardPattern.setRotation(super.getRotation());

        Vector2 symbolSize = new Vector2(this.symbolRegion.getRegionWidth() / (NATIVE_CARD_WIDTH / super.getWidth()), this.symbolRegion.getRegionHeight() / (NATIVE_CARD_HEIGHT / super.getHeight()));
        this.cardSymbol.setSize(symbolSize.x, symbolSize.y);
        this.cardSymbol.setPosition(super.getX() + super.getWidth() / 2 - this.cardSymbol.getWidth() / 2 , super.getY() + super.getHeight() / 2 - this.cardSymbol.getHeight() / 2);
        this.cardSymbol.setOrigin(super.getOriginX(), super.getOriginY());
        this.cardSymbol.setRotation(super.getRotation());

        if ( this.cardModel.getColor() != null ) {
            this.cardSymbol.setColor(this.cardModel.getColor().getColor());
            this.cardBackground.setColor(this.cardModel.getColor().getColor());
        } else {
            this.cardBackground.setColor(new Color(0, 0 ,0, 1));
        }

        this.cardHighlighted.setSize(super.getWidth() * 1.17f, super.getHeight() * 1.118f);
        this.cardHighlighted.setPosition(super.getX() - (this.cardHighlighted.getWidth() - super.getWidth()) / 2, super.getY() - (this.cardHighlighted.getHeight() - super.getHeight()) / 2);
        this.cardHighlighted.setOrigin(super.getOriginX(), super.getOriginY());
        this.cardHighlighted.setRotation(super.getRotation());

        if ( this.flipped ) {
            this.cardPattern.draw(spriteBatch);
        } else {
            this.cardBackground.draw(spriteBatch);
            this.cardPattern.draw(spriteBatch);
            this.cardSymbol.draw(spriteBatch);
        }

        if ( highlited )
            this.cardHighlighted.draw(spriteBatch);
    }
    
}