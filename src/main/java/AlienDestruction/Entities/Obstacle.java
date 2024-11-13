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
 * The `Obstacle` class represents a common base for all obstacles in the game.
 */
public abstract class Obstacle extends GameEntities {
    protected final Player player;

    public Obstacle(String sprite, Coordinate2D location, Size size, Player player) {
        super(sprite, location, size, player);
        this.player = player;
        setMotion(Helper.Speed.LOW, Helper.Direction.DOWN);
        this.setRotationSpeed(Helper.getRandomDouble(-0.6, 0.6));
    }

    @Override
    public void onCollision(List<Collider> collidingObject) {
        for (Collider collider : collidingObject) {
            if (collider instanceof LaserBeam) {
                bounceOffT((WeaponType) collider);
            }
        }
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        remove();
    }
}