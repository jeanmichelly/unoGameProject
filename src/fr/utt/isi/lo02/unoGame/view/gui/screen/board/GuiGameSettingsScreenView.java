package fr.utt.isi.lo02.unoGame.view.gui.screen.board;

import java.util.Observable;
import java.util.Observer;

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
import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiGameSettingsScreenView implements Screen, Observer {

    GameSettingsModel gameSettingsModel;
    GameSettingsController gameSettingsController;
    private Stage stage;
    private Skin skin;
    private Label heading;
    private TextButton buttonStartGame, buttonMainMenuReturn;
    private SelectBox selectBoxNumberPlayers, selectBoxNumberHumanPlayers;
    private CheckBox checkBoxes [] = new CheckBox[GameRulesFactoryModel.numberGameRules];
    Table checkBoxesTable;
    private Table table;
    
    public GuiGameSettingsScreenView(GameSettingsModel gameSettingsModel, GameSettingsController gameSettingsController) {
        super();
        this.gameSettingsModel = gameSettingsModel;
        gameSettingsModel.addObserver(this);
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
        this.selectBoxNumberHumanPlayers.addListener(gameSettingsController.getSelectBoxController());
        // TODO : écouter chaque checkbox pour déterminer le mode de jeu choisit
        for ( CheckBox checkBox : checkBoxes ) {
            checkBox.addListener(gameSettingsController.getCheckBoxController());
        }
    }
    
    public void initStage () {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
    }
    
    public void initSkin () {
        this.skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
    }
    
    public void initHeading () {
        this.heading = new Label(Expression.getProperty("LABEL_TITLE_GAME_CREATION"), skin, "heading");
    }
    
    public void initButtons () {
        this.buttonStartGame = new TextButton(Expression.getProperty("BUTTON_START_GAME"), skin);
        this.buttonMainMenuReturn = new TextButton(Expression.getProperty("BUTTON_MAIN_MENU_RETURN"), skin);
        
        this.buttonStartGame.setName("BSG");
        this.buttonMainMenuReturn.setName("BMMR");
        
        this.buttonStartGame.pad(15,40,15,40);
        this.buttonMainMenuReturn.pad(15,40,15,40);
    }
    
    public void initSelectBoxes () {
        int numberPlayersTotal = GameRulesModel.NUMBER_PLAYERS_MAX-GameRulesModel.NUMBER_PLAYERS_MIN+1;
        String [] choiceNumberPlayers = new String [numberPlayersTotal];
        for ( int i=0, j=GameRulesModel.NUMBER_PLAYERS_MIN; i<numberPlayersTotal; i++, j++) {
            choiceNumberPlayers[i] = String.valueOf(j);
        }
        this.selectBoxNumberPlayers = new SelectBox(choiceNumberPlayers, skin);
        this.selectBoxNumberPlayers.setName("NP");
        this.selectBoxNumberPlayers.setSelection(gameSettingsModel.getNumberPlayers()-GameRulesModel.NUMBER_PLAYERS_MIN);
        
        int numberHumanPlayersTotal = gameSettingsModel.getNumberPlayers()-GameRulesModel.NUMBER_PLAYERS_MIN+1;
        String [] choiceNumberHumanPlayers = new String [numberHumanPlayersTotal];
        for ( int i=0, j=GameRulesModel.NUMBER_PLAYERS_MIN; i<numberHumanPlayersTotal; i++, j++) {
            choiceNumberHumanPlayers[i] = String.valueOf(j);
        }
        this.selectBoxNumberHumanPlayers = new SelectBox(choiceNumberHumanPlayers, skin);
        this.selectBoxNumberHumanPlayers.setName("NHP");
    }
    
    public void initCheckBoxes () {
        this.checkBoxesTable = new Table();
        
        CheckBox standardBox= new CheckBox(Expression.getProperty("LABEL_GAME_RULES_ORIGINAL"), skin);
        CheckBox epicBox = new CheckBox(Expression.getProperty("LABEL_GAME_RULES_EPIC"), skin);
        
        standardBox.setName("s");
        epicBox.setName("e");
        
        this.checkBoxes[0] = standardBox;
        this.checkBoxes[1] = epicBox;
        
        ButtonGroup buttonGroup = new ButtonGroup();
        
        for ( CheckBox checkBox : this.checkBoxes ) {
            buttonGroup.add(checkBox);
            checkBoxesTable.add(checkBox).left();
            checkBoxesTable.row();
        }
    }
    
    public void initTable () {
        this.table = new Table();
        this.table.defaults().space(20);
        this.table.debug();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70).colspan(2);
        this.table.row();
        Label selectBoxNumberPlayersLabel = new Label(Expression.getProperty("LABEL_NUMBER_PLAYERS")+" : ", skin);
        this.table.add(selectBoxNumberPlayersLabel);
        this.table.add(selectBoxNumberPlayers);
        
        this.table.row();
        Label selectBoxNumberHumanPlayersLabel = new Label(Expression.getProperty("LABEL_NUMBER_HUMAN_PLAYERS")+" : ", skin);
        this.table.add(selectBoxNumberHumanPlayersLabel);
        this.table.add(selectBoxNumberHumanPlayers);
        
        this.table.row();
        Label checkBoxesLabel = new Label(Expression.getProperty("LABEL_VARIATIONS")+" : ", skin);
        this.table.add(checkBoxesLabel);
        this.table.add(checkBoxesTable);
        
        this.table.row();
        this.table.add(this.buttonStartGame);
        this.table.getCell(this.buttonStartGame).spaceTop(70).width(300);
        this.table.add(this.buttonMainMenuReturn);
        this.table.getCell(this.buttonMainMenuReturn).spaceTop(70);
    }

    @Override
    public void show () {
        initStage();
        initSkin();
        initHeading();
        initButtons();
        initSelectBoxes();
        initCheckBoxes();
        initTable();

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

    @Override
    public void update(Observable o, Object arg) {
        show();
    }

}