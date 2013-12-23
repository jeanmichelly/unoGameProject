package fr.utt.isi.lo02.unoGame.model.user;

import java.util.Stack;

/**
 * Exclusivement constituée de membres statiques. 
 * Elle définit l'utilisateur qui manipule l'application hormis le deroulement d'une partie.
 */
public class UserModel {
	
    /**
     * Stock le nombre de joueur participant à une partie
     */
	private static  byte numberPlayers;
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
    public static void initNumberPlayers () {
        UserModel.numberPlayers = 12;
    }

    /**
     * Initialise le nombre de joueur humain
     */
    public static void initNumberHumanPlayers () {
        UserModel.numberHumanPlayers = 0;    	
    }

    /**
     * Initialise les pseudo associés à chaque joueur humain
     */
    public static void initPseudonymsHumanPlayers () {
        UserModel.pseudonymsHumanPlayers.push("Pablo");
        UserModel.pseudonymsHumanPlayers.push("JM");
        UserModel.pseudonymsHumanPlayers.push("Mario");
    }
    
    /**
     * Initialise le mode de jeu choisit par l'utilisateur
     */
    public static void initChoiceGameRules () {
        UserModel.choiceGameRules = 's';
    }
    
    /**
     * Obtenir le nombre de joueur participant à une partie
     * @return le nombre de joueur participant à une partie
     */
    public static byte getNumberPlayers () {
        return UserModel.numberPlayers;
    }

    /**
     * Obtenir le nombre de joueur humain participant à une partie
     * @return le nombre de joueur humain participant à une partie
     */
    public static byte getNumberHumanPlayers () {
        return UserModel.numberHumanPlayers;
    }

    /**
     * Obtenir les pseudo des joueurs humain participant à une partie
     * @return les pseudo des joueurs humain participant à une partie
     */
    public static Stack<String> getPseudonymsHumanPlayer () {
        return UserModel.pseudonymsHumanPlayers;
    }
    
    /**
     * Obtenir et retirer le dernier pseudo.
     * @return le dernier pseudo
     */
    public static String getPseudonymHumanPlayer () {
        return UserModel.pseudonymsHumanPlayers.pop();
    }
    
    /**
     * Obtenir le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public static char getChoiceGameRules () {
        return UserModel.choiceGameRules;
    }
    
    /**
     * Modifie le nombre de joueur participant à une partie
     * @param numberPlayers mise à jour du nombre de joueur participant à une partie
     */
    public static void setNumberPlayers (byte numberPlayers) {
        UserModel.numberPlayers = numberPlayers;
    }
    
    /**
     * Modifie le nombre de joueur humain participant à une partie
     * @param numberHumanPlayers mise à jour du nombre de joueur humain participant à une partie
     */
    public static void setNumberHumanPlayers (byte numberHumanPlayers) {
        UserModel.numberHumanPlayers = numberHumanPlayers;
    }
    
    /**
     * Modifie l'ensemble des pseudo des joueurs humain participant à une partie
     * @param pseudonymsHumanPlayers mise à jour des pseudo des joueurs humain participant à une partie
     */
    public static void setPseudonymsHumanPlayers (Stack<String> pseudonymsHumanPlayers) {
        UserModel.pseudonymsHumanPlayers = pseudonymsHumanPlayers;
    }
    
}