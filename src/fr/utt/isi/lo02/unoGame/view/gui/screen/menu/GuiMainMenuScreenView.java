package fr.utt.isi.lo02.unoGame.view.gui.screen.menu;

import java.io.IOException;

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

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.screen.board.GuiBoardScreenView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiMainMenuScreenView implements Screen {

    GuiMainMenuScreenController guiMainMenuScreenController;
    private Stage stage;
    private Skin skin;
    private Label heading;
    private TextButton buttonCreateGame, buttonLoadGame, buttonGeneralSettings, buttonExit ;
    private Table table;
    
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
    
    public void initSkin () {
        this.skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
    }
    
    public void initHeading () {
        this.heading = new Label(Expression.getProperty("LABEL_TITLE_MENU_MAIN"), skin, "heading");
    }
    
    public void initButtons () {
        this.buttonCreateGame = new TextButton(Expression.getProperty("BUTTON_CREATE_GAME"), skin);
        this.buttonLoadGame = new TextButton(Expression.getProperty("BUTTON_LOAD_GAME"), skin);
        this.buttonGeneralSettings = new TextButton(Expression.getProperty("BUTTON_MENU_SETTINGS"), skin);
        this.buttonExit = new TextButton(Expression.getProperty("BUTTON_MENU_QUIT"), skin);

        this.buttonCreateGame.pad(15,40,15,40);
        this.buttonLoadGame.pad(15,40,15,40);
        this.buttonGeneralSettings.pad(15,40,15,40);
        this.buttonExit.pad(15,40,15,40);
    }
    
    public void initTable () {
        this.table = new Table();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70);
        this.table.row();
        this.table.add(this.buttonCreateGame);
        
        this.table.getCell(this.buttonCreateGame).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonLoadGame);
        
        this.table.getCell(this.buttonLoadGame).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonGeneralSettings);
        
        this.table.getCell(this.buttonGeneralSettings).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonExit);
    }
    
    public void initStage () {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        this.stage.addActor(this.table);        
    }

    @Override
    public void show () {
        initSkin();
        initHeading();
        initButtons();
        initTable();
        initStage();
        this.guiMainMenuScreenController = new GuiMainMenuScreenController();
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
    
    private class GuiMainMenuScreenController {
        
        public GuiMainMenuScreenController () {
            GuiMainMenuScreenView.this.buttonCreateGame.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(2);
                }
            });
            GuiMainMenuScreenView.this.buttonLoadGame.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    try {
                        BoardModel.getUniqueInstance().loadBoardModel();
                        ((GuiBoardScreenView)(GuiMasterView.getScreen(3))).loadBoardModel(BoardModel.getUniqueInstance());
                        GuiMasterView.setScreen(3);   
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            GuiMainMenuScreenView.this.buttonGeneralSettings.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(4);
                }
            }); 
            GuiMainMenuScreenView.this.buttonExit.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    Gdx.app.exit();
                }
            });     
        }
        
    }
    
}