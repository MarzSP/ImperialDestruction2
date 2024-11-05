package AlienDestruction.Game;

import AlienDestruction.Entities.Player;

/**De klasse Highscore houdt bij van Player: de hoogste score die de speler heeft behaald (highscore).
 * Daarnaast kan de klasse de huidige score van de speler (currentScore) bijhouden.
 *
 */
public class Highscore {
    private Player player;
    private int highscore = 0;
    private int currentScore;

    /**
     * Constructor Highscore initialiseert een highscore object, en krijgt Player als argument.
     * @param player
     */
    public Highscore(Player player){
        this.player = player;
    }

    /**
     * De methode updateCurrentScore: is een attribuut van de class met de huidige score van de speler.
     * Player class heeft een getScore() methode die de huidige score van de speler retourneert.
     */
    public void updateCurrentScore(){
       currentScore = player.getScore();
        }

    /**
     * Deze methode controleert of de huidige score van de speler hoger is dan de opgeslagen hoogste score (highscore).
     * Het roept eerst updateCurrentScore() aan om de currentScore te actualiseren.
     *     Vervolgens controleert het of currentScore groter is dan highscore.
     *         Indien waar, wordt de highscore geüpdatet naar de currentScore.
     *         Er verschijnt ook een bericht in de console ("New Highscore!").
     */
    public void checkHighScore(){
        updateCurrentScore();
        if (currentScore > highscore) {
            highscore = currentScore;
        }
    }

    }

