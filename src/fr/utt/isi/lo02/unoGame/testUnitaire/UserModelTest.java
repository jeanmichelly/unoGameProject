package fr.utt.isi.lo02.unoGame.testUnitaire;

import static org.junit.Assert.*;
import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.UserModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;

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
