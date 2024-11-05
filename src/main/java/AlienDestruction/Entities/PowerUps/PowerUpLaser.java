package AlienDestruction.Entities.PowerUps;

import AlienDestruction.Helper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.ContinuousRotatable;

/**De klasse PowerUpLaser is een power-up in de game die de laser van de speler upgradet.
 * Deze klasse erft van de PowerUps klasse en implementeert de ContinuousRotatable interface.
 * De implementatie van deze klasse bevind zich in Player in public void activateLaserPowerUp():
 * Wanneer de Spatiebalk ingedrukt wordt, kan de speler 10 seconden lang extra snel lasers afvuren.
 */
public class PowerUpLaser extends PowerUps implements ContinuousRotatable {

    /**
     * Constructor:
     * @param initialLocation : De beginpositie van de power-up op het scherm (als Coordinate2D object)
     * Sprite afbeelding: "sprites/LaserPowerUp.png" een visuele representatie van extra snelle laser.
     * Grootte: Size(40,40)
     * Hoge beginsnelheid en neerwaartse richting (met behulp van Helper.Speed.HIGH en Helper.Direction.DOWN)
     * Rotatiesnelheid: 2
     */
    public PowerUpLaser(Coordinate2D initialLocation) {
        super("sprites/LaserPowerUp.png", initialLocation,new Size(Helper.Size.SMALL, Helper.Size.SMALL));
        setMotion(Helper.Speed.HIGH, Helper.Direction.DOWN);
        this.setRotationSpeed(2);
    }
}
