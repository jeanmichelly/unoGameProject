package fr.utt.isi.lo02.unoGame.testUnitaire.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.GameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;

public class GameSettingsTest {

    @Test
    public void initHumanPlayersTest () {
        GameSettingsModel.getUniqueInstance().initNumberHumanPlayers((byte)10);
                
        assertEquals(GameSettingsModel.getUniqueInstance().getNumberHumanPlayers(), 
                    GameSettingsModel.getUniqueInstance().getPseudonymsHumanPlayer().size());
    }
    
    @Test
    public void initChoiceGameRulesTest () {
        GameSettingsModel.getUniqueInstance().initChoiceGameRules('s');
        
        assertNotNull(new GameRulesFactoryModel().createGameRules());
    }
}
