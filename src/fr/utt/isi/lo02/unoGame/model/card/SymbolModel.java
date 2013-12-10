package fr.utt.isi.lo02.unoGame.model.card;

/**
 * 
 * Cette classe permet de lister la representation des symboles d'une carte.
 *
 */
public enum SymbolModel {

    ZERO           ('0'),
    ONE            ('1'),
    TWO            ('2'),
    THREE          ('3'),
    FOUR           ('4'),
    FIVE           ('5'),
    SIX            ('6'),
    SEVEN          ('7'),
    EIGHT          ('8'),
    NINE           ('9'),
    DRAW_TWO       ('T'),
    SKIP           ('S'),
    REVERSE        ('R'),
    WILD           ('W'),
    WILD_DRAW_FOUR ('F');

    private char label;

    private SymbolModel (char label) {
        this.label = label;
    }
    
    public char getLabel () {
        return label;
    }
    
}
