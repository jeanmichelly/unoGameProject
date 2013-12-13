package fr.utt.isi.lo02.unoGame.model.card;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * 
 * Cette classe permet de lister la representation des couleurs d'une carte.
 *
 */
public enum ColorModel {
    
    YELLOW ((byte)0, 'Y', new java.awt.Color(220, 215, 0)),
    RED    ((byte)1, 'R', new java.awt.Color(199, 25, 25)),
    GREEN  ((byte)2, 'G', new java.awt.Color(11, 154, 35)),
    BLUE   ((byte)3, 'B', new java.awt.Color(25, 91, 199));
    
    private byte id;
    private char label;
    private java.awt.Color color;

    private ColorModel (byte id, char label, java.awt.Color color) {
        this.id = id;
        this.color = color;
        this.label = label;
    }
    
    public byte getId () {
        return this.id;
    }

    public char getLabel () {
        return this.label;
    }
    
    public static ColorModel getColorModel (byte index) throws InvalidColorModelException{
        switch ( index ) {
            case 0 :
                return ColorModel.YELLOW;
            case 1 :
                return ColorModel.RED;
            case 2 :
                return ColorModel.GREEN;
            case 3 :
                return ColorModel.BLUE;
            default :
                throw new InvalidColorModelException();
        }
    }

    public java.awt.Color getColor () {
        return this.color;
    }
    
}
