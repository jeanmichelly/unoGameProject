package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;
import fr.utt.isi.lo02.unoGame.model.BoardModel;

public class ConsolePlayerHandView implements Observer {
        
    public ConsolePlayerHandView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    protected static String getHeads () {
        return ConsoleDeckView.getHeads();
    }
    
    protected static String getMiddles () {
        return ConsoleDeckView.getMiddles();
    }
    
    protected static String getTails () {
        return ConsoleDeckView.getTails();
    }
    
    protected static String getBelowTails () {
        return ConsoleDeckView.getBelowTails();
    }
    
    protected static String getIndexs () {
        return ConsoleDeckView.getIndexs();
    }
    
    public static String build () {
        return ConsoleDeckView.build(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards());
    }

    @Override
    public void update(Observable o, Object arg) {
        ConsoleDeckView.build(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards());   
    }
    
}
