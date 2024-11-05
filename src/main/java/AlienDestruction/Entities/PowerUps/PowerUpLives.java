package AlienDestruction.Entities.PowerUps;

import AlienDestruction.Helper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.ContinuousRotatable;

/**De klasse PowerUpLives is een power-up in de game die 1 extra leven toegevoegd aan de speler.
 * Deze klasse erft van de PowerUps klasse en implementeert de ContinuousRotatable interface.
 * De implementatie van PowerUpLives zit in Player in OnCollision.
 */
public class PowerUpLives extends PowerUps implements ContinuousRotatable {

    /**
     * Constructor:
     * @param initialLocation : De beginpositie van de power-up op het scherm (als Coordinate2D object)
     * Sprite afbeelding: "sprites/xWingPowerUp.png" : een visuele representatie van een extra leven
     * Grootte: Size(40,40)
     * Hoge beginsnelheid en neerwaartse richting (met behulp van Helper.Speed.HIGH en Helper.Direction.DOWN)
     * Rotatiesnelheid: 2
     */
    public PowerUpLives (Coordinate2D initialLocation) {
        super("sprites/xWingPowerUp.png", initialLocation, new Size(Helper.Size.SMALL,Helper.Size.SMALL));
        setMotion(Helper.Speed.HIGH, Helper.Direction.DOWN);
        this.setRotationSpeed(2);
    }

    }


