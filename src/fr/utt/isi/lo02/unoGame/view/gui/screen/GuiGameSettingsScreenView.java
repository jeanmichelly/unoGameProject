package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiGameSettingsScreenView implements Screen {

    GuiInitGameSettingsScreenController guiInitGameSettingsScreenController;
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonStartGame, buttonMainMenuReturn;
    private Label heading;

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act(v);
        this.stage.draw();
    }

    @Override
    public void resize (int i, int i2) {
        this.stage.setViewport(i, i2, true);
        this.table.invalidateHierarchy();
        this.table.setSize(i,i2);
    }

    @Override
    public void show () {
        this.stage = new Stage();

        Gdx.input.setInputProcessor(this.stage);

        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_BUTTON_MENU);

        this.table = new Table();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.buttonStartGame = new TextButton(Expression.getProperty("BUTTON_START_GAME"), skin);
        this.buttonMainMenuReturn = new TextButton(Expression.getProperty("BUTTON_MAIN_MENU_RETURN"), skin);

        this.buttonStartGame.pad(15,40,15,40);
        this.buttonMainMenuReturn.pad(15,40,15,40);

        this.heading = new Label(Expression.getProperty("LABEL_TITLE_GAME_SETTINGS"), skin);
        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70);
        this.table.row();
        this.table.add(this.buttonStartGame);
        
        this.table.getCell(this.buttonStartGame).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonMainMenuReturn);
        
        this.stage.addActor(this.table);
        
        this.guiInitGameSettingsScreenController = new GuiInitGameSettingsScreenController();
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
    
    private class GuiInitGameSettingsScreenController {
        
        public GuiInitGameSettingsScreenController () {
            GuiGameSettingsScreenView.this.buttonStartGame.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(3);
                }
             });
            GuiGameSettingsScreenView.this.buttonMainMenuReturn.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(1);
                }
            }); 
        }
        
    }
    
}