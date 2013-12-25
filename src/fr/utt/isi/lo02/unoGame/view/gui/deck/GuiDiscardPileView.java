package fr.utt.isi.lo02.unoGame.view.gui.deck;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;

public class GuiDiscardPileView extends GuiPacketView {
    
    private Table discardPileTable;

    public GuiDiscardPileView () {
        super(DiscardPileModel.getUniqueInstance().getCards());
        this.discardPileTable = new Table();
        this.discardPileTable.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.discardPileTable.add(this);
        this.discardPileTable.getCell(this).pad(0,40,0,40);
        this.discardPileTable.debug();
    }
    
    public Table getTable () {
        return this.discardPileTable;
    }

}
