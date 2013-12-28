package fr.utt.isi.lo02.unoGame.view.gui.screen.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.GuiMasterView;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiScoringScreenView implements Screen {

    GuiScoringScreenController guiScoringScreenController;
    private Stage stage;
    private Skin skin;
    private Label heading, labelWinnerRound, labelWinnerGame, labelPseudonym, labelScore;
    private TextButton nextRound, nextGame, buttonMainMenuReturn;
    private Table table, scoresTable;

    @Override
    public void render (float v) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        this.stage.act(v);
        this.stage.draw();
    }

    @Override
    public void resize (int i, int i2) {
        this.stage.setViewport(i, i2, true);
        this.table.invalidateHierarchy();
        this.table.setSize(i,i2);
    }
    
    public void initSkin () {
        this.skin = new Skin(SkinLoader.SKIN_MENU, TextureAtlasLoader.ATLAS_MENU);
    }
    
    public void initLabels () {
        this.heading = new Label(Expression.getProperty("LABEL_TITLE_SCORING"), skin, "heading");
        this.labelWinnerRound = new Label(Expression.getProperty("LABEL_WINNER_ROUND")+" : ", skin);
        this.labelWinnerGame = new Label(Expression.getProperty("LABEL_WINNER_GAME"), skin);
        this.labelPseudonym = new Label(Expression.getProperty("LABEL_PSEUDONYM")+" : ", skin);
        this.labelScore = new Label(Expression.getProperty("LABEL_SCORE")+" : ", skin);
    }
    
    public void initButtons () {
        this.nextRound = new TextButton(Expression.getProperty("BUTTON_NEXT_ROUND"), skin);
        this.nextGame = new TextButton(Expression.getProperty("BUTTON_NEXT_GAME"), skin);
        this.buttonMainMenuReturn = new TextButton(Expression.getProperty("BUTTON_MAIN_MENU_RETURN"), skin);

        this.nextRound.pad(15,40,15,40);
        this.nextGame.pad(15,40,15,40);
        this.buttonMainMenuReturn.pad(15,40,15,40);
    } 
    
    public void initTables () {
        this.table = new Table();
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.table.add(this.heading);
        
        this.table.getCell(this.heading).spaceBottom(70).colspan(2);
        this.table.row();
        this.table.add(this.labelWinnerRound);
        this.table.add(new Label(BoardModel.getUniqueInstance().getPlayer().getPseudonym(), skin));
        
        for ( int i=0; i<BoardModel.getUniqueInstance().getPlayersWinnerGame().size(); i++ ) {
            this.table.getCell(this.heading).spaceBottom(70).colspan(2);
            this.table.row();
            this.table.add(new Label(labelWinnerGame.getText()+" n°"+(i+1)+" : ", skin));
            this.table.add(new Label(BoardModel.getUniqueInstance().getPlayersWinnerGame().get(i), skin));
        }
        
        this.table.row();
        scoresTable = new Table();
        this.scoresTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.scoresTable.add(labelPseudonym);
        for ( PlayerModel player : BoardModel.getUniqueInstance().getPlayers() ) {
            this.scoresTable.add(new Label(player.getPseudonym()+"  ", skin));
        }
        this.scoresTable.row();
        this.scoresTable.add(labelScore);
        for ( PlayerModel player : BoardModel.getUniqueInstance().getPlayers() ) {
            this.scoresTable.add(new Label(String.valueOf(player.getScore()), skin));
        }
        
        this.table.add(scoresTable);
        
        this.table.getCell(this.scoresTable).spaceBottom(20);
        this.table.row();
        this.table.add(this.nextRound);
        
        this.table.getCell(this.nextRound).spaceBottom(20);
        this.table.row();
        this.table.add(this.nextGame);
        
        this.table.getCell(this.nextGame).spaceBottom(20);
        this.table.row();
        this.table.add(this.buttonMainMenuReturn);
    }
    
    public void initStage () {
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);  
        this.stage.addActor(this.table);
    }

    @Override
    public void show () {
        initSkin();
        initLabels();
        initButtons();
        initTables();
        initStage();  
        this.guiScoringScreenController = new GuiScoringScreenController();
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
    
    private class GuiScoringScreenController {
        
        public GuiScoringScreenController () {
            GuiScoringScreenView.this.nextRound.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    try {
                        BoardModel.getUniqueInstance().nextRound();
                    } catch (InvalidActionPickCardException e) {
                        e.printStackTrace();
                    }
                    GuiMasterView.setScreen(3);
                }
            });
            GuiScoringScreenView.this.nextGame.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    try {
                        BoardModel.getUniqueInstance().nextGame();
                    } catch (InvalidActionPickCardException e) {
                        e.printStackTrace();
                    }
                    GuiMasterView.setScreen(3);
                }
            });
            GuiScoringScreenView.this.buttonMainMenuReturn.addListener( new ClickListener() {
                public void clicked (InputEvent event, float x, float y) {
                    GuiMasterView.setScreen(1);
                }
            });
        }
        
    }
    
}