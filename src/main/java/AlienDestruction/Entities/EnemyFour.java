package AlienDestruction.Entities;


import AlienDestruction.Helper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.SceneBorder;
/**
 * EnemyFour:erft van de GameEntities klasse, dit is een vijand die kan schieten
 * EnemyFour erft: zie constructor
 **/

public class EnemyFour extends GameEntities{

    /**
     * Constructor:
     * EnemyFour(Coordinate2D location, Player player, double speedIncrease):
     *   Initialiseert een nieuw object van EnemyFour
     *   Slaat een referentie op naar (player), welke EnemyFour invloed op kan hebben.
     *   Stelt de afbeelding in op "sprites/tieFighterV1.png".
     *   Stelt de grootte in op Helper.Size.MEDIUM.
     *   Stelt de beweging in op Helper.Speed.MEDIUM plus de meegegeven speedIncrease in de neerwaartse richting (Helper.Direction.DOWN).
     *   Stelt de punten in op 200 (points).
     *   Stelt de aftrekpunten in op 100 (penaltyPoints).
     *   Stelt de levens in op 8 (hitPoints).
     *   Stelt allowedToFire in op true, wat aangeeft dat deze vijand kan schieten.
     */

    public EnemyFour(Coordinate2D location, Player player, double speedIncrease) {
        super("sprites/starDestroyerV1.png", location, new Size(Helper.Size.HUGE,Helper.Size.HUGE * 2), player);

        setMotion(Helper.Speed.LOW + speedIncrease, Helper.Direction.DOWN);
        setPoints(200);
        setPenaltyPoints(100);
        setHitPoints(8);

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
