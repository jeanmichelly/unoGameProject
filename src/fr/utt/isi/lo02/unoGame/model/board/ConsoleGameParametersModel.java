package fr.utt.isi.lo02.unoGame.model.board;

import java.util.Stack;

/**
 * Exclusivement constituée de membres statiques. 
 * Elle définit l'utilisateur qui manipule l'application hormis le deroulement d'une partie.
 */
public class ConsoleGameParametersModel {
	
    /**
     * Stock le nombre de joueur participant à une partie
     */
	private static byte numberPlayers;
	/**
	 * Stock le nombre de joueur humain participant à une partie
	 */
	private static byte numberHumanPlayers;
	/**
	 * Stock les pseudo associés à chaque joueur humain
	 */
	private static Stack<String> pseudonymsHumanPlayers = new Stack<String>();
	/**
	 * Stock le mode de jeu choisit par l'utilisateur
	 */
	private static char choiceGameRules;
	
	/**
	 * Initialise le nombre de joueur
	 */
    public static void initNumberPlayers (byte numberPlayers) {
        ConsoleGameParametersModel.numberPlayers = numberPlayers;
    }

    /**
     * Initialise le nombre de joueur humain
     */
    public static void initNumberHumanPlayers () {
        ConsoleGameParametersModel.numberHumanPlayers = 0;    	
    }

    /**
     * Initialise les pseudo associés à chaque joueur humain
     */
    public static void initPseudonymsHumanPlayers () {
        ConsoleGameParametersModel.pseudonymsHumanPlayers.push("Pablo");
        ConsoleGameParametersModel.pseudonymsHumanPlayers.push("JM");
        ConsoleGameParametersModel.pseudonymsHumanPlayers.push("Mario");
    }
    
    /**
     * Initialise le mode de jeu choisit par l'utilisateur
     */
    public static void initChoiceGameRules () {
        ConsoleGameParametersModel.choiceGameRules = 's';
    }
    
    /**
     * Obtenir le nombre de joueur participant à une partie
     * @return le nombre de joueur participant à une partie
     */
    public static byte getNumberPlayers () {
        return ConsoleGameParametersModel.numberPlayers;
    }

    /**
     * Obtenir le nombre de joueur humain participant à une partie
     * @return le nombre de joueur humain participant à une partie
     */
    public static byte getNumberHumanPlayers () {
        return ConsoleGameParametersModel.numberHumanPlayers;
    }

    /**
     * Obtenir les pseudo des joueurs humain participant à une partie
     * @return les pseudo des joueurs humain participant à une partie
     */
    public static Stack<String> getPseudonymsHumanPlayer () {
        return ConsoleGameParametersModel.pseudonymsHumanPlayers;
    }
    
    /**
     * Obtenir et retirer le dernier pseudo.
     * @return le dernier pseudo
     */
    public static String getPseudonymHumanPlayer () {
        return ConsoleGameParametersModel.pseudonymsHumanPlayers.pop();
    }
    
    /**
     * Obtenir le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public static char getChoiceGameRules () {
        return ConsoleGameParametersModel.choiceGameRules;
    }
    
    /**
     * Modifie le nombre de joueur participant à une partie
     * @param numberPlayers mise à jour du nombre de joueur participant à une partie
     */
    public static void setNumberPlayers (byte numberPlayers) {
        ConsoleGameParametersModel.numberPlayers = numberPlayers;
    }
    
    /**
     * Modifie le nombre de joueur humain participant à une partie
     * @param numberHumanPlayers mise à jour du nombre de joueur humain participant à une partie
     */
    public static void setNumberHumanPlayers (byte numberHumanPlayers) {
        ConsoleGameParametersModel.numberHumanPlayers = numberHumanPlayers;
    }
    
    /**
     * Modifie l'ensemble des pseudo des joueurs humain participant à une partie
     * @param pseudonymsHumanPlayers mise à jour des pseudo des joueurs humain participant à une partie
     */
    public static void setPseudonymsHumanPlayers (Stack<String> pseudonymsHumanPlayers) {
        ConsoleGameParametersModel.pseudonymsHumanPlayers = pseudonymsHumanPlayers;
    }
    
}