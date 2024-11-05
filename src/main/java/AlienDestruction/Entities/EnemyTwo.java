package AlienDestruction.Entities;

import AlienDestruction.App;
import AlienDestruction.Helper;
import AlienDestruction.Scenes.GameScreen;
import AlienDestruction.Weapons.LaserBeam;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.scenes.SceneBorder;

import java.util.List;

/**
 * EnemyTwo:erft van de GameEntities klasse, dit is een vijand die kan schieten.
 *  EnemyTwo erft: zie constructor
 */

public class EnemyTwo extends GameEntities{
    /**
     * Constructor:
     * EnemyTwo(Coordinate2D location, Player player, double speedIncrease):
     *   Initialiseert een nieuw object van EnemyTwo
     *   Slaat een referentie op naar (player), welke EnemyTwo invloed op kan hebben.
     *   Stelt de afbeelding in op "sprites/tieFighterV1.png".
     *   Stelt de grootte in op Helper.Size.MEDIUM.
     *   Stelt de beweging in op Helper.Speed.MEDIUM plus de meegegeven speedIncrease in de neerwaartse richting (Helper.Direction.DOWN).
     *   Stelt de punten in op 80 (points).
     *   Stelt de aftrekpunten in op 40 (penaltyPoints).
     *   Stelt de levens in op 2 (hitPoints).
     *   Stelt allowedToFire in op true, wat aangeeft dat deze vijand kan schieten.
     */
    public EnemyTwo(Coordinate2D location, Player player, double speedIncrease) {
        super("sprites/tieIntercepterV1.png", location, new Size(Helper.Size.MEDIUM,Helper.Size.MEDIUM), player);
        setMotion(Helper.Speed.MEDIUM + speedIncrease, getCourse(this.getAnchorLocation().getX()));
        setPoints(80);
        setPenaltyPoints(40);
        setHitPoints(2);

        allowedToFire = true;
    }

    /**
     * notifyBoundaryCrossing(SceneBorder sceneBorder):
     * Deze methode wordt aangeroepen wanneer de vijand de grens van het scherm verlaat (sceneBorder).
     * De speler verliest punten gelijk aan penaltyPoints.
     * De vijand wordt verwijderd (this.remove()).
     */
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        player.setScore(player.getScore() - this.getPenaltyPoints());
        this.remove();
    }


}
