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
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;

import fr.utt.isi.lo02.unoGame.TesteurGUI;
import fr.utt.isi.lo02.unoGame.controller.board.BoardController;
import fr.utt.isi.lo02.unoGame.controller.board.PlayerHandController;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiPacketView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiRibbonView;
import fr.utt.isi.lo02.unoGame.view.gui.player.GuiPlayerView;
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
    private GuiPacketView drawPile, discardPile;
    private Table discardPileTable, playersTable;
    private Table saveGameTable, askToPlayTable;
    private TweenManager tweenManager = new TweenManager();
    private TextButton buttonAskToPlay, buttonSaveGame;
    
    public GuiBoardScreenView (BoardModel boardModel, BoardController boardController) {
        this.boardModel = boardModel;
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
        this.boardModel = BoardModel.getUniqueInstance(); // Pour le load
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

    public void initCardRibbon () {
        this.cardRibbon = new GuiRibbonView(this.boardModel.getPlayer().getPlayerHand());
        PlayerHandController playerHandController = new PlayerHandController(this.boardModel.getPlayer().getPlayerHand(), this.cardRibbon);
        this.cardRibbon.addListener(playerHandController);
        
        this.cardRibbon.setTweenManager(this.tweenManager);
        this.cardRibbon.setBounds(Gdx.graphics.getWidth()/2 - 200, 0, 400, 180);
        this.cardRibbon.setCustomScale(.5f);
        this.cardRibbon.build();
    }

    public void initDiscardPile () {
        this.discardPile = new GuiPacketView(DiscardPileModel.getUniqueInstance().getCards());
        this.discardPile.setCustomScale(.5f);
        this.discardPile.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
        this.discardPile.build();
    }

    public void initDrawPile () {
        this.drawPile = new GuiPacketView(DrawPileModel.getUniqueInstance().getCards());
        this.drawPile.setCustomScale(.5f);
        this.drawPile.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
        this.drawPile.setFlipped(true);
        this.drawPile.build();
    }
    
    public void initButtons () {
        this.buttonSaveGame = new TextButton(Expression.getProperty("LABEL_SAVE_GAME"), skinMenu);
        this.buttonSaveGame.setName("SG");
        this.buttonSaveGame.pad(10, 20, 10, 20);
        
        this.buttonAskToPlay = new TextButton(Expression.getProperty("BUTTON_BOARD_PLAY_CARD"), skinMenu);
        this.buttonAskToPlay.pad(10, 20, 10, 20);
    }

    public void initTables () {
        this.discardPileTable = new Table();
        this.discardPileTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.discardPileTable.add(this.discardPile);
        this.discardPileTable.getCell(this.discardPile).pad(0,40,0,40);
        this.discardPileTable.debug();

        this.playersTable = new Table();
        this.playersTable.top();
        this.playersTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            GuiPlayerView playerView = new GuiPlayerView(playerModel);
            playerView.setSize(70,60);
            playerView.build();
            this.playersTable.add(playerView);
            this.playersTable.getCell(playerView).pad(0,18,0,18);
        }
        this.playersTable.row();

        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label pseudonym = new Label(playerModel.getPseudonym(), skinBoard, "playerName");
            pseudonym.setFontScale(0.7f);
            this.playersTable.add(pseudonym);
        }
        
        this.saveGameTable = new Table();
        this.saveGameTable.add(this.buttonSaveGame);
        this.saveGameTable.setPosition(1200, 700);
        
        this.askToPlayTable = new Table();
        this.askToPlayTable.add(buttonAskToPlay);
        this.askToPlayTable.setPosition(1100, 100);
    }

    public void initStage () {
        this.stage = new Stage();
        this.stage.addActor(this.cardRibbon);
        this.stage.addActor(this.discardPileTable);
        this.stage.addActor(this.drawPile);
        this.stage.addActor(this.playersTable);
        this.stage.addActor(saveGameTable);
        this.stage.addActor(askToPlayTable);
        Gdx.input.setInputProcessor(this.stage);
    }

    @Override
    public void show () {
        refreshBoardModel();
        initBatch();
        initSkin();
        initBoardBackGround();
        initCardRibbon();
        initDiscardPile();
        initDrawPile();
        initButtons();
        initTables();
        initStage();
        initListener();
    }

    public void promptToPlay () {



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

    }
    
}