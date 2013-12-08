package testUnitaire;

import static org.junit.Assert.*;
import model.UserModel;
import model.gameRules.GameRulesFactoryModel;
import org.junit.Test;

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
