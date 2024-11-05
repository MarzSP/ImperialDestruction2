package AlienDestruction.Buttons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.media.SoundClip;
import javafx.scene.input.MouseButton;

/**
 * De MusicButton klasse is een sub-klasse van de abstracte SpriteButton klasse en specificeert een sprite knop om de achtergrondmuziek te visueel te kunnen bedienen.
 * SpriteButton: MusicButton erft functionaliteit van de SpriteButton klasse, die knoppen met sprites implementeert.
 */
public class MusicButton extends SpriteButton {
    /**
     * ResourceSpeakerOn: Statische string met het pad naar de afbeelding "sprites/Speaker.png". (Deze is statisch omdat de sprite niet zal veranderen)
     * musicClip: Een SoundClip object dat de achtergrond muziek bevat die door de knop wordt bediend.
     * isActive: Een boolean  die aangeeft of de muziek op dit moment actief afspeelt (true) of gepauzeerd is (false).
     */
    private static final String ResourceSpeakerOn = "sprites/Speaker.png";
    private final SoundClip musicClip;
    private boolean isActive = false;

    /**
     * Constructor voor de `MusicButton` klasse:
     * @param initialLocation : De initiele locatie van de knop op het scherm.
     * @param musicClip : Het 'SoundClip' object dat de achtergrondmuziek bevat.
     * @param size : Formaat van de sprite
     */
    public MusicButton(
            Coordinate2D initialLocation,
            SoundClip musicClip,
            Size size
    ) {
        super(ResourceSpeakerOn, initialLocation);
        this.musicClip = musicClip;
    }

    /**
     * onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) (overridden):
     *     Deze methode wordt opgeroepen wanneer er op de knop wordt geklikt.
     *     Wisselt de actieve status (isActive) van de knop en speelt of pauzeert de achtergrondmuziek af met behulp van de musicClip.
     *         Als de knop actief is:
     *             Zet isActive op false.
     *             Stop de muziek met musicClip.stop().
     *         Als de knop niet actief is:
     *             Zet isActive op true.
     *             Speel de muziek af met musicClip.play().
     * @param button       the {@link MouseButton} being pressed
     * @param coordinate2D the current coordinate of the mouse pointer
     */
    @Override
    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
        if (isActive) {
            isActive = false;
            musicClip.stop();
        } else {
            isActive = true;
            musicClip.play();
        }
    }
}
