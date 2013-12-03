package testeur_console;

import java.util.Scanner;
import model.BoardModel;
import model.card.ColorModel;
import model.deck.DiscardPileModel;

public class TesteurConsoleHumanPlayerModel {
    
    public static void play () {
        Scanner sc = new Scanner(System.in);
        if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) {
            w1: while (true) {
                System.out.println("Que voulez vous faire ? (j/n)");
                switch(sc.next()) {
                    case "j":
                        System.out.println("Vous avez décidé de jouer");
                        BoardModel.getUniqueInstance().getPlayer().putDownCard();
                        BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                        break w1;
                    case "n":
                        System.out.println("Vous avez décidé de ne pas jouer, vous allez alors piocher");
                        BoardModel.getUniqueInstance().getPlayer().notToPlay();
                        w2: while (true) {
                            System.out.println("Que voulez vous faire ? (j/n)");
                            switch (sc.next()) {
                                case "j":
                                    System.out.println("Vous avez finalement joué une carte");
                                    BoardModel.getUniqueInstance().getPlayer().putDownCard();
                                    BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                                    break w2;
                                case "n":
                                    System.out.println("Vous passez votre tour");
                                    BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                                    break w2;
                            }
                        }
                        break w1;
                }
            }
         }
         else {
            w3: while (true) {
                System.out.println("Pas de carte jouable, piocher carte");
                BoardModel.getUniqueInstance().getPlayer().pickCard();
                if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().hasPlayableCard() ) { // à changer pour performance
                    System.out.println("Carte jouable");
                    w4: while (true) {
                        System.out.println("Que voulez vous faire ? (j/n)");
                        switch (sc.next()) {
                            case "j":
                                System.out.println("jouer cette carte pioché");
                                BoardModel.getUniqueInstance().getPlayer().putDownCard();
                                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                                break w4;
                            case "n":
                                System.out.println("Ne pas jouer et passer le tour au prochain joueur");
                                BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                                break w4;
                         }
                     }
                    break w3;
                 }
                else {
                    System.out.println("Vous n'avez toujours pas de carte jouable, au tour du prochain joueur");
                    BoardModel.getUniqueInstance().moveCursorToNextPlayer();
                    break;
                }
             }
         }
        sc.close();
    }

    public static void putDownCard () {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Veuillez choisir une carte");
            int indexChoiceCard = sc.nextInt();
            if ( BoardModel.getUniqueInstance().getPlayer().getPlayerHand().get(indexChoiceCard).isPlayableCard() )
                DiscardPileModel.getUniqueInstance().push(BoardModel.getUniqueInstance().getPlayer().getPlayerHand().remove(indexChoiceCard));
            else
                throw new Exception();
        } 
        catch (Exception e) {
            putDownCard();
        }
        finally {
            sc.close();
        }
    }

    public static void chooseColor () {
        Scanner sc = new Scanner(System.in);
        w: while (true) {
            System.out.println("Veuillez choisir une couleur('j', 'r', 'b', 'v')");
            switch ( sc.next() ) {
                case "j":
                    DiscardPileModel.getUniqueInstance().peek().setColor(ColorModel.YELLOW);
                    break w;
                case "r":
                    DiscardPileModel.getUniqueInstance().peek().setColor(ColorModel.RED);
                    break w;
                case "b":
                    DiscardPileModel.getUniqueInstance().peek().setColor(ColorModel.BLUE);
                    break w;
                case "v":
                    DiscardPileModel.getUniqueInstance().peek().setColor(ColorModel.GREEN);
                    break w;
            }
        }
        sc.close();
    }
    
}
