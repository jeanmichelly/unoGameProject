package model.game_rules;

import model.BoardModel;
import model.card.CardModel;
import model.player.PlayerModel;

public class GameRulesStandardModel extends GameRulesModel {
    
    @Override
    public void countScoresEachPlayers () {
        short score;
        for ( PlayerModel player : BoardModel.getUniqueInstance().getPlayers() ) {
            score = 0;
            for ( CardModel card : player.getPlayerHand().getCards() ) {
                score += card.getScore();
            }
            score += player.getScore();
            if ( isWinner(score) )
                BoardModel.getUniqueInstance().setPlayerWinner(player.getPseudonym());
            player.setScore(score);
        }
    }
    
    @Override
    public boolean isWinner (short score) {
        if ( score >= GameRulesModel.SCORE_MAX ) {
            BoardModel.getUniqueInstance().setHasWinner(true);
            return true;
        }
        return false;
    }
    
}
