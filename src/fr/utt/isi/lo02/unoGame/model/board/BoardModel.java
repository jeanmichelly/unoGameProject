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
 * <b>Cette classe contient le coeur du deroulement du jeu Uno. Elle represente le plateau du jeu et elle n'est donc, instanciable qu'une fois. </b> </br>
 * <p> Le plateau est physiquement caracterise par une pioche, un talon et des joueurs.
 *  Pour la gestion du jeu, le plateau comporte egalement :
 *  <ul> 
 *      <li>Un curseur representant le joueur qui doit jouer</li> 
 *      <li>La direction du jeu (sens horaire ou anti horaire)</li>
 *      <li>Le nombre de manche jouee</li>
 *      <li>Le nombre de partie jouee</li>
 *      <li>Un mode de jeu choisit par l'utilisateur</li>
 *      <li>Des penalites pour gerer les contre uno et les eventuelles contestations des cartes +4</li>
 *  </ul>
 * @see CompositeEffectModel
 * @see DiscardPileModel
 * @see DrawPileModel
 * @see PlayerModel
 * @see GameRulesFactoryModel
 * @see GameRulesModel
 */
public class BoardModel extends Observable {
    /**
     * Rends le plateau instanciable une seule fois 
     */
	private static BoardModel uniqueInstance = null;
	/**
     * Informe sur qui est le tour de jouer
     */
	private byte playerCursor;
	/**
     * Informe sur la direction du jeu 
     */
	private byte directionOfPlay;
	/**
	 * Informe sur le nombre de manche qui a ete jouee
	 */
	private short numberRound;
	/**
	 * Informe sur le nombre de partie qui a ete jouee
	 */
	private short numberGame;
	/**
	 * Comporte les differentes penalites (contre uno ou carte +4)
	 */
	private CompositeEffectModel [] penaltys;
	/**
	 * Le talon correspond a une pile de carte dont le sommet est visible par les joueurs
	 */
	private DiscardPileModel discardPile;
	/**
	 * La pioche correspond a une pile de carte non visible par les joueurs.
	 */
	private DrawPileModel drawPile;
	/**
	 * Stock les joueurs dans un tableau. Si on considere qu'un joueur humain quitte la partie, on le remplace par un ordinateur.
	 */
	private PlayerModel [] players;
	/**
	 * Choix dynamique du mode de jeu
	 */
	private GameRulesFactoryModel gameRulesFactory;
	/**
	 * Correspond au mode de jeu choisit par l'utilisateur
	 */
	private GameRulesModel gameRules; 

	/**
	 * Construit l'unique plateau et ne peut etre appele qu'une seule fois. 
	 * Lors de la creation du plateau, on cree egalement la pioche, le talon, le mode de jeu et les penalites.
	 */
	private BoardModel () {
	    this.drawPile = DrawPileModel.getUniqueInstance();
	    this.discardPile = DiscardPileModel.getUniqueInstance();
		this.gameRulesFactory = new GameRulesFactoryModel();
		this.initPenaltys();
	}

	/**
	 * Obtenir l'instance unique du plateau
	 * @return l'unique plateau
	 */
	public static BoardModel getUniqueInstance () {
	    if ( uniqueInstance == null )
	        uniqueInstance = new BoardModel();

	    return uniqueInstance;
	}
	
	/**
	 * Initialiser une manche avec la direction du jeu
	 */
	public void initRound () {
	    this.directionOfPlay = 1; 
        this.numberRound = 1;
	}

	/**
	 * Initialiser la partie avec l'initialisation de la manche
	 */
	public void initGame () {
        this.numberGame = 1;
        this.initRound();
	}

	/**
	 * Creer l'ensemble qui va contenir les joueurs
	 */
	public void createPlayers () {
	    this.players = new PlayerModel[UserModel.getNumberPlayers()];
	}

	/**
	 * Initialiser des joueurs humains dans l'ensemble
	 */
	public void initHumanPlayers () {
		for ( int i=0; i<UserModel.getNumberHumanPlayers(); i++ ) {
		    this.players[i] = new HumanPlayerModel(UserModel.getPseudonymHumanPlayer());
		}
	}

	/**
	 * Initialiser des joueurs ordinateurs dans l'ensemble
	 */
	public void initComputerPlayers () {
		for ( int i=UserModel.getNumberHumanPlayers(), j=1; i<UserModel.getNumberPlayers(); i++, j++ ) {
		    this.players[i] = new ComputerPlayerModel("Computer"+j);
		}
	}
	
	/**
	 * Initialiser le mode de jeu choisit par l'utilisateur
	 */
	public void initGameRules () {
	    this.gameRules = this.gameRulesFactory.createGameRules();
	}
	
	/**
	 * Initialiser des penalites qui peut eventuellement avoir au cours du jeu :
	 * <ul>
	 *     <li>Contre uno : +2 cartes sur les joueurs etant vulnerable (flag du joueur a true)</li>
	 *     <li>Contre +4 : Si le poseur de la carte a bluffe et a ete conteste : +4 cartes contre lui meme</li>
	 *     <li>Contre +4 : Contestation echouee par la cible : +2 cartes de penalite + 4 cartes de l'effet de la carte, soit 6 cartes</li>
	 * </ul> 
	 */
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

	/**
	 * Choix aleatoire du joueur qui va commencer
	 */
	public void chooseRandomDealer () {
	    this.playerCursor = (byte)(Math.random() * (this.players.length)); // Formule utilisee : int random = (int)(Math.random() * (higher-lower)) + lower;
	}

	/**
	 * Distribution des cartes pour chaque joueur
	 * @throws InvalidActionPickCardException
	 */
	public void dispenseCards () throws InvalidActionPickCardException {
	    for ( PlayerModel player : this.players ) {
    	        for ( int i=0; i<GameRulesModel.CARD_NUMBER_DISTRIBUTED_PER_PLAYER; i++ ) {
    	            player.pickCard();
    	        }
	    }
	}
	
	/**
	 * Informe sur le prochain joueur qui doit jouer
	 * @return Indique l'indice de l'ensemble du prochain joueur qui doit jouer
	 */
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
	
	/**
	 * Passe le tour au joueur suivant  
	 * @return Indique l'indice de l'ensemble du prochain joueur qui doit jouer
	 */
	public byte moveCursorToNextPlayer () {
		return this.playerCursor = this.getToNextPlayer();
	}
	
	/**
	 * Applique l'effet de la carte posee sur le talon
	 * @throws InvalidActionPickCardException
	 * @throws InvalidColorModelException
	 */
	public void applyCardEffect () throws   InvalidActionPickCardException, 
	                                        InvalidColorModelException {
	    
        this.discardPile.peek().getCompositeEffects().applyEffect();
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}
    
	/**
	 * Applique l'effet de la penalite contre le uno (+2 cartes sur les joueurs n'ayant pas dit uno a temps)
	 * @throws InvalidActionPickCardException
	 */
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
    
    /**
     * Applique l'effet de la penalite contre un poseur de carte +4 ayant bluffe et decouvert par la cible(+4 cartes sur lui meme)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstLauncherWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[1].applyEffect(this.getPlayerCursor());
    }
    
    /**
     * Applique l'effet de la penalite d'une carte +4 sur la cible ayant contestee (+2 cartes en supplement de l'effet de la carte, soit 6 cartes)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[2].applyEffect(this.getToNextPlayer());
    }
    
    /**
     * Indique s'il y a un joueur vulnerable (joueur auquel on peut dire contre uno)
     * @return true s'il y un joueur vulnerable, false sinon
     */
    public boolean hasVulnerablePlayer () {
        for ( PlayerModel player : players )
            if ( player.getUno() )
                return true;
        return false;
    }

    /**
     * Permet de passer d'une manche a une autre :
     * <ul>
     *     <li>Incrementation du nombre de manche jouee</li>
     *     <li>Remise des cartes restantes des mains des joueurs et celles du talon dans la pioche</li>
     *     <li>Melange de la pioche</li>
     *     <li>Initialisation des flag de vulnerabilite (pour le contre uno) des joueurs par la valeur de defaut (false)</li>
     *     <li>Choix du joueur qui va commencer a jouer pour la nouvelle manche</li>
     *     <li>Distribution des cartes</li>
     *     <li>Initialisation du talon</li>
     * </ul>
     * @throws InvalidActionPickCardException
     */
    public void nextRound () throws InvalidActionPickCardException {
        this.numberRound++;
        
        // Remise des cartes restantes des mains des joueurs et celles du talon dans la pioche
        for ( PlayerModel player : this.players ) {
            DrawPileModel.getUniqueInstance().addCards(player.getPlayerHand().getCards());
            player.getPlayerHand().clear();
            player.setUno(false);
        }
        DrawPileModel.getUniqueInstance().addCards(DiscardPileModel.getUniqueInstance().getCards());
        DiscardPileModel.getUniqueInstance().clear();
        
        DrawPileModel.getUniqueInstance().shuffle();
        
        this.chooseRandomDealer();
        
        this.dispenseCards();
        
        try { // Initialisation du talon
            DiscardPileModel.getUniqueInstance().addCard(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de passer d'une partie a une autre
     * <ul>
     *      <li>Incrementation du nombre de partie jouee</li>
     *      <li>Initialisation de la premiere manche</li>
     *      <li>Initialisation des scores des joueurs par la valeur de defaut (0)</li>
     * </ul>
     * @throws InvalidActionPickCardException
     */
    public void nextGame () throws InvalidActionPickCardException {
        this.numberGame++;
        this.numberRound = 0;
        for ( PlayerModel player : this.players )
            player.setScore((short)0);
        this.nextRound();
    }
    
    /**
     * Obtenir le joueur a qui est le tour de jouer 
     * @return le joueur a qui est le tour de jouer
     */
    public PlayerModel getPlayer () {
        return this.players[playerCursor];
    }
    
    /**
     * Obtenir un joueur en particulier
     * @param index Indique l'indice du joueur souhaite
     * @return le joueur correspondant a l'index dans l'ensemble
     */
    public PlayerModel getPlayer (int index) {
        return this.players[index];
    }

    /**
     * Obtenir le prochain joueur qui doit jouer
     * @return le prochain joueur qui doit jouer
     */
    public PlayerModel getNextPlayer () {
        return this.players[getToNextPlayer()];
    }
    
    /**
     * Obtenir l'ensemble des joueurs participant a la partie
     * @return l'ensemble des joueurs participant a la partie
     */
    public PlayerModel [] getPlayers () {
        return this.players;
    }
    
    /**
     * Informe sur l'indice du joueur a qui est le tour
     * @return l'indice du joueur a qui est le tour
     */
    public byte getPlayerCursor() {
        return this.playerCursor;
    }
    
    /**
     * Informe sur la direction du jeu
     * @return la direction du jeu (-1 sens anti horaire, 1 sens horaire)
     */
    public byte getDirectionOfPlay () {
        return this.directionOfPlay;
    }
    
    /**
     * Obtenir le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public GameRulesModel getGameRules() {
        return this.gameRules;
    }
    
    /**
     * Informe sur le nombre de partie jouee
     * @return le nombre de partie jouee
     */
    public short getNumberGame () {
        return this.numberGame;
    }
    
    /**
     * Informe sur le nombre de manche jouee
     * @return le nombre de manche jouee
     */
    public short getNumberRound () {
        return this.numberRound;
    }
    
    /**
     * Donner la main a un joueur en particulier
     * @param playerCursor indice du joueur pour lequel la main est donnee
     */
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    /**
     * Permet d'acceder au setChanged de la classe observable (ignorer sa visibilite protected)
     */
    public void setChanged () {
        super.setChanged();
    }
    
    /**
     * Modifier le sens de jeu (-1 sens anti horaire, 1 sens horaire)
     */
    public void setDirectionOfPlay () {
        this.directionOfPlay *= -1;
    }

}
