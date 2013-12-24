package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameParametersModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

import java.util.ArrayList;

public class GuiGameCreationScreenView implements Screen {

    GuiGameCreationScreenController gameCreationScreenController;
    private Stage stage;
    private Table table;
    private TextButton buttonStartGame, buttonMainMenuReturn;
    private Label heading;
    private SelectBox selectBoxNumberPlayers;
    private ArrayList<CheckBox> checkBoxes = new ArrayList<CheckBox>();

    private byte numberOfPlayers = 2;

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
        this.checkBoxes.add(new CheckBox("Standard", skin));
        this.checkBoxes.add(new CheckBox("Epic", skin));
        ButtonGroup buttonGroup = new ButtonGroup();

        for(CheckBox checkBox : this.checkBoxes){
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

        this.stage.addActor(this.table);
        
        this.gameCreationScreenController = new GuiGameCreationScreenController();
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

    public void createGame() throws InvalidActionPickCardException {
        ConsoleGameParametersModel.initNumberPlayers(this.numberOfPlayers);
        ConsoleGameParametersModel.initNumberHumanPlayers();
        ConsoleGameParametersModel.initPseudonymsHumanPlayers();
        ConsoleGameParametersModel.initChoiceGameRules();
        BoardModel boardModel = BoardModel.getUniqueInstance();
        boardModel.initGameRules();
        boardModel.createPlayers();
        boardModel.initHumanPlayers();
        boardModel.initComputerPlayers();
        boardModel.chooseRandomDealer();
        boardModel.dispenseCards();
        boardModel.initGame();
    }
    
    private class GuiGameCreationScreenController {
        
        public GuiGameCreationScreenController () {
            GuiGameCreationScreenView.this.selectBoxNumberPlayers.addListener( new ChangeListener() {
                @Override
                public void changed(ChangeEvent changeEvent, Actor actor) {
                    if(actor instanceof SelectBox){
                        SelectBox selectBox = (SelectBox) actor;
                        int numberOfPlayers = Integer.parseInt(selectBox.getSelection());
                        GuiGameCreationScreenView.this.numberOfPlayers = (byte) numberOfPlayers;
                    }
                }
            });
            // TODO : écouter chaque checkbox pour déterminer le mode de jeu choisit
            for(CheckBox checkBox : GuiGameCreationScreenView.this.checkBoxes){
                checkBox.addListener( new InputListener() {
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        return true;
                    }
                });
            }
            GuiGameCreationScreenView.this.buttonStartGame.addListener( new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                try {
                    GuiGameCreationScreenView.this.createGame();
                } catch (InvalidActionPickCardException e) {
                    e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                }
                GuiMasterView.setScreen(3);
            }
            });
            GuiGameCreationScreenView.this.buttonMainMenuReturn.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(1);
                }
            }); 
        }
        
    }
    
}