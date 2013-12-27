package fr.utt.isi.lo02.unoGame.model.player;

import java.io.Serializable;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityColorStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityNumberStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PrioritySpecialityStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.RandomStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.StrategyModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;

/**
 * Représente les joueurs ordinateurs. 
 * Les joueurs ordinateurs ont une intelligence artificielle et peuvent alors, jouer automatiquement.
 */
public class ComputerPlayerModel extends PlayerModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Comporte différentes stratégies
     * @see PlayerModel
     * @see StrategyModel
     * @see PriorityColorStrategyModel
     * @see PriorityNumberStrategyModel
     * @see PrioritySpecialityStrategyModel
     * @see RandomStrategyModel
     */
    private StrategyModel [] strategy;
    
    /**
     * Construit un joueur ordinateur avec différentes strategies : 
     * <ul>
     *      <li>Priorité aux couleurs</li>
     *      <li>Priorité aux nombres</li>
     *      <li>Priorité aux cartes avec des effets</li>
     *      <li>Choix aleatoire</li>
     * </ul>
     * @param pseudonym représente le pseudo du joueur
     */
    public ComputerPlayerModel (String pseudonym) {
        super(pseudonym);
        
        this.strategy = new StrategyModel[4];
        this.strategy[0] = new PriorityColorStrategyModel(); 
        this.strategy[1] = new PriorityNumberStrategyModel();
        this.strategy[2] = new PrioritySpecialityStrategyModel();
        this.strategy[3] = new RandomStrategyModel();
    }
    
    /**
     * Permettre de jouer une strategie d'un joueur ordinateur.
     * Le choix de la strategie est choisie de manière 
     * intelligente grâce au controller qui lui est associé
     */
    
    public boolean play (int i) {
        try {
            return this.getStrategy(i).execute();
        } catch (InvalidActionPickCardException
                | InvalidActionPutDownCardException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Choisit la couleur la plus fréquente dans la main du joueur.
     */
    public void chooseColor () throws InvalidColorModelException {
        super.chooseColor(getMaxCountEachColorCard());
    }
    
    /**
     * Obtenir une stratégie en particulier
     * @param i indice de la stratégie souhaitée
     * @return la stratégie souhaitée
     */
    public StrategyModel getStrategy (int i) {
        return this.strategy[i];
    }
    
    /**
     * Obtenir l'indice du maximum de l'ensemble
     * @param array ensemble auquel on va rechercher l'indice du maximum
     * @return l'indice du maximum
     */
    private byte getIndexOfMax (byte [] array) {
        byte indexOfMax = 0, max = 0;
        byte i = 0;
        for ( byte occ : array ) {
            if (occ > max) {
                max = occ;
                indexOfMax = i;
            }
            i++;
        }
        return indexOfMax;
    }
    
    /**
     * Comptabilisation du nombre de carte pour chaque couleur
     * @return la couleur la plus fréquente
     * @throws InvalidColorModelException
     */
    private ColorModel getMaxCountEachColorCard () throws InvalidColorModelException {
        Iterator<CardModel> iter = super.playerHand.getCards().iterator();
        byte [] countColor = new byte [4];
        while (iter.hasNext()) {
            CardModel c = iter.next();
            if ( c.getColor() != null)
                countColor[c.getColor().getId()]++;
        }
        return ColorModel.getColor(getIndexOfMax(countColor));
    }

}
