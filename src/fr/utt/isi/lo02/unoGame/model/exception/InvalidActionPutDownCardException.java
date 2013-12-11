package fr.utt.isi.lo02.unoGame.model.exception;

public class InvalidActionPutDownCardException extends Exception {

    private static final long serialVersionUID = 1L;
    
    public InvalidActionPutDownCardException () {
        super("Tentative de mettre une carte non jouable sur le talon");
    }

}
