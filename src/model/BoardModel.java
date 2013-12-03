package model;

import model.card.effect.CompositeEffectModel;
import model.deck.DiscardPileModel;
import model.deck.DrawPileModel;
import model.game_rules.GameRulesFactoryModel;
import model.game_rules.GameRulesModel;
import model.player.ComputerPlayerModel;
import model.player.HumanPlayerModel;
import model.player.PlayerModel;
import view.console.ConsoleBoardView;

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
	private boolean hasWinner; 
    private String playerWinner;

	private BoardModel () {
	    drawPile = DrawPileModel.getUniqueInstance();
		discardPile = DiscardPileModel.getUniqueInstance();
		gameRulesFactory = new GameRulesFactoryModel();
		hasWinner = false;
	}

	public static BoardModel getUniqueInstance () {
	    if ( uniqueInstance == null ) {
	        uniqueInstance = new BoardModel();
	    }
	    return uniqueInstance;
	}
	
	public void initRound () {
        directionOfPlay = 1; 
        numberRound = 0;
	}

	public void initGame () {
        numberGame = 0;
        initRound();
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
		playerCursor = (byte)(Math.random() * (players.length)); // Formule utilis��e : int random = (int)(Math.random() * (higher-lower)) + lower;
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
            if( players.length-1 < playerCursor+1 ) {
                return 0;
            } else {
                return (byte)(playerCursor+1);
            }
        } 
        else {
            if( playerCursor-1 < 0 ) {
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
	}

	public void launchGame () {
	    System.out.println(ConsoleBoardView.build());

	    //while (!gameRules.hasWinner()) {
    	        getPlayer().play();
	    //} 
	}
	
	public static void main (String [] args) {
	    BoardModel board = BoardModel.getUniqueInstance();
	    UserModel.initNumberPlayers();
	    UserModel.initNumberHumanPlayers();
	    UserModel.initPseudonymsHumanPlayers();
	    UserModel.initChoiceGameRules();
	    board.createPlayers();
	    board.initHumanPlayers();
	    board.initComputerPlayers();
	    board.initGameRules();
	    board.chooseRandomDealer();
	    board.dispenseCards();
	    board.initGame();
	    board.launchGame();
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

   	public boolean hasWinner () {
        return hasWinner;
    }
    
    public String getPlayerWinner () {
        return playerWinner;
    }
    
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    public void setDirectionOfPlay () {
        directionOfPlay *= -1;
    }

    public void setHasWinner (boolean hasWinner) {
        this.hasWinner = hasWinner;
    }
    
    public void setPlayerWinner (String playerWinner) {
        this.playerWinner = playerWinner;
    }

}
