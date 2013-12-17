package fr.utt.isi.lo02.unoGame.model.player;

import java.util.Iterator;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;
import fr.utt.isi.lo02.unoGame.model.exception.DrawPileIsEmptyAfterReshuffledException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;

/**
 * 
 * Cette classe contient toute la description que peut avoir un joueur
 *
 */
public abstract class PlayerModel {

	protected String pseudonym;
	protected short score;
	protected PlayerHandModel playerHand;
	protected boolean uno;
	
	/**
	 * @param pseudonym
	 * Initialise un joueur humain avec son pseudonym
	 */
	public PlayerModel (String pseudonym) {
	    this.pseudonym = pseudonym;
	    this.score = 0;
	    this.playerHand = new PlayerHandModel();
	    this.uno = false;
	}
	
    public void putDownCard (int indexChoiceCard) throws InvalidActionPutDownCardException {
        if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCard(indexChoiceCard).isPlayableCard() )
            throw new InvalidActionPutDownCardException();
        DiscardPileModel.getUniqueInstance().push(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().removeCard(indexChoiceCard));
    }
	
	public void pickCard () throws InvalidActionPickCardException {
	    if ( DrawPileModel.getUniqueInstance().numberCards() == 0 )
	        throw new InvalidActionPickCardException();
		try {
		    this.playerHand.addCard(DrawPileModel.getUniqueInstance().pop());
		    if ( this.playerHand.numberCards() >= 2 )
		        this.uno = false;
        } catch (DrawPileIsEmptyAfterReshuffledException e) {
            e.printStackTrace();
        }
	}
	
	public void chooseColor (ColorModel choiceColor) {
	    DiscardPileModel.getUniqueInstance().peek().setColor(choiceColor);
    }

	public void notToPlay () throws InvalidActionPickCardException {
	    this.pickCard();
	}
	
    public abstract void play () throws InvalidActionPickCardException, InvalidActionPutDownCardException, InvalidColorModelException;
    public abstract void chooseColor() throws InvalidColorModelException;

    public void againstUno () throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().applyPenaltyAgainstUno();        
    }	
    
    public boolean hasColorBeforeWildDrawFour () {
        Iterator<CardModel> iter = this.playerHand.getCards().iterator();
        while ( iter.hasNext() ) {
            if ( iter.next().getColor() == DiscardPileModel.getUniqueInstance().getColorBeforeWildDrawFour() )
                return true;
        }
        return false;
    }
    
    public void canReceiveAgainstUno () {
        this.uno = true;
    }
    
    public void signalUno () {
        this.uno = false;
    }
	
	public String getPseudonym () {
	    return this.pseudonym;
	}
	
	public short getScore () {
	    return this.score;
	}
	
	public PlayerHandModel getPlayerHand () {
        return this.playerHand;
    }
	
	public boolean getUno() {
	    return this.uno;
    }

    public void setScore (short score) {
        this.score = score;
    }
    
    public void setUno (boolean uno) {
        this.uno = uno;
    }
	
}
