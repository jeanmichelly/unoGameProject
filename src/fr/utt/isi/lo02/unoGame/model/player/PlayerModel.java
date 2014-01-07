package fr.utt.isi.lo02.unoGame.model.player;

import java.io.Serializable;
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
 * Définit le modèle commun d'un joueur
 */
public abstract class PlayerModel implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identifie le joueur avec un pseudo
     */
	protected String pseudonym;
	/**
	 * Stock le score du joueur
	 */
	protected short score;
	/**
	 * Chaque joueur possède des cartes
	 */
	protected PlayerHandModel playerHand;
	/**
	 * Flag qui informe si un joueur est vulnérable. Cela permet la gestion d'un contre uno.
	 */
	protected boolean uno;
	
	/**
	 * Construit un joueur humain avec son pseudonym. 
	 * Il initialise son score a 0, sa main à vide et le flag à false
	 * @param pseudonym représente le pseudo du joueur
	 */
	public PlayerModel (String pseudonym) {
	    this.pseudonym = pseudonym;
	    this.score = 0;
	    this.playerHand = new PlayerHandModel();
	    this.uno = false;
	}
	
	/**
	 * Permet de poser une carte sur le talon.
	 * @param indexChoiceCard
	 * @throws InvalidActionPutDownCardException
	 */
    public void putDownCard (int indexChoiceCard) throws InvalidActionPutDownCardException {
        if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCard(indexChoiceCard).isPlayableCard() ) {
        	System.out.println(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCard(indexChoiceCard).getCard());
            throw new InvalidActionPutDownCardException();
        }
        DiscardPileModel.getUniqueInstance().push(
                BoardModel.getUniqueInstance().getPlayer().getPlayerHand().removeCard(indexChoiceCard)
        );
    }
	
    /**
     * Permet de piocher une carte. Si le joueur a 2 cartes ou plus, le flag passe ou reste à false.
     * Si la pioche n'a pas assez de carte même après un remélange grâce au talon, alors on ignore
     * @throws InvalidActionPickCardException
     */
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
	
	/**
	 * Choisit la couleur par l'utilisateur
	 * @param choiceColor
	 */
	public void chooseColor (ColorModel choiceColor) {
	    DiscardPileModel.getUniqueInstance().peek().setColor(choiceColor);
    }

	/**
	 * Passer son tour
	 * @throws InvalidActionPickCardException
	 */
	public void notToPlay () throws InvalidActionPickCardException {
	    this.pickCard();
	}
	
	/**
	 * Permet au joueur de jouer
	 * @throws InvalidActionPickCardException
	 * @throws InvalidActionPutDownCardException
	 * @throws InvalidColorModelException
	 */
    
    /**
     * Permet au joueur de choisir une couleur
     * @throws InvalidColorModelException
     */
    public abstract void chooseColor () throws InvalidColorModelException;

    /**
     * Permet au joueur de dire contre uno
     * @throws InvalidActionPickCardException
     */
    public void againstUno () throws InvalidActionPickCardException {
        BoardModel.getUniqueInstance().applyPenaltyAgainstUno();        
    }	
    
    /**
     * Permet de savoir si le joueur a la couleur du sommet avant qu'il est jouée une carte +4 
     * @return
     */
    public boolean hasColorBeforeWildDrawFour () {
        Iterator<CardModel> iter = this.playerHand.getCards().iterator();
        while ( iter.hasNext() ) {
            if ( iter.next().getColor() == DiscardPileModel.getUniqueInstance().getColorBeforeWildDrawFour() )
                return true;
        }
        return false;
    }
    
    /**
     * Rend vulnérable le joueur contre le uno
     */
    public void canReceiveAgainstUno () {
        this.uno = true;
    }
    
    /**
     * Le joueur peut dire uno lorsqu'il joue son avant derniere carte 
     * ou s'il lui reste 1 carte et que personne ne s'en rends compte
     */
    public void signalUno () {
        this.uno = false;
    }
	
    /**
     * Obtenir le pseudonym du joueur
     * @return le pseudonym du joueur
     */
	public String getPseudonym () {
	    return this.pseudonym;
	}
	
	/**
	 * Obtenir le score du joueur
	 * @return le score du joueur
	 */
	public short getScore () {
	    return this.score;
	}
	
	/**
	 * Obtenir les cartes du joueur
	 * @return les cartes du joueur
	 */
	public PlayerHandModel getPlayerHand () {
        return this.playerHand;
    }
	
	/**
	 * Informe sur l'état de vulnérabilite du joueur
	 * @return true si le joueur est vulnérable, false sinon
	 */
	public boolean getUno() {
	    return this.uno;
    }

	/**
	 * Modifie le score du joueur
	 * @param score mise à jour du score du joueur
	 */
    public void setScore (short score) {
        this.score = score;
    }
    
    /**
     * Modifie le flag de vulnerabilite
     * @param uno mise à jour du flag, true si le joueur est vulnerable, false sinon.
     */
    public void setUno (boolean uno) {
        this.uno = uno;
    }
	
}
