package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;

/**
 * Permet le paramétrage du déroulement du jeu de manière ergonomique.
 * @see GameRulesFactoryModel
 * @see GameRulesStandardModel
 * @see ColorModel
 * @see SymbolModel
 */
public abstract class GameRulesModel {
    /**
     * Définit le nombre minimum de joueur que peut avoir une partie du jeu
     */
	public static final byte NUMBER_PLAYERS_MIN = 2;
	/**
	 * Définit le nombre maximum de joueur que peut avoir une partie du jeu
	 */
	public static final byte NUMBER_PLAYERS_MAX = 10;
	/**
	 * Définit le nombre maximum de carte que le jeu possède
	 */
	public static final byte CARD_NUMBER_TOTAL = 108;
	/**
	 * Définit le nombre de carte distribuée par joueur
	 */
	public static final byte CARD_NUMBER_DISTRIBUTED_PER_PLAYER = 7;
	/**
	 * Définit le temps limite qu'un joueur a par tour de jeu
	 */
	public static final byte TIME_LIMIT_PER_PLAYER = 5;

	/**
	 * Définit le score maximum à avoir pour finaliser une partie
	 */
	public static final short SCORE_MAX = 500;

	/**
	 * Représente les symboles présents 1 fois par couleur
	 */
	public static final SymbolModel [] SYMBOLS1 = { SymbolModel.ZERO }; 
	
	/**
	 * Représente les symboles présents 2 fois par couleur
	 */
	public static final SymbolModel [] SYMBOLS2 = { 
	    SymbolModel.ONE, SymbolModel.TWO, SymbolModel.THREE, SymbolModel.FOUR, SymbolModel.FIVE, 
	    SymbolModel.SIX, SymbolModel.SEVEN, SymbolModel.EIGHT, SymbolModel.NINE, 
	    SymbolModel.DRAW_TWO, SymbolModel.SKIP, SymbolModel.REVERSE 
	};
	
	/**
	 * Représente les symboles présents 4 fois sans couleur
	 */
	public static final SymbolModel [] SYMBOLS4 = { SymbolModel.WILD, SymbolModel.WILD_DRAW_FOUR };
	
	/**
	 * Représente les couleurs qu'une carte du jeu peut avoir
	 */
	public static final ColorModel [] COLORS = { ColorModel.YELLOW, ColorModel.RED, ColorModel.GREEN, ColorModel.BLUE };
	
	/**
	 * Scores associés respectivement aux symboles de SYMBOLS1
	 */
	public static final byte [] SCORES1 = { 0 };
	
	/**
	 * Scores associés respectivement aux symboles de SYMBOLS2
	 */
	public static final byte [] SCORES2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20, 20 };

	/**
	 * Scores associés respectivement aux symboles de SYMBOLS4
	 */
	public static final byte [] SCORES4 = { 50, 50 };
	
	/**
	 * Comptabilisation des points pour chaque joueur selon le mode de jeu choisit par l'utilisateur
	 */
	public abstract void countScore ();
	
	/**
	 * Seuil à atteindre pour considérer qu'un joueur a gagné une partie
	 * @return true si le dernier joueur a avoir posé une carte est le gagnant de la partie, false sinon.
	 */
	public abstract boolean isWinner ();
	
}
