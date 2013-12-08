package fr.utt.isi.lo02.unoGame.model;

import java.util.Locale;
import java.util.PropertyResourceBundle;

public class Expression {

    private static String language; //System.getProperty("user.language");
    private static String country; //System.getProperty("user.country");

    private static Locale currentLocale;
    
    public static void initExpression () {
        Expression.language = "fr";
        Expression.country = "FR";
        currentLocale = new Locale(Expression.language, Expression.country);
    }
    
    public static void initExpression (String language, String country) {
        Expression.language = language;
        Expression.country = country;
        currentLocale = new Locale(Expression.language, Expression.country);
    }
    
    public static String getProperty (String key) {
        if ( language.isEmpty() )
            Expression.initExpression();
        return PropertyResourceBundle.getBundle("Language", currentLocale).getString(key);
    }
    
}
