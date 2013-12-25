package fr.utt.isi.lo02.unoGame.model.language;

import java.io.Serializable;
import java.util.Locale;
import java.util.PropertyResourceBundle;

/**
 * Permet la selection de la langue utilise au cours de l'application.
 * Elle peut etre definit par l'utilisateur.
 */
public class Expression implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static String language; //System.getProperty("user.language");
    private static String country; //System.getProperty("user.country");

    private static Locale currentLocale;
    
    /**
     * Initialise la langue en francais
     */
    public static void initExpression () {
        Expression.language = "fr";
        Expression.country = "FR";
        currentLocale = new Locale(Expression.language, Expression.country);
    }
    
    /**
     * Initialise la langue avec le choix de l'utilisateur.
     * @param language langue choisit par l'utilisateur
     * @param country pays choisit par l'utilisateur
     */
    public static void initExpression (String language, String country) {
        Expression.language = language;
        Expression.country = country;
        currentLocale = new Locale(Expression.language, Expression.country);
    }
    
    /**
     * Obtenir une source traduit en fonction de la langue selectionnee
     * @param key cle correspondant a une source
     * @return source correspondant a la cle
     */
    public static String getProperty (String key) {
        if ( language.isEmpty() )
            Expression.initExpression();
        return PropertyResourceBundle.getBundle("Language", currentLocale).getString(key);
    }
    
}
