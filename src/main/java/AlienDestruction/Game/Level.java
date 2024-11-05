package AlienDestruction.Game;

/**
 * Representeert een level in het spel
 */
public class Level {

    private int playerLevelNumber = 1;

    private int indexLevelNumber = getPlayerLevelNumber();

    /**
     * Constructor: De standaard constructor, initialisatie vindt plaats in de velden.
     */
    public Level() {
    }

    /**
     * getIndexLevelNumber
     * @return Geeft de waarde van `indexLevelNumber` terug.
     */
    public int getIndexLevelNumber() {
        return indexLevelNumber;
    }

    public void advanceLevel() {
        indexLevelNumber++;
        playerLevelNumber++;
    }

    /**
     * setIndexLevelNumber:
     * @param indexLevelNumber Stelt de waarde van `indexLevelNumber` in.
     */
    public void setIndexLevelNumber(int indexLevelNumber) {
        this.indexLevelNumber = indexLevelNumber;
    }

    /**
     * getPlayerLevelNumber()
     * @return Geeft de waarde van `playerLevelNumber` terug.
     */
    public int getPlayerLevelNumber() {
        return playerLevelNumber;
    }

    /**
     * setPlayerLevelNumber(int playerLevelNumber)`:
     * @param playerLevelNumber Stelt de waarde van `playerLevelNumber` in.
     */
    public void setPlayerLevelNumber(int playerLevelNumber) {
        this.playerLevelNumber = playerLevelNumber;

    }

    /** public int[][] defineLevel()`: Retourneert een tweedimensionale array die de leveldata van het huidige level voorstelt.
     * De data bestaat uit integers die de types van vijanden en obstakels definiëren.
     * @return Retourneert een tweedimensionale array die de leveldata van het huidige level voorstelt.
     */
    public int[][] defineLevel() {
        return new int[][] {
                {4, 1, 1, 5, 1, 1, 2, 1, 1, 5, 1, 1},
                {3, 1, 3, 1, 3, 1, 5, 6, 2, 1, 2, 1},
                {4, 2, 1, 1, 1, 4, 6, 5, 3, 3, 3, 2},
                {5, 5, 6, 1, 6, 6, 5, 2, 5, 5, 6, 1},
                {3, 4, 4, 4, 4, 3}
        };
    }


}