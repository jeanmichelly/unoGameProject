package fr.utt.isi.lo02.unoGame.testUnitaire.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameSettingsModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;

public class ConsoleGameParametersTest {

    @Test
    public void initHumanPlayersTest () {
        ConsoleGameSettingsModel.initNumberHumanPlayers();
        ConsoleGameSettingsModel.initPseudonymsHumanPlayers();
                
        assertEquals(ConsoleGameSettingsModel.getNumberHumanPlayers(), ConsoleGameSettingsModel.getPseudonymsHumanPlayer().size());
    }
    
    @Test
    public void initChoiceGameRulesTest () {
        ConsoleGameSettingsModel.initChoiceGameRules();
        
        assertNotNull(new GameRulesFactoryModel().createGameRules());
    }
}
