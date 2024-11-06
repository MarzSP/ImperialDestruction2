package AlienDestruction.Scenes;


import AlienDestruction.Buttons.QuitGameButton;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Deze klasse GameMenu is een sub-klasse van MenuManager die het in-game menu beheert.
 * Het is verantwoordelijk voor het opzetten van de achtergrond afbeelding, de titeltekst, en de knoppen Quit Game en Play Again.
 */
public class GameMenu extends MenuManager {
    /**
     * Methode setupScene() stelt een achtergrondafbeelding in voor het menu scherm.
     */
    public void setupScene() {
        setBackgroundImage("backgrounds/universe1.jpg");
    }

    @Override
    public void setupEntities() {
        super.setupEntities();
        addGameMenuText();
        addQuitGameButton();
    }

    private void addGameMenuText() {
        var gameMenuText = new TextEntity(
                new Coordinate2D(getWidth() / 2, getHeight() / 4),
                "Game Menu");
        gameMenuText.setAnchorPoint(AnchorPoint.TOP_CENTER);
        gameMenuText.setFill(Color.ANTIQUEWHITE);
        gameMenuText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, getWidth() / 25));
        addEntity(gameMenuText);
    }

    private void addQuitGameButton() {
        var quitGameButton = new QuitGameButton(new Coordinate2D(getWidth() / 2, getHeight() * 5 / 6), "Quit Game");
        addEntity(quitGameButton);
    }
}
