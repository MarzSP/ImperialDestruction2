package AlienDestruction.Entities.Enemies;


import AlienDestruction.Entities.GameEntities;
import AlienDestruction.Entities.Player;
import AlienDestruction.Helper;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.SceneBorder;


/**
 * EnemyOne:erft van de GameEntities klasse, dit is een vijand die niet kan schieten.
 * EnemyOne erft: zie constructor
 *
 */
public class
EnemyOne extends GameEntities {
    /**
     *  Player player is final:
     *  Dit betekent dat de EnemyOne-instantie altijd een geldige referentie naar de Player-instantie heeft.
     *  Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     *  */
    private final Player player;

/**
 * Constructor:
 * EnemyOne(Coordinate2D location, Player player, double speedIncrease):
 *     Initialiseert een nieuw object van EnemyOne
 *     Slaat een referentie op naar (player), welke EnemyOne invloed op kan hebben.
 *     Stelt de afbeelding in op "sprites/tieFighterV1.png".
 *     Stelt de grootte in op Helper.Size.MEDIUM.
 *     Stelt de beweging in op Helper.Speed.MEDIUM plus de meegegeven speedIncrease in de neerwaartse richting (Helper.Direction.DOWN).
 *     Stelt de punten in op 50 (points).
 *     Stelt de aftrekpunten in op 25 (penaltyPoints).
 *     Stelt de levens in op 1 (hitPoints).
 *     Stelt canShoot in op false, wat aangeeft dat deze vijand niet kan schieten.
 */
    public EnemyOne(Coordinate2D location, Player player, double speedIncrease) {

        super("sprites/tieFighterV1.png", location, new Size(Helper.Size.MEDIUM,Helper.Size.MEDIUM), player);
        this.player = player;

        setMotion((Helper.Speed.MEDIUM + speedIncrease), Helper.Direction.DOWN);
        setPoints(50);
        setPenaltyPoints(25);
        setHitPoints(1);
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
