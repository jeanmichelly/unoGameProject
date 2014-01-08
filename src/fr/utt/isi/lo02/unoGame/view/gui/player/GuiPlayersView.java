package fr.utt.isi.lo02.unoGame.view.gui.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.language.Expression;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.view.gui.utils.SkinLoader;
import fr.utt.isi.lo02.unoGame.view.gui.utils.TextureAtlasLoader;

public class GuiPlayersView {
    
    private Skin skinBoard;
    private Table playersTable;

    public GuiPlayersView () {
        initSkin();
        this.playersTable = new Table();
        this.playersTable.top();
        this.playersTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    public void initSkin () {
        this.skinBoard = new Skin(SkinLoader.SKIN_BOARD, TextureAtlasLoader.ATLAS_MENU);
    }
    
    public void build () {
        Label cellEmpty = new Label("", skinBoard, "playerName");
        cellEmpty.setFontScale(0.7f);

        this.playersTable.add(cellEmpty);
        this.playersTable.add(cellEmpty);
        Label numberGameRound = new Label(Expression.getProperty("LABEL_GAME")+" n°"+BoardModel.getUniqueInstance().getNumberGame()+
                " - "+Expression.getProperty("LABEL_ROUND")+" n°"+BoardModel.getUniqueInstance().getNumberRound(), skinBoard, "playerName");
        numberGameRound.setFontScale(0.7f);
        this.playersTable.add(numberGameRound);

        this.playersTable.row();
        this.playersTable.add(cellEmpty);
        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label score = new Label(String.valueOf(playerModel.getScore()), skinBoard, "playerName");
            score.setFontScale(0.7f);
            this.playersTable.add(score);
        }
        
        this.playersTable.row();
        Label antiClockWiseDirection = new Label("", skinBoard, "playerName");
        antiClockWiseDirection.setFontScale(0.7f);
        if ( BoardModel.getUniqueInstance().getDirectionOfPlay() == -1 ) {
            antiClockWiseDirection.setText("<-----");
        }
        this.playersTable.add(antiClockWiseDirection);

        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            GuiPlayerView playerView = new GuiPlayerView(playerModel);
            this.playersTable.add(playerView);
            this.playersTable.getCell(playerView).pad(0,18,0,18);
        }
        if ( BoardModel.getUniqueInstance().getDirectionOfPlay() == 1 ) {
            Label clockWiseDirection = new Label("----->", skinBoard, "playerName");
            clockWiseDirection.setFontScale(0.7f);
            this.playersTable.add(clockWiseDirection);
        }
        
        this.playersTable.row();
        this.playersTable.add(cellEmpty);
        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label pseudonym = new Label(playerModel.getPseudonym(), skinBoard, "playerName");
            pseudonym.setFontScale(0.7f);
            this.playersTable.add(pseudonym);
        }   
        
        this.playersTable.row();
        this.playersTable.add(cellEmpty);
        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label numberCards = new Label(String.valueOf(playerModel.getPlayerHand().getCards().size()), skinBoard, "playerName");
            numberCards.setFontScale(0.7f);
            this.playersTable.add(numberCards);
        }   
    }
    
    public Table getTable () {
        return this.playersTable;
    }
    
}
