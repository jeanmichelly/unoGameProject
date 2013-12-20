package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

/**
 * Comporte le mode de jeu standard du jeu Uno.
 * @see GameRulesFactoryModel
 * @see GameRulesModel
 */
public class GameRulesStandardModel extends GameRulesModel {

    /**
     * Comptabilisation des points pour chaque joueur du mode de jeu standard.
     */
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
    
    /**
     * Definit le seuil a atteindre pour considerer qu'un joueur a gagne une partie.
     */
    public boolean isWinner () {
        return BoardModel.getUniqueInstance().getPlayer().getScore() >= GameRulesModel.SCORE_MAX;
    }
    
}
