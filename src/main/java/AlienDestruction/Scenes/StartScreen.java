package AlienDestruction.Scenes;

import AlienDestruction.App;
import AlienDestruction.Buttons.DifficultyButton;
import AlienDestruction.Buttons.MusicButton;
import AlienDestruction.Buttons.StartButton;
import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.media.SoundClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

 public class StartScreen extends MenuManager {
    protected final App app;

    public StartScreen(App app) {
        this.app = app;
    }

    public void setupScene() {
        setBackgroundImage("backgrounds/universe1.jpg");

        SoundClip musicClip = new SoundClip("audio/swmaintheme.mp3", SoundClip.INDEFINITE);
        MusicButton musicButton;
        musicButton = new MusicButton(new Coordinate2D(20, 20), musicClip, new Size( 20,20) );
        addEntity(musicButton);
    }

     @Override
     public void setupEntities() {
         super.setupEntities();
         addStartMenuText();
         addStartButton();
         addDifficultyButtons();
     }

     private void addStartMenuText() {
         var startmenuText = new TextEntity(
                 new Coordinate2D(getWidth() / 2, getHeight() / 4),
                 "Imperial Destruction"
         );
         startmenuText.setAnchorPoint(AnchorPoint.TOP_CENTER);
         startmenuText.setFill(Color.ANTIQUEWHITE);
         startmenuText.setFont(Font.font("Roboto", FontWeight.SEMI_BOLD, 60));
         addEntity(startmenuText);
     }

     private void addStartButton() {
         var startButton = new StartButton(app, new Coordinate2D(getWidth() / 2, getHeight() * 5 / 6));
         addEntity(startButton);
     }

     private void addDifficultyButtons() {
         Coordinate2D button1Location = new Coordinate2D(getWidth() * 1 / 3, getHeight() / 2);
         Coordinate2D button2Location = new Coordinate2D(getWidth() * 1 / 2 - 35, getHeight() / 2);
         Coordinate2D button3Location = new Coordinate2D(getWidth() * 2 / 3, getHeight() / 2);

         DifficultyButton easyButton = new DifficultyButton(button1Location, 0, app);
         DifficultyButton mediumButton = new DifficultyButton(button2Location, 1, app);
         DifficultyButton hardButton = new DifficultyButton(button3Location, 2, app);

         // default difficulty is Medium
         app.setDifficulty(1);

         addEntity(easyButton);
         addEntity(mediumButton);
         addEntity(hardButton);
     }

 }

