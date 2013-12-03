package model;

import java.util.Stack;

public class UserModel {
	
	private static  byte numberPlayers;
	private static byte numberHumanPlayers;
	private static Stack<String> pseudonymsHumanPlayers = new Stack<String>();
	private static char choiceGameRules;
	
    public static void initNumberPlayers () {
        UserModel.numberPlayers = 3;
    }

    public static void initNumberHumanPlayers () {
        UserModel.numberHumanPlayers = 3;    	
    }

    public static void initPseudonymsHumanPlayers () {
        UserModel.pseudonymsHumanPlayers.push("Pablo");
        UserModel.pseudonymsHumanPlayers.push("JM");
        UserModel.pseudonymsHumanPlayers.push("Mario");
    }
    
    public static void initChoiceGameRules () {
        UserModel.choiceGameRules = 's';
    }
    
    public static byte getNumberPlayers () {
        return UserModel.numberPlayers;
    }

    public static byte getNumberHumanPlayers () {
        return UserModel.numberHumanPlayers;
    }

    public static Stack<String> getPseudonymsHumanPlayer () {
        return UserModel.pseudonymsHumanPlayers;
    }
    
    public static String getPseudonymHumanPlayer () {
        return UserModel.pseudonymsHumanPlayers.pop();
    }
    
    public static char getChoiceGameRules () {
        return UserModel.choiceGameRules;
    }
    
    public static void setNumberPlayers (byte numberPlayers) {
        UserModel.numberPlayers = numberPlayers;
    }
    
    public static void setNumberHumanPlayers (byte numberHumanPlayers) {
        UserModel.numberHumanPlayers = numberHumanPlayers;
    }
    
    public static void setPseudonymsHumanPlayers(Stack<String> pseudonymsHumanPlayers) {
        UserModel.pseudonymsHumanPlayers = pseudonymsHumanPlayers;
    }
    
}