package fr.utt.isi.lo02.unoGame.model;

import java.util.Observable;

import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;

/**
 * 
 * Cette classe contient le coeur du déroulement du jeu Uno. Elle représente le plateau du jeu et elle est donc, instanciable qu'une fois.  
 *
 */
public class BoardModel extends Observable {

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
        numberRound = 1;
	}

	public void initGame () {
        numberGame = 1;
        initRound();
	}

	public void nextRound () throws InvalidActionPickCardException, DrawPileIsEmptyAfterReshuffledException {
	    this.numberRound++;
	    for ( PlayerModel player : players ) {
	        DrawPileModel.getUniqueInstance().addAll(player.getPlayerHand().getCards());
	        player.getPlayerHand().clear();
	    }
	    DrawPileModel.getUniqueInstance().addAll(DiscardPileModel.getUniqueInstance().getCards());
	    DiscardPileModel.getUniqueInstance().clear();
	    this.chooseRandomDealer();
        this.dispenseCards();
        DiscardPileModel.getUniqueInstance().add(DrawPileModel.getUniqueInstance().pop());
	}

	public void nextGame () throws InvalidActionPickCardException, DrawPileIsEmptyAfterReshuffledException {
	    this.numberGame++;
	    this.numberRound = 0;
	    for ( PlayerModel player : players )
	        player.setScore((short)0);
	    this.nextRound();
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

	public void dispenseCards () throws InvalidActionPickCardException {
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
	    try {
            discardPile.peek().getCompositeEffects().applyEffect();
        } catch (InvalidActionPickCardException e) {
            e.printStackTrace();
        }
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}
    
    public PlayerModel getPlayer () {
        return this.players[playerCursor];
    }
    
    public PlayerModel getPlayer (int index) {
        return this.players[index];
    }

    public PlayerModel getNextPlayer () {
        return this.players[getToNextPlayer()];
    }
    
    public PlayerModel [] getPlayers () {
        return this.players;
    }
    
    public byte getPlayerCursor() {
        return this.playerCursor;
    }
    
    public byte getDirectionOfPlay () {
        return this.directionOfPlay;
    }
    
    public GameRulesModel getGameRules() {
        return this.gameRules;
    }
    
    public short getNumberGame () {
        return this.numberGame;
    }
    
    public short getNumberRound () {
        return this.numberRound;
    }
    
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    public void setChanged () {
        super.setChanged();
    }
    
    public void setDirectionOfPlay () {
        this.directionOfPlay *= -1;
    }

}
