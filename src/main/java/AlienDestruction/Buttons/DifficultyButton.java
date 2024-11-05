package AlienDestruction.Buttons;


import AlienDestruction.App;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Dit is een sub-klasse van Button die knoppen maakt voor de verschillende moeilijkheidsgraden(Easy, Medium, Hard).
 * Button: DifficultyButtons erft van de Button klasse, wat betekent dat het de basisfunctionaliteit van knoppen (zoals tekst weergeven, reageren op muisgebeurtenissen) heeft.
 */
public class DifficultyButton extends Button {
    /**
     * DIFFICULTIES: Een statische array met de namen van de moeilijkheidsgraden ("Easy", "Medium", "Hard").
     * Deze array is statisch omdat de waardes niet zullen veranderen tijdens de runtime van deze applicatie.
     */
    private static final String[] DIFFICULTIES = {"Easy", "Medium", "Hard"};
    private final int difficultyIndex;
    private final App app;

    /**
     *  * Constructor voor de `DifficultyButtons` klasse:
     * @param initialLocation:De initiële locatie van de knop op het scherm.
     * @param difficultyIndex: De index van de moeilijkheidsgraad in de `DIFFICULTIES` array (0 = Easy, 1 = Medium, 2 = Hard).
     * Initialiseert de tekst van de knop met de naam van de geselecteerde moeilijkheidsgraad (DIFFICULTIES[difficultyIndex]).
     * Stelt de knopkleur in op goud (setFill(Color.GOLD)).
     * Stelt het lettertype in op Impact, semi-Bold, grootte 40 (setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 40))).
     * Stelt het ankerpunt van de knop in op het midden-links (setAnchorPoint(AnchorPoint.CENTER_LEFT)).
     */
    public DifficultyButton(Coordinate2D initialLocation, int difficultyIndex, App app) {
        super(initialLocation, DIFFICULTIES[difficultyIndex]);
        this.difficultyIndex = difficultyIndex;
        this.app = app;
        setFill(Color.GOLD);
        setFont(Font.font("Impact", FontWeight.SEMI_BOLD, 40));
        setAnchorPoint(AnchorPoint.CENTER_LEFT);
    }

    /**
     *onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) (overridden): Deze methode wordt opgeroepen wanneer er op de knop geklikt wordt
     * @param button
     * @param coordinate2D
     */

    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
        app.setDifficulty(difficultyIndex);
    }
}
