package fr.utt.isi.lo02.unoGame.model.player;

import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;

public abstract class PlayerModel {

	protected String pseudonym;
	protected short score;
	protected PlayerHandModel playerHand;
	
	public PlayerModel (String pseudonym) {
	    this.pseudonym = pseudonym;
	    score = 0;
	    playerHand = new PlayerHandModel();
	}
	
	public void pickCard () {
		playerHand.add(DrawPileModel.getUniqueInstance().pop());
	}

	public void notToPlay () {
	    pickCard();
	}
	
    public abstract void play ();
    public abstract void putDownCard ();
	public abstract void chooseColor ();
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
