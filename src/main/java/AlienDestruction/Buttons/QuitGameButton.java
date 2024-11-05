package AlienDestruction.Buttons;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * De QuitGameButton klasse is een sub-klasse van de abstracte Button klasse en specificeert de knop om het spel te verlaten.
 * Deze knop gebruikt System.Exit(0) en sluit het volledige java programma.
 */
public class QuitGameButton extends Button{

    /**
     * Constructor:`QuitGameButton` klasse:
     * @param initialLocation: De initiële locatie van de knop op het scherm.
     * @param text :De tekst die op de knop wordt weergegeven ("Quit Game" is standaard).
     */
    public QuitGameButton(Coordinate2D initialLocation, String text) {
        super(initialLocation, "Quit Game");
        setFill(Color.RED);
        setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 80));
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
    }

    /**
     * onMouseButtonPressed:deze wordt aangeroepen wanneer er op een muisknop wordt gedrukt.
     * @param button       the {@link MouseButton} being pressed
     * @param coordinate2D the current coordinate of the mouse pointer
     */
    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
    System.exit(0); // Eindigd de applicatie.
    }
}
