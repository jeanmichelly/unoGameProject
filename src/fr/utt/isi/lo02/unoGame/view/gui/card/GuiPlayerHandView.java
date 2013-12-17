package fr.utt.isi.lo02.unoGame.view.gui.card;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import fr.utt.isi.lo02.unoGame.model.card.CardModel;
import fr.utt.isi.lo02.unoGame.model.deck.PlayerHandModel;

import java.util.ArrayList;

public class GuiPlayerHandView extends Group {

    private PlayerHandModel playerHandModel;
    private int localCardWidth;
    private int localCardHeight;

    public GuiPlayerHandView(PlayerHandModel playerHandModel, int x, int y, int width, int height) {

        this.setBounds(x,y,width,height);
        this.playerHandModel = playerHandModel;

         /* Calcul de la taille des cartes en fonction de la taille du ruban */
        this.localCardWidth = (int) (width / 1.5f);
        this.localCardHeight = (int) (height / 1.3);
        /* */

        float numberOfCard = this.playerHandModel.getCards().size();
        int a = 1;
        float theta = -5;
        float thetaVariation = (2 * Math.abs(theta)) / numberOfCard;

        for(CardModel cardModel : this.playerHandModel.getCards()) {
            GuiCardView cardView = new GuiCardView(cardModel);

            Vector2 localCardPosition = new Vector2();

            float middleOfPanel = super.getWidth() / 2;
            int midCardIndex = (int) Math.ceil(numberOfCard/2);

            cardView.setRotation(theta);

            if(numberOfCard%2 != 0){ // Si le nombre de carte est impair
                if(a == midCardIndex){
                    localCardPosition.x = middleOfPanel - localCardWidth / 2;
                    cardView.setRotation(0);
                }
                if(a < midCardIndex){
                    localCardPosition.x = middleOfPanel - ((midCardIndex - a) * localCardWidth) - localCardWidth / 2;
                }
                if(a > midCardIndex){
                    localCardPosition.x = middleOfPanel + ((a - midCardIndex) * localCardWidth) - localCardWidth / 2;
                }
            } else {
                if(a <= midCardIndex){
                    int mainDecalBefore = midCardIndex - a + 1;
                    localCardPosition.x = ((middleOfPanel) - mainDecalBefore * localCardWidth);
                }
                if(a > midCardIndex){
                    int mainDecalAfter = a - (midCardIndex + 1);
                    localCardPosition.x = ((middleOfPanel) + mainDecalAfter * localCardWidth);
                }
            }

            /* Conversion des coordonnées locales au groupe aux coordonnées réelles */
                int xCardPosition = (int) (localCardPosition.x + super.getX());
                int yCardPosition = (int) (localCardPosition.y + super.getY());
            /* */

            System.out.println(xCardPosition +" - "+ yCardPosition +" - "+ localCardWidth +" - "+ localCardHeight);

            cardView.setBounds(xCardPosition, yCardPosition, localCardWidth, localCardHeight);

            this.addActor(cardView);

            theta += thetaVariation;
            a++;
        }
    }
}
