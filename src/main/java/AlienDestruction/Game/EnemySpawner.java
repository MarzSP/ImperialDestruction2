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

    private final Player player;

    private final Level level;

    private final GameScreen gameScreen;
    private int enemyTypeIndex = 0;

    int[][] levelData;
    int difficulty;


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
        for (int[] level : levelData) {
            for (int enemy = level.length - 1; enemy >= 0; enemy--) {
                level[enemy]++;
            }
        }
    }

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


    public void updateLevelText(){
        TextEntity levelText;
        gameScreen.getLevelText().setText("Level: " + level.getPlayerLevelNumber());
    }

    public double getSpeedIncrease() {
        double increase = level.getPlayerLevelNumber() * 0.1;
        return increase;
    }
}

