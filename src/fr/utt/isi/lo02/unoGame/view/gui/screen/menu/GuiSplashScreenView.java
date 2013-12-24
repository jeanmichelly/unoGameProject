package fr.utt.isi.lo02.unoGame.view.gui.screen.menu;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.tween.SpriteAccessor;

public class GuiSplashScreenView implements Screen {

    private GuiSplashScreenController guiSplashScreenController;
    private SpriteBatch batch;
    private Sprite splash;
    private TweenManager tweenManager;

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.tweenManager.update(v);

        this.batch.begin();
        this.splash.draw(this.batch);
        this.batch.end();
    }

    @Override
    public void resize (int i, int i2) {
        
    }

    @Override
    public void show () {
        this.batch = new SpriteBatch();
        this.tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture splashTexture = new Texture(Gdx.files.internal("ressources/img/misc/splashScreen.png"));
        this.splash = new Sprite(splashTexture);
        this.splash.setPosition(Gdx.graphics.getWidth() / 2 - this.splash.getWidth() / 2, Gdx.graphics.getHeight() / 2 - this.splash.getHeight() / 2);

        Tween.set(this.splash, SpriteAccessor.ALPHA).target(0).start(this.tweenManager);
        
        this.guiSplashScreenController = new GuiSplashScreenController();
    }

    @Override
    public void hide () {
        
    }

    @Override
    public void pause () {
        
    }

    @Override
    public void resume () {
        
    }

    @Override
    public void dispose () {
        
    }
    
    private class GuiSplashScreenController implements TweenCallback {
        
        public GuiSplashScreenController () { 
            Tween.to(GuiSplashScreenView.this.splash, SpriteAccessor.ALPHA, .2f).target(1).repeatYoyo(1,.1f).setCallback(this).start(GuiSplashScreenView.this.tweenManager);
        }

        @Override
        public void onEvent (int arg0, BaseTween<?> arg1) {
            GuiMasterView.setScreen(1);            
        }
        
    }
    
}