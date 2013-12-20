package fr.utt.isi.lo02.unoGame.view.gui.screen;

import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiPacketView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiPlayerHandView;
import fr.utt.isi.lo02.unoGame.view.gui.player.GuiPlayerView;

public class GuiGameScreenView implements Screen {

    private Stage stage;
    private Sprite boardBackground;
    private SpriteBatch batch;
    private GuiPlayerHandView cardRibon;
    private GuiPacketView discardPile;
    private GuiPacketView drawPile, drawPile2;
    private Table discardPileTable;
    private Table playersTable;
    private TweenManager tweenManager;

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(v);
        batch.begin();
            boardBackground.draw(batch);
        batch.end();

        Table.drawDebug(stage);

        stage.draw();
    }

    @Override
    public void resize(int i, int i2) {

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        boardBackground = new Sprite(new Texture(Gdx.files.internal("ressources/img/misc/boardBackground.png")));
        boardBackground.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        cardRibon = new GuiPlayerHandView(BoardModel.getUniqueInstance().getPlayer(0).getPlayerHand());
        cardRibon.setBounds(Gdx.graphics.getWidth()/2 - 200, 0, 400, 180);
        cardRibon.setCustomScale(.5f);
        cardRibon.build();

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
        discardPileTable.add(drawPile);
        discardPileTable.getCell(drawPile).pad(0,40,0,40);
        discardPileTable.debug();

        playersTable = new Table();
        playersTable.top();
        playersTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for(PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers()){
            GuiPlayerView playerView = new GuiPlayerView(playerModel);
            playerView.setSize(100,100);
            playerView.build();
            playersTable.add(playerView);
            playersTable.getCell(playerView).pad(0,40,0,40);
        }

        playersTable.row();

        for(PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers()){
            BitmapFont font = new BitmapFont(Gdx.files.internal("ressources/font/whiteGeoSansLight.fnt"));
            Label.LabelStyle style = new Label.LabelStyle();
            Label text;
            style.font = font;
            text = new Label(playerModel.getPseudonym(),style);
            playersTable.add(text);
        }

        stage.addActor(cardRibon);
        stage.addActor(discardPileTable);
        stage.addActor(playersTable);

        cardRibon.addListener(new InputListener(){
            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(fromActor instanceof  GuiCardView){
                    GuiCardView cardView = (GuiCardView) fromActor;
                    cardView.toggleUpped();
                }
            }
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(toActor instanceof  GuiCardView){
                    GuiCardView cardView = (GuiCardView) toActor;
                    cardView.toggleUpped();
                }
            }
        });
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
}
