package fr.utt.isi.lo02.unoGame.model.gameRules;

import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;

public abstract class GameRulesModel {

	public static final byte NUMBER_PLAYERS_MIN = 2;
	public static final byte NUMBER_PLAYERS_MAX = 10;
	public static final byte CARD_NUMBER_TOTAL = 108;
	public static final byte CARD_NUMBER_DISTRIBUTED_PER_PLAYER = 7;
	public static final byte TIME_LIMIT_PER_PLAYER = 5;

	public static final short SCORE_MAX = 500;
	// Tableau de symboles présents 1 fois par couleur
	public static final SymbolModel [] SYMBOLS1 = { SymbolModel.ZERO }; 
	// Tableau de symboles présents 2 fois par couleur
	public static final SymbolModel [] SYMBOLS2 = { SymbolModel.ONE, SymbolModel.TWO, SymbolModel.THREE, SymbolModel.FOUR, SymbolModel.FIVE, SymbolModel.SIX, SymbolModel.SEVEN, SymbolModel.EIGHT, SymbolModel.NINE, SymbolModel.DRAW_TWO, SymbolModel.SKIP, SymbolModel.REVERSE };
	// Tableau de symboles présents 4 fois sans couleur
	public static final SymbolModel [] SYMBOLS4 = { SymbolModel.WILD, SymbolModel.WILD_DRAW_FOUR };
	public static final ColorModel [] COLORS = { ColorModel.YELLOW, ColorModel.RED, ColorModel.GREEN, ColorModel.BLUE };
	// Scores associés aux symboles du tableau SYMBOLS1
	public static final byte [] SCORES1 = { 0 };
	// Scores associés aux symboles du tableau SYMBOLS2
	public static final byte [] SCORES2 = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 20, 20, 20 };
	// Scores associés aux symboles du tableau SYMBOLS4
	public static final byte [] SCORES4 = { 50, 50 };
	
	public abstract void countScore ();
	public abstract boolean isWinner ();
	
}
