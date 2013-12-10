package fr.utt.isi.lo02.unoGame.model;

import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

public class BoardModel {

	private static BoardModel uniqueInstance = null;
	private byte playerCursor;
	private byte directionOfPlay;
	private short numberRound;
	private short numberGame;
	private CompositeEffectModel penaltys;
	private DiscardPileModel discardPile;
	private DrawPileModel drawPile;
	private PlayerModel [] players;
	private GameRulesFactoryModel gameRulesFactory;
	private GameRulesModel gameRules; 

	private BoardModel () {
	    drawPile = DrawPileModel.getUniqueInstance();
		discardPile = DiscardPileModel.getUniqueInstance();
		gameRulesFactory = new GameRulesFactoryModel();
	}

	public static BoardModel getUniqueInstance () {
	    if ( uniqueInstance == null )
	        uniqueInstance = new BoardModel();
	    return uniqueInstance;
	}
	
	public void initRound () {
        directionOfPlay = 1; 
        numberRound = 0;
	}

	public void initGame () {
        numberGame = 0;
	}

	public void createPlayers () {
		players = new PlayerModel[UserModel.getNumberPlayers()];
	}

	public void initHumanPlayers () {
		for ( int i=0; i<UserModel.getNumberHumanPlayers(); i++ ) {
			players[i] = new HumanPlayerModel(UserModel.getPseudonymHumanPlayer());
		}
	}

	public void initComputerPlayers () {
		for ( int i=UserModel.getNumberHumanPlayers(), j=1; i<UserModel.getNumberPlayers(); i++, j++ ) {
			players[i] = new ComputerPlayerModel("Computer"+j);
		}
	}
	
	public void initGameRules () {
	    gameRules = gameRulesFactory.createGameRules();
	}

	public void chooseRandomDealer () {
		playerCursor = (byte)(Math.random() * (players.length)); // Formule utilisée : int random = (int)(Math.random() * (higher-lower)) + lower;
	}

	public void dispenseCards () {
	    for ( PlayerModel player : players ) {
    	        for ( int i=0; i<GameRulesModel.CARD_NUMBER_DISTRIBUTED_PER_PLAYER; i++ ) {
    	            player.pickCard();
    	        }
	    }
	}
	
   public byte getToNextPlayer () {
        if( directionOfPlay == 1 ) {
            if( playerCursor == players.length-1 ) {
                return 0;
            } else {
                return (byte)(playerCursor+1);
            }
        } 
        else {
            if( playerCursor == 0 ) {
                return (byte)(players.length-1);
            } else {
                return (byte)(playerCursor-1);
            }
        }
    }
	
	public byte moveCursorToNextPlayer () {
		return playerCursor=getToNextPlayer();
	}
	
	public void applyCardEffect () {
	    discardPile.peek().getCompositeEffects().applyEffect();
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}

   	public DrawPileModel getDrawPile () {
        return drawPile;
    }

    public DiscardPileModel getDiscardPile () {
        return discardPile;
    }
    
    public PlayerModel getPlayer () {
        return players[playerCursor];
    }
    
    public PlayerModel getPlayer (int index) {
        return players[index];
    }

    public PlayerModel getNextPlayer () {
        return players[getToNextPlayer()];
    }
    
    public PlayerModel [] getPlayers () {
        return players;
    }
    
    public byte getPlayerCursor() {
        return playerCursor;
    }
    
    public byte getDirectionOfPlay () {
        return directionOfPlay;
    }
    
    public GameRulesModel getGameRules() {
        return gameRules;
    }
    
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    public void setDirectionOfPlay () {
        directionOfPlay *= -1;
    }

}