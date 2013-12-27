package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import fr.utt.isi.lo02.unoGame.model.board.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidColorModelException;
import fr.utt.isi.lo02.unoGame.model.player.ComputerPlayerModel;

public class ConsolePlayerHandView implements Observer {
        
    public ConsolePlayerHandView () {
        BoardModel.getUniqueInstance().addObserver(this);
    }
    
    protected static String getHeads () {
        return ConsoleDeckView.getHeads();
    }
    
    protected static String getMiddles () {
        return ConsoleDeckView.getMiddles();
    }
    
    protected static String getTails () {
        return ConsoleDeckView.getTails();
    }
    
    protected static String getBelowTails () {
        return ConsoleDeckView.getBelowTails();
    }
    
    protected static String getIndexs () {
        return ConsoleDeckView.getIndexs();
    }
    
    public static String build () {
        return ConsoleDeckView.build(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards());
    }

    @Override
    public void update (Observable o, Object arg) {
        if ( arg instanceof String && arg.equals("CC") )
            ConsolePlayerHandView.ConsolePlayerHandController.chooseColor();
        ConsoleDeckView.build(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCards());   
    }
    
    public static class ConsolePlayerHandController {
                
        public static void playHumanPlayerModel () throws   InvalidActionPickCardException, 
                                                            InvalidColorModelException {
            
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
                hasPlayableCards();
            } else {
                notPlayableCards();
            }
        }
        
        public static void playComputerPlayerModel () throws    InvalidActionPickCardException, 
                                                                InvalidActionPutDownCardException {
            
            if ( BoardModel.getUniqueInstance().hasVulnerablePlayer() ) {
                ConsoleBoardView.update(BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a dit contre uno !");
                BoardModel.getUniqueInstance().getPlayer().againstUno();
            }
            if ( !BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
                BoardModel.getUniqueInstance().getPlayer().pickCard();
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers(); 
                ConsoleBoardView.update("◊ "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" n'a pas de carte jouable, il a alors pioché une carte");
            }
            else {
                int sizePlayerHandBeforePlaying = BoardModel.getUniqueInstance().getPlayer().getPlayerHand().numberCards();

                // Si le joueur suivant lui reste peu de carte, le joueur ordinateur joue agressif !
                if ( BoardModel.getUniqueInstance().getNextPlayer().getPlayerHand().numberCards() < 3 )
                      ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(2).execute();
                else { // Donne la priorite aux nombres ! (Moins de chance de jouer sur des symboles identiques donc faut en profiter!)
                    if ( !((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(1).execute() )
                        if ( !((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(0).execute() )
                            ((ComputerPlayerModel)BoardModel.getUniqueInstance().getPlayer()).getStrategy(3).execute();
                }
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers(); 
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().numberCards() < sizePlayerHandBeforePlaying )
                    ConsoleBoardView.update("◊ "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a posé une carte");
                else
                    ConsoleBoardView.update(ConsoleBoardView.build()+"◊ "+BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a passé son tour, il a alors pioché une carte");
            }
        }
        
        private static void hasPlayableCards () throws  InvalidActionPickCardException, 
                                                        InvalidColorModelException {
            
            Scanner sc = new Scanner(System.in);
            w1: while (true) {
                ConsoleBoardView.update("Que voulez vous faire ? (j/n) : ");
                switch (sc.next()) {
                    case "j":
                        ConsoleBoardView.update("\n◊ Vous avez avez décidé de poser une carte \n\n");
                        // Rends le joueur vulnérable pour un contre uno
                        if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().numberCards() == 2 && !BoardModel.getUniqueInstance().getPlayer().getUno() ) {
                            BoardModel.getUniqueInstance().getPlayer().canReceiveAgainstUno();
                        }
                        putDownCard();
                        break w1;
                    case "n":
                        ConsoleBoardView.update("\n◊ Vous n'avez pas posé de carte, vous allez alors piocher\n");
                        BoardModel.getUniqueInstance().getPlayer().notToPlay();
                        BoardModel.getUniqueInstance().setChanged();
                        BoardModel.getUniqueInstance().notifyObservers(); 
                        w2: while (true) {
                            ConsoleBoardView.update("Que voulez vous faire ? (j/n) : ");
                            switch (sc.next()) {
                                case "j":
                                    ConsoleBoardView.update("\n◊ Vous avez décidé, finalement de poser une carte\n");
                                    putDownCard();
                                    break w2;
                                case "n":
                                    ConsoleBoardView.update("\n◊ Vous passez votre tour\n");
                                    break w2;
                            }
                        }                        
                        break w1;
                    case "-2":
                        ConsoleBoardView.update(BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a dit contre uno !");
                        BoardModel.getUniqueInstance().getPlayer().againstUno();
                }
            }
        }
        
        private static void notPlayableCards () throws InvalidActionPickCardException {
            Scanner sc = new Scanner(System.in);
            w1: while (true) {
                ConsoleBoardView.update("\n◊ Pas de carte jouable, vous allez alors piocher\n");
                BoardModel.getUniqueInstance().getPlayer().pickCard();
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers(); 
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) { // A changer pour performance
                    ConsoleBoardView.update("\n◊ La carte piochée est jouable\n");
                    w2: while (true) {
                        ConsoleBoardView.update("Que voulez vous faire ? (j/n) : ");
                        switch (sc.next()) {
                            case "j":
                                ConsoleBoardView.update("\n◊ Vous avez posé cette carte\n");
                                putDownCard();
                                break w2;
                            case "n":
                                ConsoleBoardView.update("\n◊ Vous passez votre tour\n");
                                break w2;
                            case "-2":
                                ConsoleBoardView.update(BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a dit contre uno !");
                                BoardModel.getUniqueInstance().getPlayer().againstUno();
                         }
                     }
                    break w1;
                }
                else {
                    ConsoleBoardView.update("\n◊ Vous n'avez toujours pas de carte jouable\n");
                    break;
                }
             }
        }

        private static void putDownCard () {
            Scanner sc = new Scanner(System.in);

            try {
                ConsoleBoardView.update("Veuillez choisir une carte : ");
                int indexChoiceCard = sc.nextInt();
                 
                // Le joueur s'immunise contre le contre uno
                if ( indexChoiceCard == -1 && BoardModel.getUniqueInstance().getPlayer().getUno() ) {
                    BoardModel.getUniqueInstance().getPlayer().signalUno();
                    ConsoleBoardView.update(BoardModel.getUniqueInstance().getPlayer().getPseudonym()+" a dit Uno !!!\n");
                }
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().getCard(indexChoiceCard).isPlayableCard() ) {
                    BoardModel.getUniqueInstance().getPlayer().putDownCard(indexChoiceCard);
                    BoardModel.getUniqueInstance().setChanged();
                    BoardModel.getUniqueInstance().notifyObservers();
                }
                else
                    throw new Exception();
            } 
            catch (Exception e) {
                putDownCard();
            }
        }
    
        public static void chooseColor () {
            Scanner sc = new Scanner(System.in);
            w: while (true) {
                ConsoleBoardView.update("Veuillez choisir une couleur('j', 'r', 'b', 'v') : ");
                switch ( sc.next() ) {
                    case "j":
                        BoardModel.getUniqueInstance().getPlayer().chooseColor(ColorModel.YELLOW);
                        break w;
                    case "r":
                        BoardModel.getUniqueInstance().getPlayer().chooseColor(ColorModel.RED);
                        break w;
                    case "b":
                        BoardModel.getUniqueInstance().getPlayer().chooseColor(ColorModel.BLUE);
                        break w;
                    case "v":
                        BoardModel.getUniqueInstance().getPlayer().chooseColor(ColorModel.GREEN);
                        break w;
                }
            }
        }

        public static void challengeAgainstWildDrawFourCard () throws   InvalidActionPickCardException, 
                                                                        InvalidColorModelException {
            
            String launcherWildDrawFourCard = BoardModel.getUniqueInstance().getPlayer().getPseudonym();
            String targetWildDrawFourCard = BoardModel.getUniqueInstance().getNextPlayer().getPseudonym();
            Scanner sc = new Scanner(System.in);
            
            w: while (true) {
                ConsoleBoardView.update(launcherWildDrawFourCard+" a joué une carte +4 sur "+targetWildDrawFourCard+"\n");
                ConsoleBoardView.update(targetWildDrawFourCard+", pensez vous que "+
                        launcherWildDrawFourCard+" a une carte de la même couleur que la carte précédente ? (o/n)\n");
                switch (sc.next()) {  
                    case "o":
                        BoardModel.getUniqueInstance().applyCardEffect();
                        break w;
                    case "n":
                        if ( BoardModel.getUniqueInstance().getPlayer().hasColorBeforeWildDrawFour() ) {
                            BoardModel.getUniqueInstance().getPlayer().getPlayerHand().addCard(DiscardPileModel.getUniqueInstance().pop());
                            BoardModel.getUniqueInstance().applyPenaltyAgainstLauncherWildDrawFourCard();
                            ConsoleBoardView.update(launcherWildDrawFourCard+
                                    " a la couleur de la carte précédente, il a alors pioché 4 cartes et repris sa carte +4\n");
                        } else {
                            BoardModel.getUniqueInstance().applyPenaltyAgainstWildDrawFourCard();
                            ConsoleBoardView.update(launcherWildDrawFourCard + " : ");
                            BoardModel.getUniqueInstance().applyCardEffect();
                            ConsoleBoardView.update(launcherWildDrawFourCard+" n'a pas la couleur de la carte précédente, "+
                                   targetWildDrawFourCard+" a alors piocher 6 cartes\n");
                        }
                        break w;
                }
            }
        }
    }
    
}
