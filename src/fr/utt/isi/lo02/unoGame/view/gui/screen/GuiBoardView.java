package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;

public class GuiBoardView implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private SpriteBatch batch;
    private Sprite c1, c2;
    private Actor card1, card2;
    private final Group ribon = new Group();

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();

        batch.begin();
            card1.draw(batch,1);
        batch.end();
    }

    @Override
    public void resize(int i, int i2) {
        stage.setViewport(i, i2, true);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ressources/img/card/symbols.pack");
        skin = new Skin();

        card1 = new Image(new Texture(Gdx.files.internal("ressources/img/card/elements/cardPattern.png")));
        card1.setBounds(120,120,50,70);
        card1.setTouchable(Touchable.enabled);
        card2 = new Image(new Texture(Gdx.files.internal("ressources/img/card/elements/cardPattern.png")));
        card2.setBounds(100,100,50,70);
        card2.setTouchable(Touchable.enabled);

        ribon.addActor(card1);
        ribon.addActor(card2);

        ribon.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                if(ribon.hit(x, y, true) != null){
                    Actor actor = ribon.hit(x,y,true);
                    actor.setY(actor.getY() + 10);
                    actor.toBack();
                }
            }
        });

        stage.addActor(ribon);
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
