package fr.utt.isi.lo02.unoGame.view.gui.screen;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Scaling;
import fr.utt.isi.lo02.unoGame.TesteurGUI;
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

import java.util.Observable;
import java.util.Observer;

public class GuiBoardScreenView implements Observer, Screen {

    private Stage stage;
    private Sprite boardBackground;
    private SpriteBatch batch;
    private GuiRibbonView cardRibbon;
    private GuiPacketView drawPile, discardPile;
    private Table discardPileTable;
    private Table playersTable;
    private TweenManager tweenManager = new TweenManager();

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);

        stage.act(v);
        batch.begin();
            boardBackground.draw(batch);
        batch.end();

        stage.draw();

        tweenManager.update(v);
    }

    @Override
    public void resize(int i, int i2) {
        Vector2 size = Scaling.fit.apply(TesteurGUI.APPLICATION_WIDTH, TesteurGUI.APPLICATION_HEIGHT, i, i2);
        int viewportX = (int)(i - size.x) / 2;
        int viewportY = (int)(i2 - size.y) / 2;
        int viewportWidth = (int)size.x;
        int viewportHeight = (int)size.y;
        Gdx.gl.glViewport(viewportX, viewportY, viewportWidth, viewportHeight);
        stage.setViewport(TesteurGUI.APPLICATION_WIDTH, TesteurGUI.APPLICATION_HEIGHT, true, viewportX, viewportY, viewportWidth, viewportHeight);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        boardBackground = new Sprite(new Texture(Gdx.files.internal("ressources/img/misc/boardBackground.png")));
        boardBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        cardRibbon = new GuiRibbonView(BoardModel.getUniqueInstance().getPlayer(0).getPlayerHand());
        cardRibbon.setTweenManager(tweenManager);
        cardRibbon.setBounds(Gdx.graphics.getWidth()/2 - 200, 0, 400, 180);
        cardRibbon.setCustomScale(.5f);
        cardRibbon.build();

        discardPile = new GuiPacketView(DiscardPileModel.getUniqueInstance().getCards());
        discardPile.setCustomScale(.5f);
        discardPile.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
        discardPile.build();

        drawPile = new GuiPacketView(DrawPileModel.getUniqueInstance().getCards());
        drawPile.setCustomScale(.5f);
        drawPile.setSize(GuiCardView.NATIVE_CARD_WIDTH * .5f, GuiCardView.NATIVE_CARD_HEIGHT * .5f);
        drawPile.setFlipped(true);
        drawPile.build();

        discardPileTable = new Table();
        discardPileTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        discardPileTable.add(discardPile);
        discardPileTable.getCell(discardPile).pad(0,40,0,40);
        discardPileTable.debug();

        playersTable = new Table();
        playersTable.top();
        playersTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers()){
            GuiPlayerView playerView = new GuiPlayerView(playerModel);
            playerView.setSize(70,60);
            playerView.build();
            playersTable.add(playerView);
            playersTable.getCell(playerView).pad(0,18,0,18);
        }

        playersTable.row();

        for(PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers()){
            Skin skin = new Skin(SkinLoader.SKIN_BOARD, TextureAtlasLoader.ATLAS_BUTTON_MENU);
            Label pseudonym = new Label(playerModel.getPseudonym(), skin, "playerName");
            pseudonym.setFontScale(0.7f);
            playersTable.add(pseudonym);
        }

        stage.addActor(cardRibbon);
        stage.addActor(discardPileTable);
        stage.addActor(drawPile);
        stage.addActor(playersTable);

        cardRibbon.addListener(new InputListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Actor actor = cardRibbon.hit(x,y,true);
                if(actor instanceof GuiCardView){
                    GuiCardView cardView = (GuiCardView) actor;
                    if(cardRibbon.getUppedCard() != null && cardRibbon.getUppedCard().equals(actor) && !cardRibbon.isSelected(cardView)){
                        cardRibbon.setSelected(cardView);
                        promptToPlay();
                    }
                    else if(cardRibbon.getUppedCard() != null && cardRibbon.getUppedCard().equals(actor) && cardRibbon.isSelected(cardView)){
                        cardRibbon.resetCardSelected();
                    }
                }
                return super.touchDown(event, x, y, pointer, button);    //To change body of overridden methods use File | Settings | File Templates.
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                Actor enteredActor = cardRibbon.hit(x,y,true);
                if(enteredActor instanceof GuiCardView){
                    GuiCardView cardView = (GuiCardView) enteredActor;
                    if(cardRibbon.getUppedCard() == null){
                        cardRibbon.setCardUpped(cardView);
                    }
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(cardRibbon.getUppedCard() != null && !cardRibbon.hasSelectedCard()){
                    cardRibbon.resetCardUpped();
                }
            }

        });
    }

    public void promptToPlay(){
        Skin skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_BUTTON_MENU);
        TextButton askToPlay = new TextButton(Expression.getProperty("BUTTON_BOARD_PLAY_CARD"), skin);
        askToPlay.pad(10, 20, 10, 20);

        Table table = new Table();
        table.add(askToPlay);
        table.setPosition(1100, 100);
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
