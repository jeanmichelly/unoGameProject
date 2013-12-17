package fr.utt.isi.lo02.unoGame.model.exception;

public class InvalidPlayException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public InvalidPlayException() {
        super("Probleme pouvant etre dans putDownCard, chooseColor, notToPlay");
    }
    
}
