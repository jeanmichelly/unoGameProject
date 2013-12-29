package fr.utt.isi.lo02.unoGame.controller.board;

import java.util.Scanner;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DrawPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;


public class ComputerPlayerController extends ClickListener {

    ComputerPlayerModel computerPlayerModel;

    public ComputerPlayerController(ComputerPlayerModel computerPlayerModel) {
        this.computerPlayerModel = computerPlayerModel;
    }
    
    public void play () {
        try {
            if ( BoardModel.getUniqueInstance().hasVulnerablePlayer() ) {
                computerPlayerModel.againstUno();
            }
            
            if ( !computerPlayerModel.getPlayerHand().hasPlayableCard() ) {
                computerPlayerModel.pickCard();
                DrawPileModel.getUniqueInstance().setDrawable(false);
            } else {
            // Si le joueur suivant lui reste peu de carte, le joueur ordinateur joue agressif !
                if ( BoardModel.getUniqueInstance().getNextPlayer().getPlayerHand().numberCards() < 3 ) {
                      computerPlayerModel.play(2);
                } else { // Donne la priorite aux nombres ! (Moins de chance de jouer sur des symboles identiques donc faut en profiter!)
                    if ( !computerPlayerModel.play(1) ) {
                        if ( !computerPlayerModel.play(0) ) {
                            computerPlayerModel.play(3);
                        }
                    }
                } 
            } 
        } catch (InvalidActionPickCardException e) {
            e.printStackTrace();
        }
    }
    
    public void clicked (InputEvent event, float x, float y) {
        DrawPileModel.getUniqueInstance().setDrawable(true);
        BoardModel.getUniqueInstance().setChanged();
        BoardModel.getUniqueInstance().notifyObservers();
    }
    
}
