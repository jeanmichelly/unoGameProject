package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;

/**
 * Permet le parametrage du deroulement du jeu de maniere ergonomique.
 */
public abstract class GameRulesModel {
    /**
     * Definit le nombre minimum de joueur que peut avoir une partie du jeu
     */
	public static final byte NUMBER_PLAYERS_MIN = 2;
	/**
	 * Definit le nombre maximum de joueur que peut avoir une partie du jeu
	 */
	public static final byte NUMBER_PLAYERS_MAX = 10;
	/**
	 * Definit le nombre maximum de carte que le jeu possede
	 */
	public static final byte CARD_NUMBER_TOTAL = 108;
	/**
	 * Definit le nombre de carte distribuee par joueur
	 */
	public static final byte CARD_NUMBER_DISTRIBUTED_PER_PLAYER = 7;
	/**
	 * Definit le temps limite qu'un joueur a par tour de jeu
	 */
	public static final byte TIME_LIMIT_PER_PLAYER = 5;

	/**
	 * Definit le score maximum a avoir pour finaliser une partie
	 */
	public static final short SCORE_MAX = 500;

	/**
	 * Represente les symboles presents 1 fois par couleur
	 */
	public static final SymbolModel [] SYMBOLS1 = { SymbolModel.ZERO }; 
	
	/**
	 * Represente les symboles presents 2 fois par couleur
	 */
	public static final SymbolModel [] SYMBOLS2 = { SymbolModel.ONE, SymbolModel.TWO, SymbolModel.THREE, SymbolModel.FOUR, SymbolModel.FIVE, SymbolModel.SIX, SymbolModel.SEVEN, SymbolModel.EIGHT, SymbolModel.NINE, SymbolModel.DRAW_TWO, SymbolModel.SKIP, SymbolModel.REVERSE };
	
	/**
	 * Represente les symboles presents 4 fois sans couleur
	 */
	public static final SymbolModel [] SYMBOLS4 = { SymbolModel.WILD, SymbolModel.WILD_DRAW_FOUR };
	
	/**
	 * Represente les couleurs qu'une carte du jeu peut avoir
	 */
	public static final ColorModel [] COLORS = { ColorModel.YELLOW, ColorModel.RED, ColorModel.GREEN, ColorModel.BLUE };
	
	/**
	 * Scores associes respectivement aux symboles de SYMBOLS1
	 */
	public static final byte [] SCORES1 = { 0 };
	
	/**
	 * Scores associes respectivement aux symboles de SYMBOLS2
	 */
	public static final byte [] SCORES2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20, 20 };

	/**
	 * Scores associes respectivement aux symboles de SYMBOLS4
	 */
	public static final byte [] SCORES4 = { 50, 50 };
	
	/**
	 * Comptabilisation des points pour chaque joueur selon le mode de jeu choisit par l'utilisateur
	 */
	public abstract void countScore ();
	
	/**
	 * Seuil a atteindre pour considerer qu'un joueur a gagne une partie
	 * @return true si le dernier joueur a avoir pose une carte est le gagnant de la partie, false sinon.
	 */
	public abstract boolean isWinner ();
	
}
