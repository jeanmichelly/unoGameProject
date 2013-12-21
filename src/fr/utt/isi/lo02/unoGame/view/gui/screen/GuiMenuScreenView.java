package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiMenuScreenView implements Screen {

    private Stage stage;
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
        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(stage);

        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_BUTTON_MENU);
        buttonPlay = new TextButton(Expression.getProperty("BUTTON_MENU_PLAY"), skin);
        buttonOption = new TextButton(Expression.getProperty("BUTTON_MENU_OPTIONS"), skin);
        buttonExit = new TextButton(Expression.getProperty("BUTTON_MENU_QUIT"), skin);

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

        buttonPlay.pad(15,40,15,40);
        buttonOption.pad(15,40,15,40);
        buttonExit.pad(15,40,15,40);

        heading = new Label(Expression.getProperty("LABEL_TITLE_MENU_MAIN"), skin);

        table.add(heading);
        table.getCell(heading).spaceBottom(70);
        table.row();

        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(20);
        table.row();

        table.add(buttonOption);
        table.getCell(buttonOption).spaceBottom(20);
        table.row();

        table.add(buttonExit);
        table.getCell(buttonExit).spaceBottom(20);
        table.row();

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
