package AlienDestruction.Game;

import AlienDestruction.Entities.Player;

public class Highscore {
    private Player player;
    private int highscore = 0;
    private int currentScore;

    public Highscore(Player player){
        this.player = player;
    }

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

