package fr.utt.isi.lo02.unoGame.model.exception;

public class InvalidGameRulesException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidGameRulesException() {
        super("Probleme de comptabilisation des points");
    }
    
}
