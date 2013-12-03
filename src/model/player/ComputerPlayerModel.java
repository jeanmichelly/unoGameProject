package model.player;

import model.player.strategy.PriorityColorStrategyModel;
import model.player.strategy.PriorityNumberStrategyModel;
import model.player.strategy.PrioritySpecialityStrategyModel;
import model.player.strategy.RandomStrategyModel;
import model.player.strategy.StrategyModel;

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
