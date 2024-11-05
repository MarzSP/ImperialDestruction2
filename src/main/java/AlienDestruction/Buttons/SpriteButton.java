package AlienDestruction.Buttons;


import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.MouseButton;


/**
 * De SpriteButton klasse is een abstracte klasse die de basis vormt voor de knoppen met een afbeelding in plaats van tekst.
 * Implements: MouseButtonPressedListener
 */
public abstract class SpriteButton extends DynamicSpriteEntity implements MouseButtonPressedListener {

    /**
     * Constructor voor de SpriteButton klasse:
     * @param resource :De locatie van de sprite afbeelding die voor de knop wordt gebruikt.
     * @param initialLocation : De initiële locatie van de knop op het scherm.
     */
    public SpriteButton(final String resource, final Coordinate2D initialLocation) {
        super(resource, initialLocation); }

    /**
     * onMouseButtonPressed: Deze abstracte methode definieert de actie die wordt uitgevoerd wanneer er op de knop wordt geklikt met de muis.
     * @param button       the {@link MouseButton} being pressed
     * @param coordinate2D the current coordinate of the mouse pointer
     */
    abstract public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D);

}