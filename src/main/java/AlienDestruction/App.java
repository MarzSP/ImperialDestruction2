package AlienDestruction;



import AlienDestruction.Scenes.EndScreen;
import AlienDestruction.Scenes.GameMenu;
import AlienDestruction.Scenes.GameScreen;
import AlienDestruction.Scenes.StartScreen;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.YaegerGame;


/** App is de hoofd-klasse van deze game applicatie, deze erft van YaegerGame(Game Engine)
 */
public class App extends YaegerGame {

    private int difficulty;

    public static void main(String[] args ) {
        launch("--noSplash");
    }

    @Override
    public void setupGame() {
        setGameTitle("Imperial Destruction");
        setSize(new Size(1200, 800));

    }

    @Override
    public void setupScenes() {

    addScene(0, new StartScreen(this));
    addScene(1, new GameScreen(this));
    addScene(2, new EndScreen(this));
    addScene(3, new GameMenu());

    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
