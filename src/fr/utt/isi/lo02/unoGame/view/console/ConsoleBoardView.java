package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Stack;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.SymbolModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidGameRulesException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidPlayException;
import fr.utt.isi.lo02.unoGame.model.player.HumanPlayerModel;

public class ConsoleBoardView implements Observer {
        
    public ConsoleBoardView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    public static String clear () {
        ConsoleDeckView.clear();
        ConsolePlayersView.clear();
        return "\n\n";
    }
    
    public static String buildHead () {
        return "*********************************************************************************\n"+
               "§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§   UNO GAME   §§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§§\n"+
               "*********************************************************************************\n";
    }
   
    public static String buildMiddle () {
        String antiClockWiseDirection = "";
        String clockWiseDirection = "";
        String marginSupp = "";
        String playerCursor=" ";

        if ( BoardModel.getUniqueInstance().getDirectionOfPlay() == 1 )
            clockWiseDirection = "---->";
        else {
            antiClockWiseDirection = "<----   ";   
            marginSupp = "        ";
        }
        
        if (BoardModel.getUniqueInstance().getPlayerCursor() != 0)
            for (int i=0; i<BoardModel.getUniqueInstance().getPlayerCursor(); i++)
                playerCursor += "        ";
        playerCursor += "~•~";

        return  
                "|                              Partie n°"+BoardModel.getUniqueInstance().getNumberGame()+" | Round n°"+BoardModel.getUniqueInstance().getNumberRound()+"\n"+
                "|\n"+
                "|     "+marginSupp+ConsolePlayersView.getAboveHeads()+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getHeads()+"\n"+
                "|     "+antiClockWiseDirection+ConsolePlayersView.getMiddles()+clockWiseDirection+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getTails()+"\n"+
                "|     "+marginSupp+ConsolePlayersView.getBelowTails()+"\n"+
                "|     "+marginSupp+playerCursor+"\n"+
                "|\n"+
                "|                              "+ConsoleDiscardPileView.buildHead()+"     "+ConsoleDrawPileView.buildHead()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildMiddle()+"     "+ConsoleDrawPileView.buildMiddle()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildTail()+"     "+ConsoleDrawPileView.buildTail()+"                            \n"+
                "|                              "+ConsoleDiscardPileView.buildBelowTail((DiscardPileModel.getUniqueInstance().peek().getColor()==null) ? '•' : DiscardPileModel.getUniqueInstance().peek().getColor().getLabel())+"     "+ConsoleDrawPileView.buildBelowTail()+"                              \n"+
                "|\n"+
                "|     Au tour de "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+"\n"+
                "|     ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n"+
                "|     "+ConsolePlayerHandView.getHeads()+"\n"+
                "|     "+ConsolePlayerHandView.getMiddles()+"\n"+
                "|     "+ConsolePlayerHandView.getTails()+"\n"+
                "|     "+ConsolePlayerHandView.getBelowTails()+"\n"+
                "|     "+ConsolePlayerHandView.getIndexs()+"\n";
    }
    
    public static String buildTail () {
        return "|________________________________________________________________________________\n"+
               "¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n";
    }
    
    public static String build () {
        ConsolePlayerHandView.build();
        ConsolePlayersView.build();
        String build = buildHead()+buildMiddle()+buildTail();

        return clear()+build;
    }

    @Override
    public void update(Observable o, Object arg) {
        String build = buildHead()+buildMiddle()+buildTail();
        System.out.println(clear()+build);
    }
    
    public static void update (String arg) {
        System.out.print(arg);
    }
    
    public static class ConsoleBoardController {
        
        private static Stack<String> playersWinnerGame = new Stack<String>();
        private static int numberGame = 4;
        
        public static void play () throws   InvalidActionPickCardException, 
                                            InvalidColorModelException, 
                                            InvalidGameRulesException, 
                                            InvalidPlayException {
            
            BoardModel.getUniqueInstance().setChanged();
            BoardModel.getUniqueInstance().notifyObservers();
            
            try {
                BoardModel.getUniqueInstance().getPlayer().play();
            } catch (Exception e) {
                InvalidPlayException ipe = new InvalidPlayException();
                ipe.initCause(e);
                throw ipe;
            }   
            controlStateRound();
        }
    
        public static void controlStateRound () throws  InvalidActionPickCardException, 
                                                        InvalidColorModelException, 
                                                        InvalidGameRulesException, 
                                                        InvalidPlayException {
            
            if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasNotCard() )
                roundNotFinish();
            else
                roundFinish();
        }
            
        private static void roundNotFinish () throws    InvalidActionPickCardException, 
                                                        InvalidColorModelException, 
                                                        InvalidGameRulesException, 
                                                        InvalidPlayException {
            
            if ( !DiscardPileModel.getUniqueInstance().hasApplyEffectLastCard() ) {// Appliquer l'effet d'une carte posee une seule fois
                if ( DiscardPileModel.getUniqueInstance().peek().getSymbol() == SymbolModel.WILD_DRAW_FOUR 
                        && BoardModel.getUniqueInstance().getNextPlayer() instanceof HumanPlayerModel) {
                    ((HumanPlayerModel)BoardModel.getUniqueInstance().getNextPlayer()).challengeAgainstWildDrawFourCard();
                } else {
                    BoardModel.getUniqueInstance().applyCardEffect();
                }
            }
            BoardModel.getUniqueInstance().moveCursorToNextPlayer();
            play();  
        }
    
        private static void roundFinish () throws   InvalidActionPickCardException, 
                                                    InvalidColorModelException, 
                                                    InvalidGameRulesException {
            
            BoardModel.getUniqueInstance().applyCardEffect(); // Le joueur a forcément posé une carte
            String playerWinnerRound = BoardModel.getUniqueInstance().getPlayer().getPseudonym(); 
            try {
                BoardModel.getUniqueInstance().getGameRules().countScore();
                if ( !BoardModel.getUniqueInstance().getGameRules().isWinner() ) {
                    BoardModel.getUniqueInstance().setChanged();
                    BoardModel.getUniqueInstance().notifyObservers();
                    BoardModel.getUniqueInstance().nextRound();
                    update(playerWinnerRound + " a gagne la manche !!!\n");
                }
                else {
                    playersWinnerGame.push(playerWinnerRound);
                    BoardModel.getUniqueInstance().setChanged();
                    BoardModel.getUniqueInstance().notifyObservers();
                    BoardModel.getUniqueInstance().nextGame();
                    for (int i=0; i<playersWinnerGame.size(); i++)
                        update("Gagnant de la partie n°"+(i+1)+" : "+playersWinnerGame.get(i)+"\n");
                    update(playersWinnerGame.peek() + " a gagne cette partie !!!\n");
                }
                if ( BoardModel.getUniqueInstance().getNumberGame() <= numberGame )
                    play();
            } 
            catch (Exception e) {
                InvalidGameRulesException igre = new InvalidGameRulesException();
                igre.initCause(e);
                throw igre;
            }      
        }
    }
}
