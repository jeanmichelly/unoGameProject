package model.game_rules;

import model.BoardModel;
import model.card.CardModel;
import model.player.PlayerModel;

public class GameRulesStandardModel extends GameRulesModel {
    
    @Override
    public void countScore () {
        short score = 0;
        for ( PlayerModel player : BoardModel.getUniqueInstance().getPlayers() ) {
            for ( CardModel card : player.getPlayerHand().getCards() ) {
                score += card.getScore();
            }
        }
        score += BoardModel.getUniqueInstance().getPlayer().getScore();
        BoardModel.getUniqueInstance().getPlayer().setScore(score);
    }
    
    @Override
    public boolean isWinner () {
        if ( BoardModel.getUniqueInstance().getPlayer().getScore() >= GameRulesModel.SCORE_MAX )
            return true;
        return false;
    }
    
}
