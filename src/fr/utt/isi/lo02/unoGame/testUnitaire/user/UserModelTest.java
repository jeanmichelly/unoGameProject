package fr.utt.isi.lo02.unoGame.testUnitaire.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;

public class UserModelTest {

    @Test
    public void initHumanPlayersTest () {
        UserModel.initNumberHumanPlayers();
        UserModel.initPseudonymsHumanPlayers();
                
        assertEquals(UserModel.getNumberHumanPlayers(), UserModel.getPseudonymsHumanPlayer().size());
    }
    
    @Test
    public void initChoiceGameRulesTest () {
        UserModel.initChoiceGameRules();
        
        assertNotNull(new GameRulesFactoryModel().createGameRules());
    }
}
