package fr.utt.isi.lo02.unoGame.view.console;

import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;

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
        
        protected static String responseUser = new String();
        
        public static void play () {
            try {
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
                    hasPlayableCards();
                 } else {
                    notPlayableCards();
                 }
            } catch (InvalidActionPickCardException e) {
                e.printStackTrace();
            }
        }
        
        private static void hasPlayableCards () throws InvalidActionPickCardException {
            Scanner sc = new Scanner(System.in);
            w1: while (true) {
                update("Que voulez vous faire ? (j/n) : ");
                switch (sc.next()) {
                    case "j":
                        update("\n◊ Vous avez avez décidé de poser une carte \n");
                        putDownCard();
                        break w1;
                    case "n":
                        update("\n◊ Vous n'avez pas posé de carte, vous allez alors piocher");
                        BoardModel.getUniqueInstance().getPlayer().notToPlay();
                        BoardModel.getUniqueInstance().setChanged();
                        BoardModel.getUniqueInstance().notifyObservers(); 
                        w2: while (true) {
                            update("Que voulez vous faire ? (j/n) : ");
                            switch (sc.next()) {
                                case "j":
                                    update("\n◊ Vous avez décidé, finalement de poser une carte");
                                    putDownCard();
                                    break w2;
                                case "n":
                                    update("\n◊ Vous passez votre tour");
                                    break w2;
                            }
                        }
                        break w1;
                }
            }
        }

        private static void putDownCard () {
            Scanner sc = new Scanner(System.in);
            try {
                update("Veuillez choisir une carte : ");
                int indexChoiceCard = sc.nextInt();
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().get(indexChoiceCard).isPlayableCard() ) {
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
        
        private static void notPlayableCards () throws InvalidActionPickCardException {
            Scanner sc = new Scanner(System.in);
            w1: while (true) {
                update("\n◊ Pas de carte jouable, vous allez alors piocher");
                BoardModel.getUniqueInstance().getPlayer().pickCard();
                BoardModel.getUniqueInstance().setChanged();
                BoardModel.getUniqueInstance().notifyObservers(); 
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) { // A changer pour performance
                    update("\n◊ La carte piochée est jouable");
                    w2: while (true) {
                        update("Que voulez vous faire ? (j/n) : ");
                        switch (sc.next()) {
                            case "j":
                                update("\n◊ Vous avez posé cette carte");
                                putDownCard();
                                break w2;
                            case "n":
                                update("\n◊ Vous passez votre tour");
                                break w2;
                         }
                     }
                    break w1;
                }
                else {
                    update("\n◊ Vous n'avez toujours pas de carte jouable");
                    break;
                }
             }
        }
    }
    
}
