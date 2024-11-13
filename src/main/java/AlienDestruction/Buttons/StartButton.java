package AlienDestruction.Buttons;

import AlienDestruction.App;
import AlienDestruction.Helper;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.userinput.MouseButtonPressedListener;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StartButton extends Button implements MouseButtonPressedListener {

    protected App app;

    /**
     * Constructor: StartButton klasse.
     * @param app :De instantie van de hoofdapplicatie.
     * @param initialLocation: De initiële locatie van de knop op het scherm.
     */
    public StartButton (App app, Coordinate2D initialLocation) {
        super(initialLocation, "Start Game!");
        this.app = app;
        setFill(Color.BLACK);
        setStrokeWidth(2);
        setStrokeColor(Color.GOLD);
        setFont(Font.font("FUTURA BOLD", FontWeight.BOLD, 80));
        setAnchorPoint(AnchorPoint.CENTER_CENTER);
    }

    /**
     * Deze publieke methode geeft aan dat deze wordt aangeroepen wanneer er op een muisknop wordt gedrukt.
     * @param mousebutton  de {@link MouseButton} die geklikt wordt.
     * @param coordinate2D huidige locatie van de muis.
     * app.setActiveScene(Helper.SceneIds.GameMenu);: Deze regel vormt de kernfunctionaliteit van de methode: Het veranderd het scherm (Scene)
     * De app-object wordt gebruikt om de actieve scène te wijzigen.
     */
    @Override
    public void onMouseButtonPressed(MouseButton mousebutton, Coordinate2D coordinate2D){
        app.setActiveScene(Helper.SceneIds.GameScreen);
    }
}
