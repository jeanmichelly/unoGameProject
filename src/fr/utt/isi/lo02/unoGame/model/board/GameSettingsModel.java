package fr.utt.isi.lo02.unoGame.model.board;

import java.util.Observable;
import java.util.Stack;

/**
 * Exclusivement constituée de membres statiques. 
 * Elle définit l'utilisateur qui manipule l'application hormis le deroulement d'une partie.
 */
public class GameSettingsModel extends Observable {
    
    private static GameSettingsModel uniqueInstance = null;
    /**
     * Stock le nombre de joueur participant à une partie
     */
    private byte numberPlayers;
    /**
     * Stock le nombre de joueur humain participant à une partie
     */
    private byte numberHumanPlayers;
    /**
     * Stock les pseudo associés à chaque joueur humain
     */
    private Stack<String> pseudonymsHumanPlayers;
    /**
     * Stock le mode de jeu choisit par l'utilisateur
     */
    private char choiceGameRules;
    
    private GameSettingsModel () {
        this.pseudonymsHumanPlayers = new Stack<String>();
        this.numberPlayers = 2;
        this.choiceGameRules = 's';
    }

    public static GameSettingsModel getUniqueInstance () {
        if ( uniqueInstance == null )
            uniqueInstance = new GameSettingsModel();

        return uniqueInstance;
    }
    
    /**
     * Initialise le nombre de joueur
     */
    public void initNumberPlayers (byte numberPlayers) {
        this.numberPlayers = numberPlayers;
    }

    /**
     * Initialise le nombre de joueur humain
     */
    public void initNumberHumanPlayers (byte numberHumanPlayers) {
        this.numberHumanPlayers = numberHumanPlayers;      
    }

    /**
     * Initialise les pseudo associés à chaque joueur humain
     */
    public void initPseudonymsHumanPlayers () {
        this.pseudonymsHumanPlayers.push("Pablo");
        this.pseudonymsHumanPlayers.push("JM");
        this.pseudonymsHumanPlayers.push("Jeremie");
        this.pseudonymsHumanPlayers.push("Cindy");
        this.pseudonymsHumanPlayers.push("Isabelle");
        this.pseudonymsHumanPlayers.push("Mario");
        this.pseudonymsHumanPlayers.push("Annie");
        this.pseudonymsHumanPlayers.push("Francois");
        this.pseudonymsHumanPlayers.push("Antoine");
        this.pseudonymsHumanPlayers.push("Sabrina");
    }
    
    /**
     * Initialise le mode de jeu choisit par l'utilisateur
     */
    public void initChoiceGameRules (char choiceGameRules) {
        this.choiceGameRules = choiceGameRules;
    }
    
    public void setChanged () {
        super.setChanged();
    }
    
    /**
     * Obtenir le nombre de joueur participant à une partie
     * @return le nombre de joueur participant à une partie
     */
    public byte getNumberPlayers () {
        return this.numberPlayers;
    }

    /**
     * Obtenir le nombre de joueur humain participant à une partie
     * @return le nombre de joueur humain participant à une partie
     */
    public byte getNumberHumanPlayers () {
        return this.numberHumanPlayers;
    }

    /**
     * Obtenir les pseudo des joueurs humain participant à une partie
     * @return les pseudo des joueurs humain participant à une partie
     */
    public Stack<String> getPseudonymsHumanPlayer () {
        return this.pseudonymsHumanPlayers;
    }
    
    /**
     * Obtenir et retirer le dernier pseudo.
     * @return le dernier pseudo
     */
    public String getPseudonymHumanPlayer () {
        return this.pseudonymsHumanPlayers.pop();
    }
    
    /**
     * Obtenir le mode de jeu choisit par l'utilisateur
     * @return le mode de jeu choisit par l'utilisateur
     */
    public char getChoiceGameRules () {
        return this.choiceGameRules;
    }
    
    /**
     * Modifie le nombre de joueur participant à une partie
     * @param numberPlayers mise à jour du nombre de joueur participant à une partie
     */
    public void setNumberPlayers (byte numberPlayers) {
        this.numberPlayers = numberPlayers;
    }
    
    /**
     * Modifie le nombre de joueur humain participant à une partie
     * @param numberHumanPlayers mise à jour du nombre de joueur humain participant à une partie
     */
    public void setNumberHumanPlayers (byte numberHumanPlayers) {
        this.numberHumanPlayers = numberHumanPlayers;
    }
    
    /**
     * Modifie l'ensemble des pseudo des joueurs humain participant à une partie
     * @param pseudonymsHumanPlayers mise à jour des pseudo des joueurs humain participant à une partie
     */
    public void setPseudonymsHumanPlayers (Stack<String> pseudonymsHumanPlayers) {
        this.pseudonymsHumanPlayers = pseudonymsHumanPlayers;
    }
    
}