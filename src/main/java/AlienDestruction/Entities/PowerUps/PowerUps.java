package AlienDestruction.Entities.PowerUps;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/** Deze klasse is een blauwdruk voor power-ups (PowerUpLives, PowerUpLaser).
 * Extends DynamicSpriteEntity: klasse die sprites met posities en afmetingen in de game beheert.
 * Implements Collider:  implementeert de Collider welke aangeeft dat power-ups kunnen botsen met andere objecten in de game en wat er dan gebeurd.
 */
public class PowerUps extends DynamicSpriteEntity implements Collider {

    /**
     * Deze constructor is protected zodat er alleen toegang is vanuit de klasse zelf, en de subklassen, om de interne implementatiecode:
     * te verbergen van alles wat buiten scope is.
     * @param resource :De locatie van het sprite-bestand (afbeelding) dat voor de power-up wordt gebruikt.
     * @param initialLocation :De beginpositie van de power-up op het scherm (als Coordinate2D object).
     * @param size :De grootte van de power-up sprite (als Size object).
     */
    protected PowerUps(String resource, Coordinate2D initialLocation, Size size) {
        super(resource, initialLocation, size);
    }
}
