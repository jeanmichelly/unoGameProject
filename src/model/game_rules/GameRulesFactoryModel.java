package model.game_rules;

import model.UserModel;

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
