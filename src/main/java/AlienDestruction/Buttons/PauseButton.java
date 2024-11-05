package AlienDestruction.Buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.scenes.DynamicScene;
import javafx.scene.input.MouseButton;

/**
 * De `PauseButton` class vertegenwoordigt een knop in het GameScren waarmee de speler het spel kan pauzeren en hervatten.
 */
public class PauseButton extends SpriteButton {
    /**
     * isPaused: Een boolean waarde die aangeeft of het spel momenteel gepauzeerd is (`true`) of actief is (`false`).
     * resourcePause: Een `static final String` waarde die het pad naar de afbeelding van de pauzeer knop aangeeft ("sprites/pause-button.png").
     * gameScreen: Een `DynamicScene` referentie naar de actieve gameScreen
     */
    private boolean isPaused;
    private static final String resourcePause = "sprites/pause-button.png";
    private final DynamicScene gameScreen;

    /**
     * Constructor: `PauseButton(Coordinate2D initialLocation, DynamicScene gameScreen)`:
     * Initialiseert een nieuwe `PauseButton` object. op de opgegeven locatie (`initialLocation`) en linkt deze aan het`gameScreen`.
     * @param initialLocation : de opgegeven locatie (`initialLocation`) van de knop
     * @param gameScreen : Linkt de knop aan gameScreen
     */
    public PauseButton(Coordinate2D initialLocation, DynamicScene gameScreen) {
        super(resourcePause, initialLocation);
        this.gameScreen = gameScreen;
        isPaused = false; //
    }

    /**
     *  pauseGame(): Pauzeert de actieve gameScreen.
     */
    private void pauseGame() {
        gameScreen.pause();
        System.out.println("Game Paused!");
    }

    /**
     * Hervat het gepauzeerde gameScreen.
     */
    private void resumeGame() {
        gameScreen.resume();
        System.out.println("Game Resumed!");
    }

    /**
     * Reageert op een muisklik op de knop/sprite
     * Als het spel gepauzeerd is (isPaused is true), hervat het spel (resumeGame()).
     * Anders, pauzeert het spel (pauseGame()).
     * Wisselt vervolgens de waarde van isPaused om de huidige pauzestatus te reflecteren.
     *
     * @param button       de knop die wordt geklikt
     * @param coordinate2D huidige locatie van de klik
     */
    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
        if (isPaused) {
            resumeGame();
        } else {
            pauseGame();
        }
        isPaused = !isPaused;
    }
}


