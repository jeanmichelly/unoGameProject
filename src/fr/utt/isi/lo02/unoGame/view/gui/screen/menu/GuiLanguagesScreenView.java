package fr.utt.isi.lo02.unoGame.view.gui.screen.menu;

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

public class GuiLanguagesScreenView implements Screen {

    GuiChangeLanguageScreenController guiChangeLanguageScreenController;
    private Stage stage;
    private Skin skin;
    private Label heading;
    private TextButton buttonFr, buttonEn, buttonMainMenuReturn;
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
    
    public void initStage () {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);    
    }
    
    public void initSkin () {
        this.skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
    }
    
    public void initHeading () {
        this.heading = new Label(Expression.getProperty("LABEL_TITLE_MENU_LANGUAGE"), skin, "heading");
    }
    
    public void initButtons () {
        this.buttonFr = new TextButton(Expression.getProperty("BUTTON_MENU_LANGAGE_FR"), skin);
        this.buttonEn = new TextButton(Expression.getProperty("BUTTON_MENU_LANGAGE_EN"), skin);
        this.buttonMainMenuReturn = new TextButton(Expression.getProperty("BUTTON_MAIN_MENU_RETURN"), skin);

        this.buttonFr.pad(15,40,15,40);
        this.buttonEn.pad(15,40,15,40);
        this.buttonMainMenuReturn.pad(15,40,15,40);
    }
    
    public void initTable () {
        this.table = new Table();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70);
        this.table.row();
        this.table.add(this.buttonFr);
        
        this.table.getCell(this.buttonFr).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonEn);
        
        this.table.getCell(this.buttonEn).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonMainMenuReturn);
    }

    @Override
    public void show () {
        initStage();
        initSkin();
        initHeading();
        initButtons();
        initTable();
        
        this.stage.addActor(this.table);
        this.guiChangeLanguageScreenController = new GuiChangeLanguageScreenController();
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
    
    private class GuiChangeLanguageScreenController {
        
        public GuiChangeLanguageScreenController () {
            GuiLanguagesScreenView.this.buttonFr.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    Expression.initExpression("fr", "FR");
                    GuiMasterView.setScreen(5);
                }
            });
            GuiLanguagesScreenView.this.buttonEn.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    Expression.initExpression("en", "EN");
                    GuiMasterView.setScreen(5);
                }
            });
            GuiLanguagesScreenView.this.buttonMainMenuReturn.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(1);
                }
            });
        }
        
    }
    
}