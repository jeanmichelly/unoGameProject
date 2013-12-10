package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.UserModel;

/**
 * 
 * Cette classe utilise le patron fabrique pour permettre le choix dynamique du mode de jeu choisit par l'utilisateur.
 *
 */
public class GameRulesFactoryModel {

    public GameRulesModel createGameRules () {
        switch( UserModel.getChoiceGameRules() ) {
            case 's' :
                return new GameRulesStandardModel();
            default :
                return null;
        }
    }
    
}
