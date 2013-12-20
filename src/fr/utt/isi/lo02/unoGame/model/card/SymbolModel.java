package fr.utt.isi.lo02.unoGame.model.card;
/**
 * Permet de lister la représentation des symboles que peut avoir une carte : 
 * <ul>
 *      <li>Jaune</li>
 *      <li>Rouge</li>
 *      <li>Vert</li>
 *      <li>Bleu</li>
 * </ul>
 * Une couleur est representée par un identifiant, un label, et une composante couleur RGB
 */
/**
 * 
 * Permet de lister la représentation des symboles que peut avoir une carte :
 * <ul>
 *      <li>ZERO</li>
 *      <li>ONE</li>
 *      <li>TWO</li>
 *      <li>THREE</li>
 *      <li>FOUR</li>
 *      <li>FIVE</li>
 *      <li>SIX</li>
 *      <li>SEVEN</li>
 *      <li>EIGHT</li>
 *      <li>NINE</li>
 *      <li>DRAW_TWO</li>
 *      <li>SKIP</li>
 *      <li>REVERSE</li>
 *      <li>WILD</li>
 *      <li>WILD_DRAW_FOUR</li>
 * </ul>
 * Un symbole est representé par un label
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

    /**
     * Identifie un symbole par un caractère
     */
    private char label;

    /**
     * Construit un symbole que peut avoir une carte du jeu
     * @param label identifie le symbole par un caractère
     */
    private SymbolModel (char label) {
        this.label = label;
    }
    
    /**
     * Obtenir le caractère identifiant le symbole
     * @return le caractère identifiant le symbole
     */
    public char getLabel () {
        return this.label;
    }
    
}
