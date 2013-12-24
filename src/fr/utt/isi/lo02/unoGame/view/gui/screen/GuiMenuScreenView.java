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

    GuiMenuScreenController guiMenuScreenController;
    private Stage stage;
    private Table table;
    private TextButton buttonCreateGame, buttonGeneralSettings, buttonExit ;
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
        this.table = new Table();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Gdx.input.setInputProcessor(this.stage);

        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
        
        this.buttonCreateGame = new TextButton(Expression.getProperty("BUTTON_CREATE_GAME"), skin);
        this.buttonGeneralSettings = new TextButton(Expression.getProperty("BUTTON_MENU_SETTINGS"), skin);
        this.buttonExit = new TextButton(Expression.getProperty("BUTTON_MENU_QUIT"), skin);

        this.buttonCreateGame.pad(15,40,15,40);
        this.buttonGeneralSettings.pad(15,40,15,40);
        this.buttonExit.pad(15,40,15,40);

        this.heading = new Label(Expression.getProperty("LABEL_TITLE_MENU_MAIN"), skin, "heading");
        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70);
        this.table.row();
        this.table.add(this.buttonCreateGame);
        
        this.table.getCell(this.buttonCreateGame).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonGeneralSettings);
        
        this.table.getCell(this.buttonGeneralSettings).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonExit);

        this.stage.addActor(this.table);
        
        this.guiMenuScreenController = new GuiMenuScreenController();
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
    
    private class GuiMenuScreenController {
        
        public GuiMenuScreenController () {
            GuiMenuScreenView.this.buttonCreateGame.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(2);
                }
            });
            GuiMenuScreenView.this.buttonGeneralSettings.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(4);
                }
            }); 
            GuiMenuScreenView.this.buttonExit.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });     
        }
        
    }
    
}