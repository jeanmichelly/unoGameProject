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
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

import java.util.Observable;
import java.util.Observer;

public class GuiLanguageScreenView implements Observer, Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonFr, buttonEn, buttonRetour;
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

        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_BUTTON_MENU);

        table = new Table();
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        buttonFr = new TextButton(Expression.getProperty("BUTTON_MENU_LANGAGE_FR"), skin);
        buttonEn = new TextButton(Expression.getProperty("BUTTON_MENU_LANGAGE_EN"), skin);
        buttonRetour = new TextButton(Expression.getProperty("BUTTON_MENU_RETURN"), skin);
        
        buttonFr.addListener(new ClickListener(){
           public void clicked(InputEvent event, float x, float y){
               Expression.initExpression("fr", "FR");
           }
        });
        buttonEn.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                Expression.initExpression("en", "EN");
            }
        });
        buttonRetour.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                GuiMasterView.setScreen(1);
            }
         });

        buttonFr.pad(15,40,15,40);
        buttonEn.pad(15,40,15,40);
        buttonRetour.pad(15,40,15,40);

        heading = new Label(Expression.getProperty("LABEL_TITLE_MENU_LANGUAGE"), skin);

        table.add(heading);
        table.getCell(heading).spaceBottom(70);
        table.row();

        table.add(buttonFr);
        table.getCell(buttonFr).spaceBottom(20);
        table.row();

        table.add(buttonEn);
        table.getCell(buttonEn).spaceBottom(20);
        table.row();

        table.add(buttonRetour);

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

    @Override
    public void update(Observable o, Object arg) {
    }
}
