package AlienDestruction.Buttons;
import AlienDestruction.App;
import AlienDestruction.Helper;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * De PlayAgainButton is een sub-class van Button.
 * Deze knop wordt in het Eindscherm gebruikt om het spel opnieuw op te starten nadat het spel is afgelopen.
 * Button: PlayAgainButton erft functionaliteit van de Button klasse, die de basisfunctionaliteit van knoppen verzorgt (zoals tekst weergeven, reageren op muisklik events).
 */
    public class PlayAgainButton extends Button {
        protected App app;

    /**
     * public PlayAgainButton(Coordinate2D initialLocation, App app):
     *@param initialLocation : de locatie van de knop
     *@param app : Een koppeling naar app, waar de scenes worden geinitialiseert.
     *  Initialiseert de knop met de tekst "Play again!" op de opgegeven locatie.
     *  Stelt de knopkleur in op zwart (setFill(Color.BLACK))
     *  Stelt de randdikte in op 2 pixels (setStrokeWidth(2))
     *  Stelt de randkleur in op goud (setStrokeColor(Color.GOLD))
     *  Stelt het lettertype in op "FUTURA BOLD", Bold, grootte 70 (setFont(Font.font("FUTURA BOLD", FontWeight.BOLD, 70)))
     *  Stelt het ankerpunt van de knop in op het midden (setAnchorPoint(AnchorPoint.CENTER_CENTER))
     */
        public PlayAgainButton(Coordinate2D initialLocation, App app) {
            super(initialLocation, "Play again!");
            this.app = app;
            setFill(Color.BLACK);
            setStrokeWidth(2);
            setStrokeColor(Color.GOLD);
            setFont(Font.font("FUTURA BOLD", FontWeight.BOLD, 70));
            setAnchorPoint(AnchorPoint.CENTER_CENTER);
        }

    /**
     * Deze methode wordt opgeroepen wanneer er op de knop geklikt wordt.
     * Controleert of de geklikte knop de primaire muisknop is (linker muisknop).
     * Gebruikt de app referentie om de actieve scene te veranderen naar de "StartScreen" scene met app.setActiveScene(Helper.SceneIds.StartScreen). .
     * @param button       the {@link MouseButton} being pressed
     * @param coordinate2D the current coordinate of the mouse pointer
     */
        @Override
        public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
            if (button == MouseButton.PRIMARY) {
                app.setActiveScene(Helper.SceneIds.StartScreen);
}
        }
    }

