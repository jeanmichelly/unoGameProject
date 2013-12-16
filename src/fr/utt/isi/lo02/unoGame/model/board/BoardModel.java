package fr.utt.isi.lo02.unoGame.model.board;

import java.util.Observable;

import fr.utt.isi.lo02.unoGame.model.card.effect.CompositeEffectModel;
import fr.utt.isi.lo02.unoGame.model.card.effect.DrawEffectModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesFactoryModel;
import fr.utt.isi.lo02.unoGame.model.gameRules.GameRulesModel;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;
import fr.utt.isi.lo02.unoGame.model.player.PlayerModel;
import fr.utt.isi.lo02.unoGame.model.user.UserModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * <u>Pattern Singleton : </u> </br> 
 * <b>Cette classe contient le coeur du deroulement du jeu Uno. Elle represente le plateau du jeu et elle est donc, instanciable qu'une fois. </b> </br>
 * <p> Le plateau est physiquement caracterise par une pioche, un talon et des joueurs.
 *  Pour la gestion du jeu, le plateau comporte egalement :
 *  <ul> 
 *      <li>Un curseur representant le joueur qui doit jouer</li> 
 *      <li>La direction du jeu (sens horaire ou anti horaire)</li>
 *      <li>Le nombre de manche</li>
 *      <li>Le nombre de partie</li>
 *      <li>Un mode de jeu choisit par l'utilisateur</li>
 *      <li>Des penalites pour gerer les contre uno et les eventuelles contestations des cartes +4</li>
 *  </ul>
 */
public class BoardModel extends Observable {
    /**
     * Permet de rendre le plateau instanciable une seule fois 
     */
	private static BoardModel uniqueInstance = null;
	/**
     * Permet de savoir a qui est le tour 
     */
	private byte playerCursor;
	/**
     * Permet de connaitre la direction du jeu 
     */
	private byte directionOfPlay;
	/**
	 * Permet de savoir combien de manche a ete jouee
	 */
	private short numberRound;
	/**
	 * Permet de savoir combien de partie a ete jouee
	 */
	private short numberGame;
	/**
	 * Comporte les differentes penalites (contre uno ou carte +4)
	 * @see CompositeEffectModel
	 */
	private CompositeEffectModel [] penaltys;
	/**
	 * Le talon correspond a une pile de carte dont le sommet est visible par les joueurs
	 * @see DiscardPileModel
	 */
	private DiscardPileModel discardPile;
	/**
	 * La pioche correspond a pile de carte non visible par les joueurs.
	 * @see DrawPileModel
	 */
	private DrawPileModel drawPile;
	/**
	 * Stock les joueurs dans un tableau. Si on considere qu'un joueur humain quitte la partie, on le remplace par un ordinateur.
	 * @see PlayerModel
	 */
	private PlayerModel [] players;
	/**
	 * Permet de choisir le mode de jeu dynamiquement
	 * @see GameRulesFactoryModel
	 */
	private GameRulesFactoryModel gameRulesFactory;
	/**
	 * Correspond au mode de jeu choisit par l'utilisateur
	 * @see GameRulesModel
	 */
	private GameRulesModel gameRules; 

	/**
	 * Le constructeur est en prive, n'a pas d'argument et ne peut etre appele qu'une seule fois. 
	 * Lors de la creation du plateau, on cree egalement la pioche, le talon, le mode de jeu et les penalites.
	 */
	private BoardModel () {
	    this.drawPile = DrawPileModel.getUniqueInstance();
	    this.discardPile = DiscardPileModel.getUniqueInstance();
		this.gameRulesFactory = new GameRulesFactoryModel();
		this.initPenaltys();
	}

	public static BoardModel getUniqueInstance () {
	    if ( uniqueInstance == null )
	        uniqueInstance = new BoardModel();

	    return uniqueInstance;
	}
	
	public void initRound () {
	    this.directionOfPlay = 1; 
        this.numberRound = 1;
	}

	public void initGame () {
        this.numberGame = 1;
        this.initRound();
	}

	public void nextRound () throws InvalidActionPickCardException {
	    this.numberRound++;
	    for ( PlayerModel player : this.players ) {
	        DrawPileModel.getUniqueInstance().addAll(player.getPlayerHand().getCards());
	        player.getPlayerHand().clear();
	        player.setUno(false);
	    }
	    DrawPileModel.getUniqueInstance().addAll(DiscardPileModel.getUniqueInstance().getCards());
	    DiscardPileModel.getUniqueInstance().clear();
	    this.chooseRandomDealer();
        this.dispenseCards();
        try {
            DiscardPileModel.getUniqueInstance().add(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
	}

	public void nextGame () throws InvalidActionPickCardException {
	    this.numberGame++;
	    this.numberRound = 0;
	    for ( PlayerModel player : this.players )
	        player.setScore((short)0);
	    this.nextRound();
	}

	public void createPlayers () {
	    this.players = new PlayerModel[UserModel.getNumberPlayers()];
	}

	public void initHumanPlayers () {
		for ( int i=0; i<UserModel.getNumberHumanPlayers(); i++ ) {
		    this.players[i] = new HumanPlayerModel(UserModel.getPseudonymHumanPlayer());
		}
	}

	public void initComputerPlayers () {
		for ( int i=UserModel.getNumberHumanPlayers(), j=1; i<UserModel.getNumberPlayers(); i++, j++ ) {
		    this.players[i] = new ComputerPlayerModel("Computer"+j);
		}
	}
	
	public void initGameRules () {
	    this.gameRules = this.gameRulesFactory.createGameRules();
	}
	
	public void initPenaltys () {
        CompositeEffectModel againstUno = new CompositeEffectModel();
        againstUno.addEffect(new DrawEffectModel(), 2);
        
        CompositeEffectModel againstLauncherWildDrawFourCard = new CompositeEffectModel();
        againstLauncherWildDrawFourCard.addEffect(new DrawEffectModel(), 4);

        CompositeEffectModel againstWildDrawFourCard = new CompositeEffectModel();
        againstWildDrawFourCard.addEffect(new DrawEffectModel(), 2);
        
        this.penaltys = new CompositeEffectModel [3];  
        this.penaltys[0] = againstUno;
        this.penaltys[1] = againstLauncherWildDrawFourCard;
        this.penaltys[2] = againstWildDrawFourCard;
    }

	public void chooseRandomDealer () {
	    this.playerCursor = (byte)(Math.random() * (this.players.length)); // Formule utilisee : int random = (int)(Math.random() * (higher-lower)) + lower;
	}

	public void dispenseCards () throws InvalidActionPickCardException {
	    for ( PlayerModel player : this.players ) {
    	        for ( int i=0; i<GameRulesModel.CARD_NUMBER_DISTRIBUTED_PER_PLAYER; i++ ) {
    	            player.pickCard();
    	        }
	    }
	}
	
	public byte getToNextPlayer () {
        if( this.directionOfPlay == 1 ) {
            if( this.playerCursor == this.players.length-1 ) {
                return 0;
            } else {
                return (byte)(this.playerCursor+1);
            }
        } 
        else {
            if( this.playerCursor == 0 ) {
                return (byte)(this.players.length-1);
            } else {
                return (byte)(this.playerCursor-1);
            }
        }
    }
	
	public byte moveCursorToNextPlayer () {
		return this.playerCursor = this.getToNextPlayer();
	}
	
	public void applyCardEffect () throws   InvalidActionPickCardException, 
	                                        InvalidColorModelException {
	    
        this.discardPile.peek().getCompositeEffects().applyEffect();
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}
    
    public void applyPenaltyAgainstUno () throws InvalidActionPickCardException {
        for (int i=0; i<this.players.length; i++) {
            if ( this.players[i].getUno() ) {
                this.penaltys[0].applyEffect(i);
                this.setChanged();
                this.notifyObservers();
                ConsoleBoardView.update(this.players[i].getPseudonym()+" a pioche 2 cartes\n");
                this.players[i].setUno(false);
            }
        }
    }
    
    public void applyPenaltyAgainstLauncherWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[1].applyEffect(this.getPlayerCursor());
    }
    
    public void applyPenaltyAgainstWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[2].applyEffect(this.getToNextPlayer());
    }
    
    public boolean hasVulnerablePlayer () {
        for ( PlayerModel player : players )
            if ( player.getUno() )
                return true;
        return false;
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
