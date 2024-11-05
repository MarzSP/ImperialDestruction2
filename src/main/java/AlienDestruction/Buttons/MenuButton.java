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
 * De MenuButton is sub-klsse van de Button klasse.
 * De knop wordt gebruikt om een menu met opties weer te geven wanneer de speler erop klikt.
 */
public class MenuButton extends Button {
    private final App app;

    /**
     *  De constructor initialiseert een nieuw MenuButton-object en stelt de volgende eigenschappen in:
     *  initialLocation, Tekstlabel "Menu", tekstkleur, lettertype en ankerpunt
     * @param app De referentie naar de `App`-instantie waaraan de MenuButton is gekoppeld (omdat deze ook scene-veranderingen kunnen bewerkstelligen)
     * @param initialLocation initialLocation De initiële locatie van de knop in 2D-coördinaten.
     */
    public MenuButton(App app, Coordinate2D initialLocation) {
        super(initialLocation, "Menu");
        this.app = app;
        setFill(Color.GOLD);
        setFont(Font.font("Roboto", FontWeight.BOLD, 20));
        setAnchorPoint(AnchorPoint.TOP_RIGHT);
    }

    /**
     * Deze publieke methode geeft aan dat deze wordt aangeroepen wanneer er op een muisknop wordt gedrukt.
     *
     * @param button       de {@link MouseButton} klik op de muis.
     * @param coordinate2D de huidige locatie van waar de muisklik op de knop op het scherm is gebeurd.
     * app.setActiveScene(Helper.SceneIds.GameMenu);: Deze regel vormt de kernfunctionaliteit van de methode.Het veranderd het scherm (Scene)
     * De app-object wordt gebruikt om de actieve scène te wijzigen.
     */
        @Override
        public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
            app.setActiveScene(Helper.SceneIds.GameMenu);
            }
    }

