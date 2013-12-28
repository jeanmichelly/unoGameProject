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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;

import fr.utt.isi.lo02.unoGame.TesteurGUI;
import fr.utt.isi.lo02.unoGame.controller.board.BoardController;
import fr.utt.isi.lo02.unoGame.controller.board.ComputerPlayerController;
import fr.utt.isi.lo02.unoGame.controller.board.DrawPileController;
import fr.utt.isi.lo02.unoGame.controller.board.HumanPlayerController;
import fr.utt.isi.lo02.unoGame.controller.board.PlayerHandController;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
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
    private Image choiceColors;
    private Table saveGameTable, notToPlayTable, choiceColorsTable;
    private TweenManager tweenManager = new TweenManager();
    private TextButton buttonSaveGame, buttonNotToPlay;
    public static boolean colors = false;
    
    public GuiBoardScreenView (BoardModel boardModel, BoardController boardController) {
        this.boardModel = boardModel;
        this.boardController = boardController;
        this.boardModel.addObserver(this);
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
        
        if ( BoardModel.getUniqueInstance().getPlayer() instanceof HumanPlayerModel ) {
            DrawPileController drawPileController = new DrawPileController(
                    DrawPileModel.getUniqueInstance(), this.drawPile);
                this.drawPile.addListener(drawPileController);
            PlayerHandController playerHandController = new PlayerHandController(
                this.boardModel.getPlayer().getPlayerHand(), this.cardRibbon);
            this.cardRibbon.addListener(playerHandController);
        }
        
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
        
        HumanPlayerController humanPlayerController = new HumanPlayerController(
                (HumanPlayerModel)BoardModel.getUniqueInstance().getPlayer());
        this.buttonNotToPlay = new TextButton ("Next", skinMenu);
        this.buttonNotToPlay.setName("NTP");
        this.buttonNotToPlay.pad(10, 20, 10, 20);
        this.buttonNotToPlay.setVisible(false);
        this.buttonNotToPlay.addListener(humanPlayerController);

        Texture colorsTexture = new Texture(Gdx.files.internal("ressources/img/card/colors.png"));
        this.choiceColors = new Image(colorsTexture);
        this.choiceColors.setBounds(0, 0, 200, 200);
        this.choiceColors.setName("CC");
        this.choiceColors.addListener(humanPlayerController);
        this.choiceColors.setVisible(colors);
    }

    public void initTables () {
        this.saveGameTable = new Table();
        this.saveGameTable.add(this.buttonSaveGame);
        this.saveGameTable.setPosition(1200, 700);
        
        this.notToPlayTable = new Table();
        this.notToPlayTable.add(this.buttonNotToPlay);
        this.notToPlayTable.setPosition(1200, 100);
        
        this.choiceColorsTable = new Table();
        this.choiceColorsTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.choiceColorsTable.add(choiceColors);
        this.choiceColorsTable.getCell(choiceColors).pad(0, 40, 0, 40);
    }

    public void initStage () {
        this.stage = new Stage();
        this.stage.addActor(this.cardRibbon);
        this.stage.addActor(this.discardPile.getTable());
        this.stage.addActor(this.drawPile);
        this.stage.addActor(this.players.getTable());
        if ( BoardModel.getUniqueInstance().getPlayer() instanceof HumanPlayerModel ) {
            this.stage.addActor(this.saveGameTable);
            this.stage.addActor(this.notToPlayTable);
            this.stage.addActor(this.choiceColorsTable);
        }
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
        if ( BoardModel.getUniqueInstance().getPlayer() instanceof HumanPlayerModel ) {
            initButtons();
            initTables();
            initListener();
        } else {
            ComputerPlayerController computerPlayerController = new ComputerPlayerController(
                    (ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer());
            computerPlayerController.play();
        }
        initStage();
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
        if ( arg instanceof String && arg.equals("CC") ) {
            colors = true;
        } else {
            if ( !DiscardPileModel.getUniqueInstance().hasApplyEffectLastCard() ) { 
                try {
                    BoardModel.getUniqueInstance().applyCardEffect();
                } catch (InvalidActionPickCardException | InvalidColorModelException e) {
                    e.printStackTrace();
                }
            }
            BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            this.show();
            if ( !DrawPileModel.getUniqueInstance().isDrawable() ) {
                this.buttonNotToPlay.setVisible(true);
                drawPile.setHighlited(false);
            }
        }
    }
    
}