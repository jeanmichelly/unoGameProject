package fr.utt.isi.lo02.unoGame.view.gui.board;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import fr.utt.isi.lo02.unoGame.controller.board.GameSettingsController;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiGameSettingsView implements Screen {

    GameSettingsModel gameSettingsModel;
    GameSettingsController gameSettingsController;
    private Stage stage;
    private Table table;
    private TextButton buttonStartGame, buttonMainMenuReturn;
    private Label heading;
    private SelectBox selectBoxNumberPlayers;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();
    
    public GuiGameSettingsView(GameSettingsModel gameSettingsModel, GameSettingsController gameSettingsController) {
        super();
        this.gameSettingsModel = gameSettingsModel;
        this.gameSettingsController = gameSettingsController;
    }

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act(v);
        this.stage.draw();
//        Table.drawDebug(this.stage);
    }

    @Override
    public void resize (int i, int i2) {
        this.stage.setViewport(i, i2, true);
        this.table.invalidateHierarchy();
        this.table.setSize(i,i2);
    }
    
    public void initListener() { // Obligé de le faire dans show donc vue comporte le controller
        this.buttonStartGame.addListener(gameSettingsController.getButtonController());
        this.buttonMainMenuReturn.addListener(gameSettingsController.getButtonController());
        this.selectBoxNumberPlayers.addListener(gameSettingsController.getSelectBoxController());
        // TODO : écouter chaque checkbox pour déterminer le mode de jeu choisit
        for ( CheckBox checkBox : checkBoxes ) {
            checkBox.addListener(gameSettingsController.getCheckBoxController());
        }
    }

    @Override
    public void show () {
        this.stage = new Stage();

        Gdx.input.setInputProcessor(this.stage);

        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);

        this.table = new Table();
        this.table.defaults().space(20);
        this.table.debug();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.buttonStartGame = new TextButton(Expression.getProperty("BUTTON_START_GAME"), skin);
        this.buttonMainMenuReturn = new TextButton(Expression.getProperty("BUTTON_MAIN_MENU_RETURN"), skin);
        
        this.buttonStartGame.setName("BSG");
        this.buttonMainMenuReturn.setName("BMMR");
        
        this.buttonStartGame.pad(15,40,15,40);
        this.buttonMainMenuReturn.pad(15,40,15,40);

        this.heading = new Label(Expression.getProperty("LABEL_TITLE_GAME_CREATION"), skin, "heading");
        this.table.add(this.heading);
        this.table.getCell(this.heading).spaceBottom(70).colspan(2);
        this.table.row();

        String [] choiceNumberPlayers = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        this.selectBoxNumberPlayers = new SelectBox(choiceNumberPlayers, skin);
        Label selectBoxLabel = new Label("Nombre de joueurs : ", skin);
        this.table.add(selectBoxLabel);
        this.table.add(selectBoxNumberPlayers);
        this.table.row();

        Label checkBoxesLabel = new Label("Mode de jeu : ", skin);
        this.table.add(checkBoxesLabel);

        Table checkboxesTable = new Table();
        
        CheckBox standardBox= new CheckBox("Standard", skin);
        CheckBox epicBox = new CheckBox("Epic", skin);
        standardBox.setName("s");
        epicBox.setName("e");
        
        this.checkBoxes.add(standardBox);
        this.checkBoxes.add(epicBox);
        
        ButtonGroup buttonGroup = new ButtonGroup();
        
        for ( CheckBox checkBox : this.checkBoxes ) {
            buttonGroup.add(checkBox);
            checkboxesTable.add(checkBox).left();
            checkboxesTable.row();
        }

        this.table.add(checkboxesTable);
        this.table.row();

        this.table.add(this.buttonStartGame);
        this.table.getCell(this.buttonStartGame).spaceTop(70).width(300);

        this.table.add(this.buttonMainMenuReturn);
        this.table.getCell(this.buttonMainMenuReturn).spaceTop(70);
        
        initListener();
        
        this.stage.addActor(this.table);
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

}