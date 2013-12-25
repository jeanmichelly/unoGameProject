package fr.utt.isi.lo02.unoGame.view.gui.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
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
        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label score = new Label(String.valueOf(playerModel.getScore()), skinBoard, "playerName");
            score.setFontScale(0.7f);
            this.playersTable.add(score);
        }
        this.playersTable.row();
        
        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            GuiPlayerView playerView = new GuiPlayerView(playerModel);
            this.playersTable.add(playerView);
            this.playersTable.getCell(playerView).pad(0,18,0,18);
        }
        this.playersTable.row();

        for ( PlayerModel playerModel : BoardModel.getUniqueInstance().getPlayers() ) {
            Label pseudonym = new Label(playerModel.getPseudonym(), skinBoard, "playerName");
            pseudonym.setFontScale(0.7f);
            this.playersTable.add(pseudonym);
        }    
    }
    
    public Table getTable () {
        return this.playersTable;
    }
    
}
