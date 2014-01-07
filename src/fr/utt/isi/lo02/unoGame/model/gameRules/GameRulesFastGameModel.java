package fr.utt.isi.lo02.unoGame.model.gameRules;

import java.io.Serializable;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

public class GameRulesFastGameModel extends GameRulesModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void countScore() {
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
    public boolean isWinner() {
        return BoardModel.getUniqueInstance().getPlayer().getScore() >= GameRulesModel.SCORE_MAX / 2;
    }

}
