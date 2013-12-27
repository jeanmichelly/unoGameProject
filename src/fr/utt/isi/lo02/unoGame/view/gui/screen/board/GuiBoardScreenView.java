package fr.utt.isi.lo02.unoGame.view.gui.screen.board;

import java.util.Observable;
import java.util.Observer;

import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;

import fr.utt.isi.lo02.unoGame.TesteurGUI;
import fr.utt.isi.lo02.unoGame.controller.board.BoardController;
import fr.utt.isi.lo02.unoGame.controller.board.PlayerHandController;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.view.gui.deck.GuiDiscardPileView;
import fr.utt.isi.lo02.unoGame.view.gui.deck.GuiDrawPileView;
import fr.utt.isi.lo02.unoGame.view.gui.deck.GuiRibbonView;
import fr.utt.isi.lo02.unoGame.view.gui.player.GuiPlayersView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiBoardScreenView implements Observer, Screen {

    BoardModel boardModel;
    BoardController boardController;
    private Stage stage;
    Skin skinBoard, skinMenu; 
    private Sprite boardBackground;
    private SpriteBatch batch;
    private GuiRibbonView cardRibbon;
    private GuiDiscardPileView discardPile;
    private GuiDrawPileView drawPile;
    private GuiPlayersView players;
    private Table saveGameTable;
    private TweenManager tweenManager = new TweenManager();
    private TextButton buttonSaveGame;
    
    public GuiBoardScreenView (BoardModel boardModel, BoardController boardController) {
        this.boardModel = boardModel;
        this.boardModel.addObserver(this);
        this.boardController = boardController;
    }

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        this.stage.act(v);
        this.batch.begin();
        this.boardBackground.draw(this.batch);
        this.batch.end();

        this.stage.draw();

        this.tweenManager.update(v);
    }

    @Override
    public void resize (int i, int i2) {
        Vector2 size = Scaling.fit.apply(TesteurGUI.APPLICATION_WIDTH, TesteurGUI.APPLICATION_HEIGHT, i, i2);
        int viewportX = (int)(i - size.x) / 2;
        int viewportY = (int)(i2 - size.y) / 2;
        int viewportWidth = (int)size.x;
        int viewportHeight = (int)size.y;
        Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
        this.stage.setViewport(TesteurGUI.APPLICATION_WIDTH, TesteurGUI.APPLICATION_HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);
    }
    
    public void initListener () {
        this.buttonSaveGame.addListener(boardController.getButtonController());
    }

    public void refreshBoardModel () {
        this.boardModel = BoardModel.getUniqueInstance();
    }

    public void initBatch () {
        this.batch = new SpriteBatch();
    }

    public void initBoardBackGround () {
        this.boardBackground = new Sprite(new Texture(Gdx.files.internal("ressources/img/misc/boardBackground.png")));
        this.boardBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void initSkin () {
        this.skinBoard = new Skin(SkinLoader.SKIN_BOARD, TextureAtlasLoader.ATLAS_MENU);
        this.skinMenu = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
    }

    public void initComponentView () {
        this.discardPile = new GuiDiscardPileView();
        this.drawPile = new GuiDrawPileView();
        
        this.cardRibbon = new GuiRibbonView(this.boardModel.getPlayer().getPlayerHand(), this.tweenManager);
        PlayerHandController playerHandController = new PlayerHandController(this.boardModel.getPlayer().getPlayerHand(), this.cardRibbon);
        this.cardRibbon.addListener(playerHandController);
        
        this.players = new GuiPlayersView();  
    }

    public void build () {
        this.discardPile.build();
        this.drawPile.build();
        this.cardRibbon.build();
        this.players.build();
    }

    public void initButtons () {
        this.buttonSaveGame = new TextButton(Expression.getProperty("LABEL_SAVE_GAME"), skinMenu);
        this.buttonSaveGame.setName("SG");
        this.buttonSaveGame.pad(10, 20, 10, 20);
    }

    public void initTables () {
        this.saveGameTable = new Table();
        this.saveGameTable.add(this.buttonSaveGame);
        this.saveGameTable.setPosition(1200, 700);
    }

    public void initStage () {
        this.stage = new Stage();
        this.stage.addActor(this.cardRibbon);
        this.stage.addActor(this.discardPile.getTable());
        this.stage.addActor(this.drawPile);
        this.stage.addActor(this.players.getTable());
        this.stage.addActor(saveGameTable);
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void show () {
        refreshBoardModel();
        initBatch();
        initSkin();
        initBoardBackGround();
        initComponentView();
        build();
        initButtons();
        initTables();
        initStage();
        initListener();
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
    public void update (Observable o, Object arg) {
        this.show();
    }
    
}