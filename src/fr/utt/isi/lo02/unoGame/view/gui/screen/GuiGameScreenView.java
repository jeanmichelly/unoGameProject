package fr.utt.isi.lo02.unoGame.view.gui.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiCardView;
import fr.utt.isi.lo02.unoGame.view.gui.card.GuiPlayerHandView;

public class GuiGameScreenView implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private SpriteBatch batch;
    private Group ribon;
    private BoardModel boardModel;

    public GuiGameScreenView() {
        this.boardModel = BoardModel.getUniqueInstance();
        UserModel.initNumberPlayers();
        UserModel.initNumberHumanPlayers();
        UserModel.initPseudonymsHumanPlayers();
        UserModel.initChoiceGameRules();
        this.boardModel.initGameRules();
        this.boardModel.createPlayers();
        this.boardModel.initHumanPlayers();
        this.boardModel.initComputerPlayers();
        this.boardModel.initGameRules();
        this.boardModel.chooseRandomDealer();
        try {
            this.boardModel.dispenseCards();
        } catch (InvalidActionPickCardException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        this.boardModel.initGame();
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(v);
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

        atlas = new TextureAtlas("ressources/img/card/symbols.pack");
        skin = new Skin();

        ribon = new GuiPlayerHandView(BoardModel.getUniqueInstance().getPlayer(0).getPlayerHand(),Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,200,80);

        stage.addActor(ribon);
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
