package fr.utt.isi.lo02.unoGame.model.card;

/**
 * Permet de lister la représentation des couleurs que peut avoir une carte : 
 * <ul>
 *      <li>Jaune</li>
 *      <li>Rouge</li>
 *      <li>Vert</li>
 *      <li>Bleu</li>
 * </ul>
 * Une couleur est representée par un identifiant, un label, et une composante couleur RGB
 */
public enum ColorModel {
    
    YELLOW ((byte)0, 'Y', new com.badlogic.gdx.graphics.Color(.86f, .84f,  0,    1)),
    RED    ((byte)1, 'R', new com.badlogic.gdx.graphics.Color(.78f, .098f, .098f,1)),
    GREEN  ((byte)2, 'G', new com.badlogic.gdx.graphics.Color(.043f,.60f,  .13f, 1)),
    BLUE   ((byte)3, 'B', new com.badlogic.gdx.graphics.Color(.098f,.35f,  .78f, 1));
    
    /**
     * Identifie une couleur par un nombre
     */
    private byte id;
    /**
     * Identifie une couleur par un caractère
     */
    private char label;
    /**
     * Identifie une couleur par une composante couleur RGB
     */
    private com.badlogic.gdx.graphics.Color color;
    /**
     * Obtenir la couleur par un indice 
     */
    private static ColorModel [] colors = {YELLOW, RED, GREEN, BLUE};

    /**
     * Construit une couleur que peut avoir une carte du jeu
     * @param id identifie la couleur par un nombre
     * @param label identifie la couleur par un caractere
     * @param color identifie la couleur par une composite couleur RGB
     */
    private ColorModel (byte id, char label, com.badlogic.gdx.graphics.Color color) {
        this.id = id;
        this.color = color;
        this.label = label;
    }
    
    /**
     * Obtenir le nombre identifiant la couleur 
     * @return le nombre identifiant la couleur
     */
    public byte getId () {
        return this.id;
    }

    /**
     * Obtenir le caractère identifiant la couleur
     * @return le caractère identifiant la couleur
     */
    public char getLabel () {
        return this.label;
    }
    
    /**
     * Obtenir la composante couleur RGB
     * @return la composante couleur RGB
     */
    public com.badlogic.gdx.graphics.Color getColor () {
        return this.color;
    }
    
    public static ColorModel getColor (byte index) {
        return ColorModel.colors[index];
    }
   
}
