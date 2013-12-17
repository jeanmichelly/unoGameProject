package fr.utt.isi.lo02.unoGame.model.card;

import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * Permet de lister la representation des couleurs que peut avoir une carte : 
 * <ul>
 *      <li>Jaune</li>
 *      <li>Rouge</li>
 *      <li>Vert</li>
 *      <li>Bleu</li>
 * </ul>
 * Une couleur est representee par un identifiant, un label, et une composante couleur RGB
 */
public enum ColorModel {
    
    YELLOW ((byte)0, 'Y', new java.awt.Color(220, 215, 0)),
    RED    ((byte)1, 'R', new java.awt.Color(199, 25, 25)),
    GREEN  ((byte)2, 'G', new java.awt.Color(11, 154, 35)),
    BLUE   ((byte)3, 'B', new java.awt.Color(25, 91, 199));
    
    /**
     * Identifie une couleur par un nombre
     */
    private byte id;
    /**
     * Identifie une couleur par un caractere
     */
    private char label;
    /**
     * Identifie une couleur par une composante couleur RGB
     */
    private java.awt.Color color;

    /**
     * Construit une couleur que peut avoir une carte du jeu
     * @param id identifie la couleur par un nombre
     * @param label identifie la couleur par un caractere
     * @param color identifie la couleur par une composite couleur RGB
     */
    private ColorModel (byte id, char label, java.awt.Color color) {
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
     * Obtenir le caractere identifiant la couleur
     * @return le caractere identifiant la couleur
     */
    public char getLabel () {
        return this.label;
    }
    
    /**
     * Obtenir la composante couleur RGB
     * @return la composante couleur RGB
     */
    public java.awt.Color getColor () {
        return this.color;
    }
    
}
