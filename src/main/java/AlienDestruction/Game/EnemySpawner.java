package AlienDestruction.Game;

import AlienDestruction.App;
import AlienDestruction.Entities.*;
import AlienDestruction.Helper;
import AlienDestruction.Scenes.GameScreen;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.impl.TextEntity;


import java.util.Random;

public class EnemySpawner extends EntitySpawner {

    /**
     * Player player is final:
     * Dit betekent dat de EntitySpawner-instantie altijd een geldige referentie naar de Player-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    private final Player player;
    /**
     * Level level is final:
     * Dit betekent dat de EntitySpawner-instantie altijd een geldige referentie naar de Level-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    private final Level level;

    /**
     * GameScreen gameScreen is final:
     * Dit betekent dat de EntitySpawner-instantie altijd een geldige referentie naar de gameScreen-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    private final GameScreen gameScreen;
    private int enemyTypeIndex = 0;

    int[][] levelData;
    int difficulty;

    /**
     * EnemySpawner(Player player, Level level, GameScreen gameScreen)`: Deze constructor initialiseert de spawner met een referentie naar de speler, de leveldata en het gamescherm.
     *  Stelt de initiële spawnvertraging in op 1200 milliseconden.
     * @param player Een referentie naar het spelerobject in het spel, omdat deze interageert met Player
     * @param level Een referentie naar het Level-object met de leveldata.
     * @param gameScreen Een referentie naar het GameScreen-object waar de entiteiten worden gespawned.
     */
    public EnemySpawner(Player player, Level level, GameScreen gameScreen, App app) {
        super(1200);
        this.level = level;
        this.player = player;
        this.gameScreen = gameScreen;

        levelData = level.defineLevel();
        difficulty = app.getDifficulty();
        for (int lvl = 0; lvl < levelData.length; lvl++) {
            for (int enemy = levelData[lvl].length - 1; enemy >= 0; enemy--) {
                levelData[lvl][enemy] += difficulty;
            }
        }
    }
/**
    protected void spawnEntities()`:
 *      Deze methode wordt periodiek aangeroepen om vijanden en obstakels te spawnen op basis van de huidige levelgegevens.
 *      Haalt het huidige levelnummer en de leveldata op uit het `level`-object.
 *      Itereert door de vijandtypen gedefinieerd voor het huidige level met behulp van `enemyTypeIndex`.
 *      Roept `spawnEnemyFromLevel(enemyType)` aan om de juiste vijand of obstakel te spawnen op basis van het vijandtype.
 *      Verhoogt `enemyTypeIndex` om naar het volgende vijandtype in de leveldata te wijzen
     *  Als alle vijandtypen in het huidige level zijn gespawned:
     *  Reset `enemyTypeIndex` naar 0.
     *  Verhoogt het huidige levelnummer en het spelerlevelnummer in het `level`-object.
     *  Werk de leveltekst bij die op het scherm wordt weergegeven.
     *  Als het einde van alle levels is bereikt, wordt het huidige levelnummer opnieuw ingesteld op 1.
 *      */
    @Override
    protected void spawnEntities() {

        int indexLevelNumber = level.getIndexLevelNumber();
        int[] currentLevel = levelData[indexLevelNumber - 1];
        spawnEnemyFromLevel(currentLevel[enemyTypeIndex]);
        enemyTypeIndex++;

        // level up
        if (enemyTypeIndex >= currentLevel.length) {
            enemyTypeIndex = 0;
            level.advanceLevel();
            updateLevelText();
            gameScreen.pause();
        }

        // continuously increase difficulty
        if (indexLevelNumber >= levelData.length) {
            level.setIndexLevelNumber(1);
            for (int lvl = 0; lvl < levelData.length; lvl++) {
                for (int enemy = levelData[lvl].length - 1; enemy >= 0; enemy--) {
                    levelData[lvl][enemy]++;
                }
            }
        }
    }

    /**
     *  Deze methode krijgt een vijandtype als invoer en spawnt de corresponderende vijand of obstakel.
     *  - Creëert een nieuw `GameEntity`-object op basis van het vijandtype.
     *  - Stelt de beginpositie van de vijand/obstakel in met behulp van een willekeurige X-coördinaat en een start-Y-coördinaat boven het scherm.
     *  - Roept de methode `spawn(e)` aan om de vijand/obstakel aan het spel toe te voegen.
     *  - `public void updateLevelText()`:
     * @param enemyType is de invoer van het type vijand.
     */
    public void spawnEnemyFromLevel(int enemyType){
        GameEntities e;
        int randomX = Helper.getRandomX(gameScreen.getWidth());

        switch (enemyType) {
            case 1:
                e = new EnemyOne(new Coordinate2D(randomX, -40), player, getSpeedIncrease());
                spawn(e);
                break;
            case 2: e = new EnemyTwo(new Coordinate2D(randomX, -40), player, getSpeedIncrease());
                spawn(e);
                break;
            case 3: e = new EnemyThree(new Coordinate2D(randomX, -40), player, getSpeedIncrease());
                spawn(e);
                break;
            case 4: e = new EnemyFour(new Coordinate2D(randomX, -40), player, getSpeedIncrease());
                spawn(e);
                break;
            case 5: e = new ObstacleOne(new Coordinate2D(randomX, -40), player);
                spawn(e);
                break;
            case 6: e = new ObstacleTwo(new Coordinate2D(randomX, -40), player);
                spawn(e);
                break;
            default:
                break;
        }
    }

    /**
     * Deze methode updatet de leveltekst die op het scherm wordt weergegeven om het huidige spelerlevelnummer weer te geven.
     */
    public void updateLevelText(){
        TextEntity levelText;
        gameScreen.getLevelText().setText("Level: " + level.getPlayerLevelNumber());
    }

    /**
     * Deze methode berekent een snelheidstoename op basis van het huidige spelerlevelnummer.
     * @return Retourneert een waarde die gelijk is aan het spelerlevelnummer vermenigvuldigd met 0.1.
     */
    public double getSpeedIncrease() {
        double increase = level.getPlayerLevelNumber() * 0.1;
        return increase;
    }
}

