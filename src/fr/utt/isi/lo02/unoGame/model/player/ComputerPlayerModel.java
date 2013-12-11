package fr.utt.isi.lo02.unoGame.model.player;

import java.util.ArrayList;
import java.util.Iterator;
import fr.utt.isi.lo02.unoGame.model.BoardModel;
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
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * 
 * Cette classe represente les joueurs ordinateurs. Les joueurs ordinateurs ont une intelligence artificielle et peuvent alors, jouer automatiquement.
 *
 */
public class ComputerPlayerModel extends PlayerModel {

    private StrategyModel [] strategy;
    
    public ComputerPlayerModel (String pseudonym) {
        super(pseudonym);
        strategy = new StrategyModel[4];
        strategy[0] = new PriorityColorStrategyModel(); 
        strategy[1] = new PriorityNumberStrategyModel();
        strategy[2] = new PrioritySpecialityStrategyModel();
        strategy[3] = new RandomStrategyModel();
    }
    
    @Override
    public void play () throws InvalidActionPickCardException, InvalidActionPutDownCardException {
        // if ( BoardModel.getUniqueInstance().getNextPlayer().getPlayerHand().size() < 3 )
        //     strategy[2].execute();
        
        if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
            pickCard();
            System.out.println(ConsoleBoardView.build()+"◊ "+pseudonym+" n'a pas de carte jouable, il a alors pioché une carte");
        }
        else {
            int i = playerHand.size();
            strategy[3].execute();
            // A retiré mode interface
            if ( playerHand.size() < i )
                System.out.println(ConsoleBoardView.build()+"◊ "+pseudonym+" a posé une carte");
            else
                System.out.println(ConsoleBoardView.build()+"◊ "+pseudonym+" a passé son tour, il a alors pioché une carte");
        }
    }
    
    @Override
    public void chooseColor() {
        try {
            chooseColor(getMaxCountEachColorCard());
        } catch (InvalidColorModelException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    @Override
    public void signalUno() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void againstUno() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void challengeAgainstWildDrawFourCard() {
        // TODO Auto-generated method stub
        
    }
    
    public ArrayList<CardModel> getPlayableCards () {
        Iterator<CardModel> iter = playerHand.getCards().iterator();
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
        Iterator<CardModel> iter = playerHand.getCards().iterator();
        byte [] countColor = new byte [4];
        while (iter.hasNext()) {
            CardModel c = iter.next();
            if ( c.getColor() != null)
                countColor[c.getColor().getId()]++;
        }
        return ColorModel.getColorModel(getIndexOfMax(countColor));
    }

}
