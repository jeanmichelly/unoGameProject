package fr.utt.isi.lo02.unoGame.model.player;

import java.util.ArrayList;
import java.util.Iterator;

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
 * Represente les joueurs ordinateurs. Les joueurs ordinateurs ont une intelligence artificielle et peuvent alors, jouer automatiquement.
 */
public class ComputerPlayerModel extends PlayerModel {

    /**
     * Comporte differentes strategies
     */
    private StrategyModel [] strategy;
    
    /**
     * Construit un joueur ordinateur avec differentes strategies : 
     * <ul>
     *      <li>Priorite aux couleurs</li>
     *      <li>Priorite aux nombres</li>
     *      <li>Priorite aux cartes avec des effets</li>
     *      <li>Choix aleatoire</li>
     * </ul>
     * @param pseudonym represente le pseudo du joueur
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
     * Permettre la gestion du moteur d'un joueur ordinateur 
     * en choisissant de maniere intelligente le choix de la strategie au cours du temps.
     */
    public void play () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        ConsolePlayerHandView.ConsolePlayerHandController.playComputerPlayerModel();
    }
    
    /**
     * Choisit la couleur la plus frequente dans la main du joueur.
     */
    public void chooseColor() throws InvalidColorModelException {
        super.chooseColor(getMaxCountEachColorCard());
    }
    
    /**
     * Obtenir une strategie en particulier
     * @param i indice de la strategie souhaitee
     * @return la strategie souhaitee
     */
    public StrategyModel getStrategy (int i) {
        return this.strategy[i];
    }
    
    /**
     * Obtenir l'ensemble des cartes qui sont jouables.
     * @return les cartes qui sont jouables
     */
    public ArrayList<CardModel> getPlayableCards () {
        Iterator<CardModel> iter = super.playerHand.getCards().iterator();
        ArrayList<CardModel> playableCards = new ArrayList<CardModel>();
        while (iter.hasNext()) {
            CardModel card = iter.next();
            if (card.isPlayableCard())
                playableCards.add(card);
        }
        return playableCards;
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
     * Obtenir la couleur par une cle avec un format nombre 
     * @param index nombre correspondant a une couleur
     * @return la couleur correspondant au nombre 
     * @throws InvalidColorModelException
     */
    private ColorModel getColorModel (byte index) throws InvalidColorModelException {
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
    
    /**
     * Comptabilisation du nombre de carte pour chaque couleur
     * @return la couleur la plus frequente
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
        return getColorModel(getIndexOfMax(countColor));
    }

}
