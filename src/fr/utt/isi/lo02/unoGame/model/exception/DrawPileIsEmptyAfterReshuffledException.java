package fr.utt.isi.lo02.unoGame.model.exception;

public class DrawPileIsEmptyAfterReshuffledException extends Exception {

    private static final long serialVersionUID = 1L;

    public DrawPileIsEmptyAfterReshuffledException() {
        super("Pas assez de carte dans la pioche et le talon pour pouvoir piocher");
    }
    
}
