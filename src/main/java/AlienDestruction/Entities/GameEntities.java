package AlienDestruction.Entities;

import AlienDestruction.Entities.Enemies.EnemyFour;
import AlienDestruction.Helper;
import AlienDestruction.Weapons.LaserBeam;
import AlienDestruction.Weapons.WeaponType;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import com.github.hanyaeger.api.entities.*;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;


import java.util.List;


/**
 * Class GameEntities:
 * Extends:
 *     DynamicSpriteEntity: Erft van de DynamicSpriteEntity klasse die functionaliteit biedt voor het hanteren van een bewegende sprite entity met een afbeelding.
 *     SceneBorderTouchingWatcher: Implementeert de SceneBorderTouchingWatcher interface om te detecteren wanneer het object de rand van het scherm raakt.
 *     Newtonian: Implementeert de Newtonian interface om physics simulatie toe te passen (zwaartekracht).
 *     Collided: Implementeert de Collided interface om te detecteren wanneer het object met andere objecten botst. (Zoals bijv Speler met Vijand)
 *     Collider: Implementeert de Collider interface voor detectie van botsingen
 *     Rotatable: Implementeert de Rotatable interface om rotatie van sprites mogelijk te maken
 *     SceneBorderCrossingWatcher: Implementeert de SceneBorderCrossingWatcher interface om te detecteren wanneer het objectbuiten de schermranden komt.
 */
public class GameEntities extends DynamicSpriteEntity implements TimerContainer, SceneBorderTouchingWatcher, Newtonian, Collided, Collider, Rotatable, SceneBorderCrossingWatcher {
    private int points;
    private int penaltyPoints;
    private int hitPoints;
    protected boolean allowedToFire;
    private final Size size;
    /**
     * Player player is final:
     * Dit betekent dat de EnemyFour-instantie altijd een geldige referentie naar de Player-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    protected final Player player;
    private final Timer fireTimer;

    /**
     * Constructor:
     * Initialiseert het object met een afbeelding, locatie, grootte en referentie naar de speler. Schakelt zwaartekracht en wrijving uit.
     * @param resource De afbeelding van de GameEntity
     * @param initialLocation De initiele locatie van de GameEntity
     * @param size Het formaat van de sprite
     * @param player de referentie naar Player zodat deze kan interageren met Player
     */
    protected GameEntities(String resource, Coordinate2D initialLocation, Size size, Player player) {
        super(resource, initialLocation, size);
        this.size = size;
        this.player = player;

        setGravityConstant(0.000);
        setFrictionConstant(0.00);

        fireTimer = new Timer(1000) {
            @Override
            public void onAnimationUpdate(long timestamp) {
                if (allowedToFire) {
                    shoot();
                }
            }
        };
    }

    /**
     * Configureert en start timers voor het object.
     * Deze methode voegt de vuur-timer toe aan het object.
     * De vuur-timer regelt het interval waarin het object schiet.
     */
    @Override
    public void setupTimers() {
        addTimer(fireTimer);
    }

    /**
     * Schiet een laserstraal vanaf de spelerpositie.
     * De laserstraal wordt afgevuurd vanaf het bovenste midden van het spelerobject.
     */
    public void shoot() {
        boolean isSD = false;
        double x = getLocationInScene().getX() + (this.size.width() / 2);
        double y = getLocationInScene().getY();
        if (this instanceof EnemyFour) {
            isSD = true;
            player.getGun().shoot(new LaserBeam(false, new Coordinate2D(x - 40, y), this, isSD));
            player.getGun().shoot(new LaserBeam(false, new Coordinate2D(x + 30, y), this, isSD));
        } else {
            player.getGun().shoot(new LaserBeam(false, new Coordinate2D(x, y), this, isSD));
        }
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getPenaltyPoints() {
        return penaltyPoints;
    }
    public void setPenaltyPoints(int penaltyPoints) {
        this.penaltyPoints = penaltyPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    /**
     * Wordt aangeroepen wanneer het object een grens van de scène raakt.
     *
     * @param sceneBorder de grens van de scène waarmee het object in aanraking komt
     */
    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
    }

    /**
     * Detecteert botsingen en handelt deze af.
     *     Botsing met WeaponType vermindert hitPoints en controleert of vernietiging nodig is.
     *     Botsing met de speler, leidt tot verwijdering van dit object.
     *     Anders: stuurt het object in een andere richting.
     * @param collidingObject a {@link List} of all instances of {@link Collider} this {@link Collided} has collided
     *                         with, during the last Game World Update.
     */
    @Override
    public void onCollision(List<Collider> collidingObject) {
        for (Collider collider : collidingObject){
            if (collider instanceof WeaponType weaponType){

                // Dont get hit by your own laser beams
                if (weaponType.isOwnedBy(this)) {
                    break;
                }

                addDamage(weaponType.getDamagePoints());
                checkIfDestroyed();
                weaponType.remove(); // remove Laser / Weapon
                break;
            } else if (collider instanceof Player){ // TODO Checken ??
                remove();
                break;
            } else {
                this.setMotion(this.getSpeed(), this.calculateCourse((int)getDirection()));
            }
        }
    }

    /**
     * calculateCourse maakt een nieuwe koers gebaseerd op de oude richting
     * @param oldDirection de richting waar de Entity vandaan kwam, zodat deze kan veranderen naar een nieuwe
     * @return newDirection de nieuwe richting waar de Entity heen gaat
     */
    public int calculateCourse(int oldDirection){
        int newDirection = 0;
        if (oldDirection < 359 && oldDirection > 271) {
            newDirection = 90 - (360-oldDirection);
        } else {
            newDirection = 360 -(360-oldDirection);
        }
        return newDirection;
    }

    /**
     * addDamage(double damage): Vermindert de hitPoints met de opgegeven schade.
     * @param damage de hoeveelheid hitPoints dat een Entity geeft
     */
    public void addDamage(double damage){
        this.hitPoints -= damage;
    }

    /**
     * checkIfDestroyed(): Controleert of het object vernietigd moet worden (hitpoints <= 0) en voegt score toe aan de speler en verwijdert zichzelf.
     */
    public void checkIfDestroyed(){
        if (this.hitPoints <= 0) {
            player.increaseScore(points);
            this.remove();                      // remove Enemy / entity
        }
    }

    /**
     * getCourse(double xPos): Berekent een nieuwe koers gebaseerd op de horizontale positie
     * @param xPos de X positie van de koers van deze Entity
     * @return de nieuwe koers van deze Entity in graden.
     * Als de xPos kleiner is dan 600, wordt een willekeurige koers tussen 10 en 40 graden gekozen.
     * Als de xPos groter is dan 500, wordt een willekeurige koers tussen 310 en 340 graden gekozen.
     * In andere gevallen wordt 0 graden (recht omhoog) geretourneerd.
     */
    public int getCourse(double xPos){
        if(xPos < 600) {
            return Helper.getRandomInt(10, 40);
        } else if (xPos >500) {
            return Helper.getRandomInt(310, 340);
        }
        return 0;
    }

    /**
     * notifyBoundaryCrossing(SceneBorder sceneBorder):
     * Deze methode wordt aangeroepen wanneer de vijand de grens van het scherm verlaat (sceneBorder).
     */
    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
    }

    public void bounceOffT(WeaponType collider){
        double randomBounce = Helper.getRandomDouble(-8.0, 8.0);
        switch (getHitGrid(collider)) {
            case 1:
                setNewColliderDirection(collider, 90 + randomBounce, 270 + randomBounce);
                break;
            case 2:
                setNewColliderDirection(collider, 90 + randomBounce, 90 + randomBounce);
                break;
            case 3:
                setNewColliderDirection(collider, 90 + randomBounce, 90+ randomBounce);
                break;
            case 4:
                setNewColliderDirection(collider, 90 + randomBounce, 270 + randomBounce);
                break;
            default:
                break;
        }
    }

    public void setNewColliderDirection(WeaponType collider, double rotate, double direction){
        (collider).setRotate(rotate);
        (collider).setDirection(direction);
    }

    public int getHitGrid(WeaponType collider){
        double obstWidth = this.getWidth();
        double obstHeight = this.getHeight();
        double obstacleX = getLocationInScene().getX();
        double obstacleY = getLocationInScene().getY();
        double laserX = collider.getAnchorLocation().getX();
        double laserY = collider.getAnchorLocation().getY();
        boolean isLeftSide = laserX < obstacleX + (obstWidth / 2);
        boolean isTopSide = laserY < obstacleY + (obstHeight / 2);

        if (isLeftSide && isTopSide) {
            return  1;
        } else if (!isLeftSide && isTopSide){
            return  2;
        } else if (!isLeftSide && !isTopSide){
            return  3;
        } else if (isLeftSide && !isTopSide){
            return  4;
        }
        return 0;
    }

}
