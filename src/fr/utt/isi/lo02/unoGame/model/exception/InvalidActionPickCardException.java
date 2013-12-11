package fr.utt.isi.lo02.unoGame.model.exception;

public class InvalidActionPickCardException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidActionPickCardException() {
        super("Tentative de piocher dans une pioche vide");
    }
    
}
