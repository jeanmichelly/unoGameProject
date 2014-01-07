package fr.utt.isi.lo02.unoGame.model.gameRules;

import java.io.Serializable;

import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;

/** 
 * <u>Pattern Fabrique : </u> </br> 
 * Permettre le choix dynamique du mode de jeu choisit par l'utilisateur.
 * @see GameRulesModel
 * @see GameRulesStandardModel
 */
public class GameRulesFactoryModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final int numberGameRules = 2;
    /**
     * Créer le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public GameRulesModel createGameRules () {
        switch ( GameSettingsModel.getUniqueInstance().getChoiceGameRules() ) {
            case 's' :
                return new GameRulesStandardModel();
            case 'f' : 
                return new GameRulesFastGameModel();
            default :
                return new GameRulesStandardModel();
        }
    }
    
}
