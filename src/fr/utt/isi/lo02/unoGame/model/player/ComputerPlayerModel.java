package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityColorStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityNumberStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PrioritySpecialityStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.RandomStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.StrategyModel;

/**
 * 
 * Cette classe represente les joueurs ordinateurs. Les joueurs ordinateurs ont une intelligence artificielle et peuvent alors, jouer automatiquement.
 *
 */
public class ComputerPlayerModel extends PlayerModel {

    private StrategyModel [] strategy;
    
    public ComputerPlayerModel (String pseudonym) {
        super(pseudonym);
        strategy = new StrategyModel[4];
        strategy[0] = new PriorityColorStrategyModel(); 
        strategy[1] = new PriorityNumberStrategyModel();
        strategy[2] = new PrioritySpecialityStrategyModel();
        strategy[3] = new RandomStrategyModel();
    }
    
    @Override
    public void play () {
        if ( BoardModel.getUniqueInstance().getNextPlayer().getPlayerHand().size() < 3 )
            strategy[2].execute();
    }

    @Override
    public void putDownCard() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void chooseColor() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void signalUno() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void againstUno() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void challengeAgainstWildDrawFourCard() {
        // TODO Auto-generated method stub
        
    }
    
}
