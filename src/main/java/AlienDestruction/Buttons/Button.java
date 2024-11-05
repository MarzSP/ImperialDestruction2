package AlienDestruction.Buttons;


import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import com.github.hanyaeger.api.userinput.MouseEnterListener;
import com.github.hanyaeger.api.userinput.MouseExitListener;
import javafx.scene.Cursor;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * De abstracte Button klasse vormt de basis voor de knoppen in de game. Knoppen erven van deze klasse de abstracte methode ‘onMouseButtonPressed’.
 * Extends: TextEntity: Button erft van de TextEntity klasse, waardoor knoppen tekst kunnen weergeven.
 * Implements: MouseButtonPressedListener, MouseExitListener, MouseEnterListener.
 */
public abstract class Button extends TextEntity implements MouseButtonPressedListener, MouseExitListener, MouseEnterListener {

    /**
     *  Constructor: Constructor voor de Button klasse:
     *     @param initialLocation: De beginpositie van de knop op het scherm.
     *     @param text: De tekst die op de knop getoond wordt.
     *     Standaard lettertype: Roboto, SEMI_BOLD, grootte 30.
    **/
    public Button(final Coordinate2D initialLocation, final String text) {
        super(initialLocation, text);
        setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 30));
    }

    /**
     * abstract public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D);:
     * Deze abstracte methode moet door subklassen worden geimplementeerd om te definiëren wat er gebeurt als er op de knop geklikt wordt.
     * De methode krijgt informatie over de geklikte muisknop (button) en de coordinaten van de klik (coordinate2D).
     * @param button       the {@link MouseButton} being pressed
     * @param coordinate2D the current coordinate of the mouse pointer
     */
    abstract public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D);

    /**
     * public void onMouseEntered() (overridden): Deze methode wordt automatisch aangeroepen wanneer de muiscursor boven de knop beweegt.
     * De knopkleur verandert naar rood en de cursor is een standaard cursor.
     */
    @Override
    public void onMouseEntered() {
        setFill(Color.RED);
        setCursor(Cursor.DEFAULT);
    }

    /**
     * public void onMouseExited() (overridden): Deze methode wordt automatisch aangeroepen wanneer de muiscursor van de knop wegbeweegt.
     * De knopkleur verandert terug naar goud en de cursor is een standaard cursor.
     */
    @Override
    public void onMouseExited() {
        setFill(Color.GOLD);
        setCursor(Cursor.DEFAULT);
    }
}