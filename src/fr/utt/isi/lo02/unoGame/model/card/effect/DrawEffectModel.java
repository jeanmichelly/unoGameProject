package fr.utt.isi.lo02.unoGame.model.card.effect;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;

/**
 * Permet d'effectuer un effet pour faire piocher une carte au prochain joueur.
 */
public class DrawEffectModel extends ComponentEffectModel {

    /**
     * Construit un effet pour piocher avec une priorite de 20
     */
    public DrawEffectModel () {
        super((byte)20);
    }
    
    /**
     * Applique l'effet de faire piocher au prochain joueur
     */
    public void applyEffect () throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().getNextPlayer().pickCard();
    }
    
    /**
     * Applique l'effet de faire piocher a un joueur en particulier (pour les penalites)
     * @param i indique l'indice du joueur a faire piocher
     * @throws InvalidActionPickCardException
     */
    public void applyEffect (int index) throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().getPlayer(index).pickCard();
    }
    
}
