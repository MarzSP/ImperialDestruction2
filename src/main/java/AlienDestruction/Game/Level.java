package AlienDestruction.Game;

public class Level {

    private int playerLevelNumber = 1;

    private int indexLevelNumber = getPlayerLevelNumber();

    public Level() {
    }

    public int getIndexLevelNumber() {
        return indexLevelNumber;
    }

    public void advanceLevel() {
        indexLevelNumber++;
        playerLevelNumber++;
    }

    public void setIndexLevelNumber(int indexLevelNumber) {
        this.indexLevelNumber = indexLevelNumber;
    }


    public int getPlayerLevelNumber() {
        return playerLevelNumber;
    }


    public void setPlayerLevelNumber(int playerLevelNumber) {
        this.playerLevelNumber = playerLevelNumber;

    }

    private static final int[][] LEVEL_DATA = {
            {4, 1, 1, 5, 1, 1, 2, 1, 1, 5, 1, 1},
            {3, 1, 3, 1, 3, 1, 5, 6, 2, 1, 2, 1},
            {4, 2, 1, 1, 1, 4, 6, 5, 3, 3, 3, 2},
            {5, 5, 6, 1, 6, 6, 5, 2, 5, 5, 6, 1},
            {3, 4, 4, 4, 4, 3}
    };

    public int[][] defineLevel() {
        return LEVEL_DATA;
    }

}