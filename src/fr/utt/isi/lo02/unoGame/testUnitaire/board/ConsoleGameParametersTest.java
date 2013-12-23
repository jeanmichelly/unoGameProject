package fr.utt.isi.lo02.unoGame.testUnitaire.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.utt.isi.lo02.unoGame.model.board.ConsoleGameParametersModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;

public class ConsoleGameParametersTest {

    @Test
    public void initHumanPlayersTest () {
        ConsoleGameParametersModel.initNumberHumanPlayers();
        ConsoleGameParametersModel.initPseudonymsHumanPlayers();
                
        assertEquals(ConsoleGameParametersModel.getNumberHumanPlayers(), ConsoleGameParametersModel.getPseudonymsHumanPlayer().size());
    }
    
    @Test
    public void initChoiceGameRulesTest () {
        ConsoleGameParametersModel.initChoiceGameRules();
        
        assertNotNull(new GameRulesFactoryModel().createGameRules());
    }
}
