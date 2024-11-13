package AlienDestruction.Entities.Obstacles;


import AlienDestruction.Entities.Obstacles.Obstacle;
import AlienDestruction.Entities.Player;
import AlienDestruction.Helper;
import AlienDestruction.Weapons.WeaponType;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.SceneBorder;

/**
 * De ObstacleOne klasse vertegenwoordigt een obstakel van type 2 in een game.
 *  * Het erft van de GameEntities klasse die de basis functionaliteit voor game-elementen verzorgt.
 */
public class ObstacleTwo extends Obstacle {

    /**
     * ObstacleTwo(Coordinate2D location, Player player): Initialiseert de ObstacleTwo met een afbeelding, locatie, grootte en referentie naar de speler.
    * Het obstakel krijgt een lage beginsnelheid naar beneden en een willekeurige rotatiesnelheid.
    * @param location De locatie van ObstacleOne
    * @param player Een referentie naar Player waar het obstakel interactie mee heeft
      */
    public ObstacleTwo(Coordinate2D location, Player player) {
        super("sprites/asteroidRectangleV1.png", location, new Size(Helper.Size.HUGE,Helper.Size.LARGE), player);
        /**
         *  Player player is final:
         *  Dit betekent dat de ObstacleTwo-instantie altijd een geldige referentie naar de Player-instantie heeft.
         *  Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
         *  */
        setMotion(Helper.Speed.LOW, getCourse(this.getAnchorLocation().getX()));
        this.setRotationSpeed(Helper.getRandomDouble(-0.6, 0.6));
    }

/**
 * setNewColliderDirection(WeaponType collider, double rotate, double direction): Wijzigt de rotatie en richting van de laserstraal zodat deze wegkaatst.
 * @param collider is een referentie naar het object dat met de laserstraal botst.
 * @param rotate De nieuwe rotatiehoek in graden van de laserstraal
 * @param direction De nieuwe bewegingsrichting in graden van de laserstraal
 **/
    public void setNewColliderDirection(WeaponType collider, double rotate, double direction){
        (collider).setRotate(rotate);
        (collider).setDirection(direction);
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

