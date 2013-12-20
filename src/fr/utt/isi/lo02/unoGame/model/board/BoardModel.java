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
 * <i>Cette classe utilise le patron de conception <b>Singleton</b> et n'est donc instanciable qu'une fois.</i></br>
 * <p> Le plateau est physiquement caractérisé par une pioche, un talon et des joueurs. <br/>
 *  Pour la gestion du jeu, le plateau comporte également :
 *  <ul> 
 *      <li>Un curseur : index du joueur qui doit jouer</li> 
 *      <li>Une direction de jeu</li>
 *      <li>Un nombre de manches jouées</li>
 *      <li>Un nombre de parties jouées</li>
 *      <li>Un mode de jeu choisit par l'utilisateur</li>
 *      <li>Des penalités : gestion du contre uno et des éventuelles contestations des cartes +4</li>
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
<<<<<<< HEAD
     * Contient l'instance unique du plateau de jeu
=======
     * Rend le plateau instanciable une seule fois 
>>>>>>> bc71ce491c6168fb75be4cc85972db122305f8b6
     */
	private static BoardModel uniqueInstance = null;
	/**
     * Contient le curseur correspondant à l'index du joueur courant
     */
	private byte playerCursor;
	/**
     * Contient la direction de jeu (1 : horaire), (-1 : anti-horaire)
     */
	private byte directionOfPlay;
	/**
	 * Contient le nombre de manches jouées
	 */
	private short numberRound;
	/**
	 * Contient le nombre de parties jouées
	 */
	private short numberGame;
	/**
	 * Contient les différentes penalités (contre uno, +4)
	 */
	private CompositeEffectModel [] penaltys;
	/**
	 * Contient la pile de cartes correspondant à la défausse (sommet visible par les joueurs)
	 */
	private DiscardPileModel discardPile;
	/**
	 * Contient la pile de cartes correspondant à la pioche (sommet non visible par les joueurs)
	 */
	private DrawPileModel drawPile;
	/**
	 * Contient les différents joueurs
	 */
	private PlayerModel [] players;
	/**
	 * Contient les règles du jeu
	 */
	private GameRulesFactoryModel gameRulesFactory;
	/**
	 * Contient le mode de jeu
	 */
	private GameRulesModel gameRules; 

	/**
	 * Construit l'unique plateau et ne peut être appelé qu'une seule fois. 
	 * Lors de la création du plateau, on crée également la pioche, le talon, le mode de jeu et les pénalités.
	 */
	private BoardModel () {
	    this.drawPile = DrawPileModel.getUniqueInstance();
	    this.discardPile = DiscardPileModel.getUniqueInstance();
		this.gameRulesFactory = new GameRulesFactoryModel();
		this.initPenaltys();
		CompositeEffectModel.initGameEffect();
	}

	/**
	 * Récupérer l'instance unique du plateau
	 * @return instance unique du plateau
	 */
	public static BoardModel getUniqueInstance () {
	    if ( uniqueInstance == null )
	        uniqueInstance = new BoardModel();

	    return uniqueInstance;
	}
	
	/**
	 * Initialisation d'une manche avec la direction du jeu
	 */
	public void initRound () {
	    this.directionOfPlay = 1; 
        this.numberRound = 1;
	}

	/**
	 * Initialisation de la partie avec l'initialisation de la manche
	 */
	public void initGame () {
        this.numberGame = 1;
        this.initRound();
	}

	/**
	 * Initialisation du tableau de joueurs
	 */
	public void createPlayers () {
	    this.players = new PlayerModel[UserModel.getNumberPlayers()];
	}

	/**
	 * Initialisation et insertion des joueurs humains dans le tableau de joueurs
	 */
	public void initHumanPlayers () {
		for ( int i=0; i<UserModel.getNumberHumanPlayers(); i++ ) {
		    this.players[i] = new HumanPlayerModel(UserModel.getPseudonymHumanPlayer());
		}
	}

	/**
	 * Initialisation et insertion des joueurs ordinateurs dans le tableau de joueurs
	 */
	public void initComputerPlayers () {
		for ( int i=UserModel.getNumberHumanPlayers(), j=1; i<UserModel.getNumberPlayers(); i++, j++ ) {
		    this.players[i] = new ComputerPlayerModel("Computer"+j);
		}
	}
	
	/**
	 * Initialisation du mode de jeu choisit par l'utilisateur
	 */
	public void initGameRules () {
	    this.gameRules = this.gameRulesFactory.createGameRules();
	}
	
	/**
	 * Initialisation des penalités de jeu :
	 * <ul>
	 *     <li>Contre uno : Le joueur vunérable pioche 2 cartes</li>
	 *     <li>Contre +4 : Si celui qui pose la carte bluffe et se fait contester,  il pioche 4 cartes</li>
	 *     <li>Contre +4 : Si celui qui conteste échoue, il pioche 2 cartes pour s'être trompé, et 4 cartes pour le +4 qu'il vient de subir</li>
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
	 * Choix aléatoire du joueur qui va commencer
	 */
	public void chooseRandomDealer () {
	    // Formule utilisée : int random = (int)(Math.random() * (higher-lower)) + lower;
	    this.playerCursor = (byte)(Math.random() * (this.players.length)); 
	}

	/**
	 * Distribution des cartes à chaque joueurs
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
	 * Récupérer l'index du joueur suivant
	 * @return index du joueur suivant
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
	 * @return index du joueur suivant
	 */
	public byte moveCursorToNextPlayer () {
		return this.playerCursor = this.getToNextPlayer();
	}
	
	/**
	 * Applique l'effet de la carte posée sur le talon
	 * @throws InvalidActionPickCardException
	 * @throws InvalidColorModelException
	 */
	public void applyCardEffect () throws   InvalidActionPickCardException, 
	                                        InvalidColorModelException {
	    
        this.discardPile.peek().getCompositeEffects().applyEffect();
	    DiscardPileModel.getUniqueInstance().setApplyEffectLastCard(true);
	}
    
	/**
	 * Applique l'effet de la pénalité contre le uno (+2 cartes sur les joueurs n'ayant pas dit uno à temps)
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
     * Applique l'effet de la pénalité contre un poseur de carte +4 ayant bluffé et découvert par la cible
     * (+4 cartes sur lui même)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstLauncherWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[1].applyEffect(this.getPlayerCursor());
    }
    
    /**
     * Applique l'effet de la pénalité d'une carte +4 sur la cible ayant contestée 
     * (+2 cartes en supplement de l'effet de la carte, soit 6 cartes)
     * @throws InvalidActionPickCardException
     */
    public void applyPenaltyAgainstWildDrawFourCard () throws InvalidActionPickCardException {
        penaltys[2].applyEffect(this.getToNextPlayer());
    }
    
    /**
     * Indique s'il y a un joueur vulnérable (joueur auquel on peut dire contre uno)
     * @return true s'il y un joueur vulnérable, false sinon
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
     *     <li>Incrémentation du nombre de manche jouée</li>
     *     <li>Remise des cartes restantes des mains des joueurs et celles du talon dans la pioche</li>
     *     <li>Mélange de la pioche</li>
     *     <li>Initialisation des flag de vulnérabilité (pour le contre uno) des joueurs par la valeur de défaut (false)</li>
     *     <li>Choix du joueur qui va commencer à jouer pour la nouvelle manche</li>
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
     * Permet de passer d'une partie à une autre
     * <ul>
     *      <li>Incrémentation du nombre de partie jouée</li>
     *      <li>Initialisation de la premiere manche</li>
     *      <li>Initialisation des scores des joueurs par la valeur de défaut (0)</li>
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
     * Obtenir le joueur à qui est le tour de jouer 
     * @return le joueur à qui est le tour de jouer
     */
    public PlayerModel getPlayer () {
        return this.players[playerCursor];
    }
    
    /**
     * Obtenir un joueur en particulier
     * @param index Indique l'indice du joueur souhaité
     * @return le joueur correspondant à l'index dans l'ensemble
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
     * Obtenir l'ensemble des joueurs participant à la partie
     * @return l'ensemble des joueurs participant à la partie
     */
    public PlayerModel [] getPlayers () {
        return this.players;
    }
    
    /**
     * Informe sur l'indice du joueur à qui est le tour
     * @return l'indice du joueur à qui est le tour
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
     * Informe sur le nombre de partie jouée
     * @return le nombre de partie jouée
     */
    public short getNumberGame () {
        return this.numberGame;
    }
    
    /**
     * Informe sur le nombre de manche jouée
     * @return le nombre de manche jouée
     */
    public short getNumberRound () {
        return this.numberRound;
    }
    
    /**
     * Donner la main à un joueur en particulier
     * @param playerCursor indice du joueur pour lequel la main est donnée
     */
    public void setPlayerCursor(byte playerCursor) {
        this.playerCursor = playerCursor;
    }
    
    /**
     * Permet d'accéder au setChanged de la classe observable (ignorer sa visibilité protected)
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
