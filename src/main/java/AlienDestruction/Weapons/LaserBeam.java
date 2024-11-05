package AlienDestruction.Weapons;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;

import java.util.List;

public class LaserBeam extends WeaponType {

    public LaserBeam(boolean isUp, Coordinate2D initialLocation, DynamicSpriteEntity owner, boolean isSD) {
        super(
                isUp ? "sprites/laserup.png" : "sprites/laserdown.png",
                initialLocation,
                new Size(8,24),
                owner
        );
        if (isUp) {
            setDirection(180);
        }
        setDamagePoints(1);
    }


    @Override
    public void onCollision(List<Collider> collidingObject) {
        super.onCollision(collidingObject);
    }


    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        this.remove();
    }

}


