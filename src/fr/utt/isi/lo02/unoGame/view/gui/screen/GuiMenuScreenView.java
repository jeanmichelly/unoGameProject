package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;

public class GuiMenuScreenView implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit, buttonOption;
    private Label heading;

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
        stage.draw();
    }

    @Override
    public void resize(int i, int i2) {
        stage.setViewport(i, i2, true);
        table.invalidateHierarchy();
        table.setSize(i,i2);
    }

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ressources/img/ui/button.pack");
        skin = new Skin(Gdx.files.internal("ressources/skins/ui/menu.json"),atlas);

        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        buttonPlay = new TextButton(Expression.getProperty("BUTTON_PLAY"), skin);
        buttonOption = new TextButton(Expression.getProperty("BUTTON_OPTIONS"), skin);
        buttonExit = new TextButton(Expression.getProperty("BUTTON_QUIT"), skin);

        buttonPlay.addListener(new ClickListener(){
           public void clicked(InputEvent event, float x, float y){
               GuiMasterView.setScreen(2);
           }
        });
        buttonExit.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
               Gdx.app.exit();
            }
        });
        buttonOption.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                GuiMasterView.setScreen(3);
            }
         });
        
        buttonPlay.pad(30,80,30,80);
        buttonOption.pad(30,80,30,80);
        buttonExit.pad(30,80,30,80);

        heading = new Label(Expression.getProperty("TITLE_MP"), skin);

        table.add(heading);

        table.getCell(heading).spaceBottom(70);
        table.row();
        table.add(buttonPlay);

        table.getCell(buttonPlay).spaceBottom(20);
        table.row();
        table.add(buttonExit);

        table.getCell(buttonPlay).spaceBottom(20);
        table.row();
        table.add(buttonOption);

        stage.addActor(table);
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
