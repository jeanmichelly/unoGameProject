package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.user.UserModel;

/** 
 * <u>Pattern Fabrique : </u> </br> 
 * Permettre le choix dynamique du mode de jeu choisit par l'utilisateur.
 */
public class GameRulesFactoryModel {

    /**
     * Creer le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public GameRulesModel createGameRules () {
        switch( UserModel.getChoiceGameRules() ) {
            case 's' :
                return new GameRulesStandardModel();
            default :
                return null;
        }
    }
    
}
