package AlienDestruction.Entities;


import AlienDestruction.Helper;
import AlienDestruction.Weapons.LaserBeam;
import AlienDestruction.Weapons.WeaponType;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.scenes.SceneBorder;

import java.util.List;

/**
 * De ObstacleOne klasse vertegenwoordigt een obstakel van type 1 in een game.
 * Het erft van de GameEntities klasse die de basis functionaliteit voor game-elementen verzorgt.
 */
public class ObstacleOne extends GameEntities {
    /**
     *  Player player is final:
     *  Dit betekent dat de ObstacleOne-instantie altijd een geldige referentie naar de Player-instantie heeft.
     *  Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     *  */
    private final Player player;

    /**
     *ObstacleOne(Coordinate2D location, Player player): Initialiseert de ObstacleOne met een afbeelding, locatie, grootte en referentie naar de speler.
     * Het obstakel krijgt een lage beginsnelheid naar beneden en een willekeurige rotatiesnelheid.
     * @param location De locatie van ObstacleOne
     * @param player Een referentie naar Player waar het obstakel interactie mee heeft
     */
    public ObstacleOne(Coordinate2D location, Player player) {
        super("sprites/asteroidSquareV1.png", location, new Size(Helper.Size.LARGE,Helper.Size.LARGE), player);
        this.player = player;

        setMotion(Helper.Speed.LOW, Helper.Direction.DOWN);
        this.setRotationSpeed(Helper.getRandomDouble(-0.6, 0.6));
    }

    /**
     * onCollision(List<Collider> collidingObject) (Override): Deze methode wordt aangeroepen wanneer de obstakel met een ander object collideert.
     * De methode controlleert of het collidende object een laserstraal (LaserBeam) is. Zo ja, dan roept de methode bounceOff(WeaponType) aan om van richting te veranderen.
     * @param collidingObject a {@link List} of all instances of {@link Collider} with, during the last Game World Update.
     */
    @Override
    public void onCollision(List<Collider> collidingObject) {
        for (Collider collider : collidingObject){
            if (collider instanceof LaserBeam){
                bounceOffT((WeaponType) collider);
            }
        }
    }

/**
 * Deze methode implementeert de `notifyBoundaryCrossing`-methode van de `GameEntity`-klasse.
 * Wordt aangeroepen wanneer de laserstraal de grens van het scherm overschrijdt.
 **/
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }

}

