package AlienDestruction.Game;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.entities.FiniteAnimation;
import com.github.hanyaeger.api.entities.impl.SpriteEntity;

public class Explosion extends SpriteEntity {
    private FiniteAnimation explosionAnimation;
    private Coordinate2D location = getLocationInScene();
    private int currentFrame = 0;

    /**
     * Constructor Explosion:
     * @param explosionAnimation : spritesheet voor ded animatie.
     */
    public Explosion(FiniteAnimation explosionAnimation) {
        super("sprites/Explosion.png", new Coordinate2D(), new Size(20, 20));
        this.explosionAnimation = explosionAnimation;
        playAnimation();
    }

    private void playAnimation() {
        new Timer(30) {

            @Override
            public void onAnimationUpdate(long timestamp) {
                int currentFrame = 0;
                setCurrentFrame(currentFrame);
            }
        };
        explosionAnimation.cycleTimeInMs();
    }

    public void run() {
        currentFrame++;
        if (currentFrame >= 12) {
            remove();
            this.remove();
        }
    }
    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }
}
