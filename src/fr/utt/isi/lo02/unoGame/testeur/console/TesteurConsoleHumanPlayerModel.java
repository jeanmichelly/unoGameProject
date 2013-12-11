package fr.utt.isi.lo02.unoGame.testeur.console;


import java.util.Scanner;

import fr.utt.isi.lo02.unoGame.model.BoardModel;
import fr.utt.isi.lo02.unoGame.model.card.ColorModel;
import fr.utt.isi.lo02.unoGame.model.deck.DiscardPileModel;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPickCardException;
import fr.utt.isi.lo02.unoGame.model.exception.InvalidActionPutDownCardException;
import fr.utt.isi.lo02.unoGame.view.console.ConsoleBoardView;

/**
 * 
 * @version JRE 1.7
 *
 */
public class TesteurConsoleHumanPlayerModel {
    
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
            System.out.print("Que voulez vous faire ? (j/n) : ");
            switch (sc.next()) {
                case "j":
                    System.out.println("\n◊ Vous avez avez décidé de poser une carte \n");
                    putDownCard();
                    break w1;
                case "n":
                    System.out.println("\n◊ Vous n'avez pas posé de carte, vous allez alors piocher");
                    BoardModel.getUniqueInstance().getPlayer().notToPlay();
                    System.out.println(ConsoleBoardView.build());
                    w2: while (true) {
                        System.out.print("Que voulez vous faire ? (j/n) : ");
                        switch (sc.next()) {
                            case "j":
                                System.out.println("\n◊ Vous avez décidé, finalement de poser une carte");
                                putDownCard();
                                break w2;
                            case "n":
                                System.out.println("\n◊ Vous passez votre tour");
                                break w2;
                        }
                    }
                    break w1;
            }
        }
    }
    
    private static void notPlayableCards () throws InvalidActionPickCardException {
        Scanner sc = new Scanner(System.in);
        w1: while (true) {
            System.out.println("\n◊ Pas de carte jouable, vous allez alors piocher");
            BoardModel.getUniqueInstance().getPlayer().pickCard();
            System.out.println(ConsoleBoardView.build());
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) { // A changer pour performance
                System.out.println("\n◊ La carte piochée est jouable");
                w2: while (true) {
                    System.out.print("Que voulez vous faire ? (j/n) : ");
                    switch (sc.next()) {
                        case "j":
                            System.out.println("\n◊ Vous avez posé cette carte");
                            putDownCard();
                            break w2;
                        case "n":
                            System.out.println("\n◊ Vous passez votre tour");
                            break w2;
                     }
                 }
                break w1;
            }
            else {
                System.out.println("\n◊ Vous n'avez toujours pas de carte jouable");
                break;
            }
         }
    }

    private static void putDownCard () {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Veuillez choisir une carte : ");
            int indexChoiceCard = sc.nextInt();
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().get(indexChoiceCard).isPlayableCard() ) {
                BoardModel.getUniqueInstance().getPlayer().putDownCard(indexChoiceCard);
                System.out.println(ConsoleBoardView.build());
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
            System.out.print("Veuillez choisir une couleur('j', 'r', 'b', 'v') : ");
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
    
}
