package AlienDestruction.Game;

import AlienDestruction.App;
import AlienDestruction.Entities.*;
import AlienDestruction.Entities.Enemies.*;
import AlienDestruction.Entities.Obstacles.*;
import AlienDestruction.Helper;
import AlienDestruction.Scenes.GameScreen;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.impl.TextEntity;

public class EnemySpawner extends EntitySpawner {

    private static final int SPAWN_INTERVAL = 1200;
    private static final int INITIAL_Y_POSITION = -40;

    private final Player player;
    private final Level level;
    private final GameScreen gameScreen;
    private int enemyTypeIndex = 0;
    private int[][] levelData;
    int difficulty;

    public EnemySpawner(Player player, Level level, GameScreen gameScreen, App app) {
        super(SPAWN_INTERVAL);
        this.player = player;
        this.level = level;
        this.gameScreen = gameScreen;
        initializeLevelData(app.getDifficulty());
    }

    private void initializeLevelData(int difficulty) {
        this.difficulty = difficulty;
        levelData = level.defineLevel();
        for (int[] levelRow : levelData) {
            for (int i = 0; i < levelRow.length; i++) {
                levelRow[i] += difficulty;
            }
        }
    }

    @Override
    protected void spawnEntities() {
        int indexLevelNumber = level.getIndexLevelNumber();
        int[] currentLevel = levelData[indexLevelNumber - 1];
        spawnEnemyFromLevel(currentLevel[enemyTypeIndex]);
        enemyTypeIndex++;

        if (enemyTypeIndex >= currentLevel.length) {
            levelUp();
        }

        if (indexLevelNumber >= levelData.length) {
            resetAndIncreaseDifficulty();
        }
    }

    private void levelUp() {
        enemyTypeIndex = 0;
        level.advanceLevel();
        updateLevelText();
        gameScreen.pause();
    }

    private void resetAndIncreaseDifficulty() {
        level.setIndexLevelNumber(1);
        for (int[] levelRow : levelData) {
            for (int i = 0; i < levelRow.length; i++) {
                levelRow[i]++;
            }
        }
    }

    private void spawnEnemyFromLevel(int enemyType) {
        GameEntities enemy = createEnemy(enemyType);
        if (enemy != null) {
            spawn(enemy);
        }
    }

    private GameEntities createEnemy(int enemyType) {
        int randomX = Helper.getRandomX(gameScreen.getWidth());
        Coordinate2D location = new Coordinate2D(randomX, INITIAL_Y_POSITION);

        return switch (enemyType) {
            case 1 -> new EnemyOne(location, player, getSpeedIncrease());
            case 2 -> new EnemyTwo(location, player, getSpeedIncrease());
            case 3 -> new EnemyThree(location, player, getSpeedIncrease());
            case 4 -> new EnemyFour(location, player, getSpeedIncrease());
            case 5 -> new ObstacleOne(location, player);
            case 6 -> new ObstacleTwo(location, player);
            default -> null;
        };
    }

    private void updateLevelText() {
        gameScreen.getLevelText().setText("Level: " + level.getPlayerLevelNumber());
    }

    private double getSpeedIncrease() {
        return level.getPlayerLevelNumber() * 0.1;
    }
}
