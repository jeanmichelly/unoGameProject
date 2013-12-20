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
 * <i>Cette classe adopte le patron de conception <b>Singleton</b></i> </br> <br/> 
 * <b>Elle contient le coeur du deroulement du jeu Uno. Elle represente le plateau du jeu et elle n'est donc instanciable qu'une seule fois. </b> </br>
 * <p> Le plateau est physiquement caracterisé par une pioche, un talon et des joueurs.
 */
public class BoardModel extends Observable {
    /**
     * Contient l'instance unique du plateau de jeu
     */
	private static BoardModel uniqueInstance = null;
	/**
     * Contient l'index du joueur à qui c'est au tour de jouer
     */
	private byte playerCursor;
	/**
     * Contient le sens de jeu (Horaire : 1, Anti-Horaire : -1)
     */
	private byte directionOfPlay;
	/**
	 * Contient le nombre de manches qui ont été jouées
	 */
	private short numberRound;
	/**
	 * Contient le nombre de parties qui ont été jouées
	 */
	private short numberGame;
	/**
	 * Contient les differentes penalités (reçues dans le cas d'un contre uno ou d'une carte +4)
	 * @see CompositeEffectModel
	 */
	private CompositeEffectModel [] penaltys;
	/**
	 * Le talon correspond à une pile de cartes dont le sommet est visible par les joueurs
	 * @see DiscardPileModel
	 */
	private DiscardPileModel discardPile;
	/**
	 * La pioche correspond à une pile de cartes non visible par les joueurs.
	 * @see DrawPileModel
	 */
	private DrawPileModel drawPile;
	/**
	 * Stocke les joueurs dans un tableau. Si un joueur humain quitte la partie, on le remplace par un ordinateur.
	 * @see PlayerModel
	 */
	private PlayerModel [] players;
	/**
	 * Choix dynamique du mode de jeu
	 * @see GameRulesFactoryModel
	 */
	private GameRulesFactoryModel gameRulesFactory;
	/**
	 * Correspond au mode de jeu choisit par l'utilisateur
	 * @see GameRulesModel
	 */
	private GameRulesModel gameRules; 

	/**
	 * Construit l'unique plateau et ne peut etre appelé qu'une seule fois. 
	 * Lors de la creation du plateau, on crée egalement la pioche, le talon, le mode de jeu et les penalites.
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
	 * Créer le tableau qui contiendra les joueurs
	 */
	public void createPlayers () {
	    this.players = new PlayerModel[UserModel.getNumberPlayers()];
	}

	/**
	 * Initialiser des joueurs humains dans le tableau de joueurs
	 */
	public void initHumanPlayers () {
		for ( int i=0; i<UserModel.getNumberHumanPlayers(); i++ ) {
		    this.players[i] = new HumanPlayerModel(UserModel.getPseudonymHumanPlayer());
		}
	}

	/**
	 * Initialiser des joueurs ordinateurs dans le tableau de joueurs
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
	 * Initialiser des penalités qui peuvent survenir au cours du jeu :
	 * <ul>
	 *     <li>Contre uno : +2 cartes sur les joueurs étant vulnérable (flag du joueur à true)</li>
	 *     <li>Contre +4 : Si celui qui pose la carte a bluffé et a ete contesté : +4 cartes contre lui meme</li>
	 *     <li>Contre +4 : Contestation echouée : +2 cartes de penalité, +4 cartes de l'effet de la carte, soit 6 cartes</li>
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
	 * Choix aléatoire du joueur qui commence
	 */
	public void chooseRandomDealer () {
	    this.playerCursor = (byte)(Math.random() * (this.players.length)); // Formule utilisée : int random = (int)(Math.random() * (higher-lower)) + lower;
	}

	/**
	 * Distribution des cartes à chaque joueur
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
	 * Retourne l'index du joueur suivant
	 * @return Indice du joueur suivant
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
	 * Déplacement du curseur vers le joueur suivant  
	 * @return Indice du joueur suivant
	 */
	public byte moveCursorToNextPlayer () {
		return this.playerCursor = this.getToNextPlayer();
	}
	
	/**
	 * Applique l'effet de la carte posée sur le talon
	 * @throws InvalidActionPickCardException
	 * @throws InvalidColorModelException
	 */
	public void applyCardEffect () throws InvalidActionPickCardException, 
	                                      InvalidColorModelException {
	    
        this.discardPile.peek().getCompositeEffects().applyEffect();
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}
    
	/**
	 * Applique l'effet de la penalité contre le joueur qui n'a pas dit UNO à temps (+2 cartes)
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
     * Applique l'effet de la penalité contre celui qui a posé une carte +4, ayant bluffé, et ayant été trahis par un autre joueur (+4 cartes sur lui meme)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstLauncherWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[1].applyEffect(this.getPlayerCursor());
    }
    
    /**
     * Applique l'effet de la penalité d'une carte +4 sur la cible ayant contesté mais ayant échoué (+2 cartes en supplément de l'effet de la carte, soit 6 cartes)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[2].applyEffect(this.getToNextPlayer());
    }
    
    /**
     * Indique s'il y a un joueur vulnerable (joueur à qui on peut dire contre uno)
     * @return true s'il y un joueur vulnerable, false sinon
     */
    public boolean hasVulnerablePlayer () {
        for ( PlayerModel player : players )
            if ( player.getUno() )
                return true;
        return false;
    }

    /**
     * Permet de passer d'une manche à une autre :
     * <ul>
     *     <li>Incrémentation du nombre de manche jouées</li>
     *     <li>Remise des cartes restantes des mains des joueurs et celles du talon dans la pioche</li>
     *     <li>Mélange de la pioche</li>
     *     <li>Initialisation des flag de vulnerabilité (pour le contre uno) des joueurs par la valeur par defaut (false)</li>
     *     <li>Choix du joueur qui va commencer à jouer lors de la nouvelle manche</li>
     *     <li>Distribution des cartes</li>
     *     <li>Initialisation du talon</li>
     * </ul>
     * @throws InvalidActionPickCardException
     */
    public void nextRound () throws InvalidActionPickCardException {
        this.numberRound++;
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
        try {
            DiscardPileModel.getUniqueInstance().addCard(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet de passer d'une partie a une autre
     * <ul>
     *      <li>Incrémentation du nombre de partie jouées</li>
     *      <li>Initialisation de la première manche</li>
     *      <li>Initialisation des scores des joueurs par la valeur par defaut (0)</li>
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
     * Retourne le joueur à qui c'est au tour de jouer
     * @return joueur à qui c'est au tour de jouer
     */
    public PlayerModel getPlayer () {
        return this.players[playerCursor];
    }
    
    /**
     * Obtenir un joueur en particulier
     * @param index Indice du joueur
     * @return le joueur
     */
    public PlayerModel getPlayer (int index) {
        return this.players[index];
    }

    /**
     * Retourne le prochain joueur qui doit jouer
     * @return le prochain joueur qui doit jouer
     */
    public PlayerModel getNextPlayer () {
        return this.players[getToNextPlayer()];
    }
    
    /**
     * Obtenir l'ensemble des joueurs participant à la partie
     * @return l'ensemble des joueurs participant à la partie
     */
    public PlayerModel [] getPlayers () {
        return this.players;
    }
    
    /**
     * Retourne l'indice du joueur courant
     * @return l'indice du joueur courant
     */
    public byte getPlayerCursor() {
        return this.playerCursor;
    }
    
    /**
     * Informe sur la direction du jeu
     * @return la direction du jeu (1 sens horaire, -1 sens anti-horaire)
     */
    public byte getDirectionOfPlay () {
        return this.directionOfPlay;
    }
    
    /**
     * Retourne le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public GameRulesModel getGameRules() {
        return this.gameRules;
    }
    
    /**
     * Retourne le nombre de partie jouées
     * @return le nombre de partie jouées
     */
    public short getNumberGame () {
        return this.numberGame;
    }
    
    /**
     * Retourne le nombre de manche jouées
     * @return le nombre de manche jouées
     */
    public short getNumberRound () {
        return this.numberRound;
    }
    
    /**
     * Donner la main a un joueur en particulier
     * @param playerCursor indice du joueur
     */
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    /**
     * Permet d'accéder au setChanged de la classe observable (ignorer sa visibilite protected)
     */
    public void setChanged () {
        super.setChanged();
    }
    
    /**
     * Modifier le sens de jeu (1 sens horaire, -1 sens anti-horaire)
     */
    public void setDirectionOfPlay () {
        this.directionOfPlay *= -1;
    }

}
