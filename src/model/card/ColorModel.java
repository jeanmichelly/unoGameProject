package model.card;

public enum ColorModel {
    
    YELLOW ('Y', new java.awt.Color(220, 215, 0)),
    RED    ('R', new java.awt.Color(199, 25, 25)),
    GREEN  ('G', new java.awt.Color(11, 154, 35)),
    BLUE   ('B', new java.awt.Color(25, 91, 199));
    
    private char label;
    private java.awt.Color color;

    private ColorModel (char label, java.awt.Color color) {
        this.color = color;
        this.label = label;
    }

    public char getLabel () {
        return label;
    }

    public java.awt.Color getColor () {
        return color;
    }
    
}
