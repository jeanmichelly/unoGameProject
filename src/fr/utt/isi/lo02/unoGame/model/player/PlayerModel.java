package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;

/**
 * 
 * Cette classe contient toute la description que peut avoir un joueur
 *
 */
public abstract class PlayerModel {

	protected String pseudonym;
	protected short score;
	protected PlayerHandModel playerHand;
	
	public PlayerModel (String pseudonym) {
	    this.pseudonym = pseudonym;
	    score = 0;
	    playerHand = new PlayerHandModel();
	}
	
    public void putDownCard (int indexChoiceCard) throws InvalidActionPutDownCardException {
        if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().get(indexChoiceCard).isPlayableCard() )
            throw new InvalidActionPutDownCardException();
        DiscardPileModel.getUniqueInstance().push(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().remove(indexChoiceCard));
    }
	
	public void pickCard () throws InvalidActionPickCardException {
	    if ( DrawPileModel.getUniqueInstance().size() == 0 )
	        throw new InvalidActionPickCardException();
		try {
            playerHand.add(DrawPileModel.getUniqueInstance().pop());
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
	}
	
	public void chooseColor (ColorModel choiceColor) {
	    DiscardPileModel.getUniqueInstance().peek().setColor(choiceColor);
    }

	public void notToPlay () throws InvalidActionPickCardException {
	    pickCard();
	}
	
    public abstract void play () throws InvalidActionPickCardException, InvalidActionPutDownCardException;
    public abstract void chooseColor();
	public abstract void signalUno ();
	public abstract void againstUno ();
	public abstract void challengeAgainstWildDrawFourCard ();
	
	public String getPseudonym () {
	    return pseudonym;
	}
	
	public short getScore () {
	    return score;
	}
	
	public PlayerHandModel getPlayerHand () {
        return playerHand;
    }

    public void setScore (short score) {
        this.score = score;
    }
	
}
