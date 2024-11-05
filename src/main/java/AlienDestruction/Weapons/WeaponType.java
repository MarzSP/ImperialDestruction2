package AlienDestruction.Weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

import java.util.List;

/**
 * Deze abstracte class WeaponType is de basis voor verschillende soorten wapens in de game.
*/
public abstract class WeaponType extends DynamicSpriteEntity implements Collider, Collided, SceneBorderCrossingWatcher {

    private double damagePoints;

    private final DynamicSpriteEntity owner;

    protected WeaponType(String resource, Coordinate2D initialLocation, Size size, DynamicSpriteEntity owner) {
        super(resource, initialLocation, size );
        this.owner = owner;
    }

    public double getDamagePoints() {
        return damagePoints;
    }


    public void setDamagePoints(double damagePoints) {
        this.damagePoints = damagePoints;
    }

    @Override
    public void onCollision(List<Collider> collidingObject) {
    }
    public boolean isOwnedBy(DynamicSpriteEntity other) {
        return owner == other;
    }

}
