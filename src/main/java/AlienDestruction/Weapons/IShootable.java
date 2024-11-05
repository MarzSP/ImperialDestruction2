package AlienDestruction.Weapons;

/**
 * De IShootable interface definieert een blauwdruk voor objecten in de game die kunnen schieten met verschillende wapens.
 */
public interface IShootable {

    /**
     * Deze methode simuleert het schieten met een wapen.
     * @param weaponType Een parameter van het type WeaponType die specificeert welk wapen wordt gebruikt om te schieten
     */
    void shoot(WeaponType weaponType);
}
