package AlienDestruction.Game;

import AlienDestruction.Entities.PowerUps.PowerUpLaser;
import AlienDestruction.Entities.PowerUps.PowerUpLives;
import AlienDestruction.Entities.PowerUps.PowerUps;
import AlienDestruction.Helper;
import AlienDestruction.Scenes.GameScreen;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;

/**
 * De `PowerUpSpawner`-klasse is een subklasse van `EntitySpawner` en is verantwoordelijk voor het spawnen van power-ups in het spel.
 */
public class PowerUpSpawner extends EntitySpawner {

    /**
     * Level level is final:
     * Dit betekent dat de PowerUpSpawner-instantie altijd een geldige referentie naar de level-instantie heeft.
     * */
    private final Level level;

    /**
     * GameScreen gameScreen is final:
     * Dit betekent dat de PowerUpSpawner-instantie altijd een geldige referentie naar de gameScreen-instantie heeft.
     * */
    private final GameScreen gameScreen;

    /**
     * Constructor: - `PowerUpSpawner(Level level, GameScreen gameScreen)`
     *  Roept de constructor van de superklasse `EntitySpawner` aan en geeft een willekeurige waarde tussen 5000 en 15000 milliseconden door als spawnvertraging.
     *  Slaat de meegegeven `level` en `gameScreen` referenties op in object velden.
     * @param level  Een referentie naar het `Level`-object dat informatie over het huidige level bevat.
     * @param gameScreen Een referentie naar het `GameScreen`-object waar de power-ups worden gespawned.
     */
    public PowerUpSpawner(Level level, GameScreen gameScreen) {
        super(Helper.getRandomInt(5000, 15000));
        System.out.println(level.getPlayerLevelNumber());
        this.level = level;
        this.gameScreen = gameScreen;
    }

    /**
     * Overriding methode van de superklasse. Deze methode roept `spawnPowerUp()` aan om een power-up te spawnen.
     */
    @Override
    protected void spawnEntities() {
        spawnPowerUp();
    }

    /**
     * SpawnPowerUp: spawnt een power-up op basis van een willekeurig getal.
     * Genereert een willekeurige X-coördinaat binnen de breedte van het scherm.
     * Genereert een willekeurig getal tussen 1 en 3 om het type power-up te bepalen.
     * Gebaseerd op het willekeurige getal:
     *           - Creëert een nieuw `PowerUpLives`-object (extra levens) of `PowerUpLaser`-object (laser power-up) met een startpositie boven het scherm.
     *           - Roept de `spawn(e)`-methode aan om de power-up toe te voegen aan het spel.
     */
    public void spawnPowerUp(){
        PowerUps e;
        int randomX = Helper.getRandomX(gameScreen.getWidth());
        int randomP = Helper.getRandomInt(1,3);

        switch (randomP) {
            case 1:
                e = new PowerUpLives(new Coordinate2D(randomX, -40));
                spawn(e);
                break;
            case 2:
                e = new PowerUpLaser(new Coordinate2D(randomX, -40));
                spawn(e);
                break;
            default:
                break;
        }
    }
}
