package AlienDestruction.Weapons;

import com.github.hanyaeger.api.entities.EntitySpawner;

/**
 * Class LaserGun  vertegenwoordigt een lasergeweer en erft van een class en een interface:
 * EntitySpawner: Deze abstracte class is er voor om: automatisch entiteiten te spawnen (creëren).
 * IShootable: Deze interface definieert een blauwdruk voor objecten die kunnen schieten.
 */
public class LaserGun extends EntitySpawner implements IShootable{

    /**
     * Constructor:
     * @param intervalInMs Initialiseert het schietinterval van het lasergeweer in milliseconden
     */
    public LaserGun(long intervalInMs) {
        super(intervalInMs);
    }

    /**
     * public void shoot(WeaponType weaponType):
     *     Deze methode implementeert de shoot methode van de IShootable interface.
     * @param weaponType :
     * Stelt de snelheid van de weaponType in op 25.
     * Stelt de richting van de weaponType  in op 180 graden.
     */
    @Override
    public void shoot(WeaponType weaponType) {
        weaponType.setSpeed(25);
        this.spawn(weaponType);
    }

    /**
     * protected void spawnEntities() (overridden):
     * Deze abstracte methode is geërfd van EntitySpawner en moet worden geïmplementeerd voor de logica voor het spawnen van entiteiten.
     */
    @Override
    protected void spawnEntities() {

    }
}
