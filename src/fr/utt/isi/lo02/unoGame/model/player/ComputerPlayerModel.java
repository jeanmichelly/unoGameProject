package fr.utt.isi.lo02.unoGame.model.player;

import java.util.ArrayList;
import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityColorStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PriorityNumberStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.PrioritySpecialityStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.RandomStrategyModel;
import fr.utt.isi.lo02.unoGame.model.player.strategy.StrategyModel;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;
import fr.utt.isi.lo02.unoGame.view.console.ConsolePlayerHandView;

/**
 * 
 * Cette classe represente les joueurs ordinateurs. Les joueurs ordinateurs ont une intelligence artificielle et peuvent alors, jouer automatiquement.
 *
 */
public class ComputerPlayerModel extends PlayerModel {

    private StrategyModel [] strategy;
    
    public ComputerPlayerModel (String pseudonym) {
        super(pseudonym);
        
        this.strategy = new StrategyModel[4];
        this.strategy[0] = new PriorityColorStrategyModel(); 
        this.strategy[1] = new PriorityNumberStrategyModel();
        this.strategy[2] = new PrioritySpecialityStrategyModel();
        this.strategy[3] = new RandomStrategyModel();
    }
    
    @Override
    public void play () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        // if ( BoardModel.getUniqueInstance().getNextPlayer().getPlayerHand().size() < 3 )
        //     strategy[2].execute();
        ConsolePlayerHandView.ConsolePlayerHandController.playComputerPlayerModel();
    }
    
    @Override
    public void chooseColor() throws InvalidColorModelException {
        super.chooseColor(getMaxCountEachColorCard());
    }

    @Override
    public void againstUno() {
        // TODO Auto-generated method stub
        
    }
    
    public StrategyModel getStrategy (int i) {
        return this.strategy[i];
    }
    
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
    
    private ColorModel getMaxCountEachColorCard () throws InvalidColorModelException {
        Iterator<CardModel> iter = super.playerHand.getCards().iterator();
        byte [] countColor = new byte [4];
        while (iter.hasNext()) {
            CardModel c = iter.next();
            if ( c.getColor() != null)
                countColor[c.getColor().getId()]++;
        }
        return ColorModel.getColorModel(getIndexOfMax(countColor));
    }

}
