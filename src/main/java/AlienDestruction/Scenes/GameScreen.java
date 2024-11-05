package AlienDestruction.Scenes;

import AlienDestruction.App;
import AlienDestruction.Buttons.MenuButton;
import AlienDestruction.Buttons.MusicButton;
import AlienDestruction.Buttons.PauseButton;
import AlienDestruction.Entities.*;
import AlienDestruction.Game.EnemySpawner;
import AlienDestruction.Game.Level;
import AlienDestruction.Game.PowerUpSpawner;
import AlienDestruction.MenuBar.BlackRectangle;
import AlienDestruction.MenuBar.PlayerLivesSprite;
import AlienDestruction.Weapons.IShootable;
import AlienDestruction.Weapons.LaserGun;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.EntitySpawnerContainer;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.EntitySpawner;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Set;

public class GameScreen extends DynamicScene implements EntitySpawnerContainer, KeyListener {

    private TextEntity playerLivesText;
    private TextEntity scoreText;
    private TextEntity levelText;
    private TextEntity pauseText;

    protected App app;
    private Player player;
    private boolean isPaused;
    private final EntitySpawner weaponTypeSpawner;
    private Level level = new Level();

    public GameScreen(App app) {
        this.app = app;
        this.weaponTypeSpawner = new LaserGun(10);
    }

    public TextEntity getScoreText() {
        return scoreText;
    }

    public TextEntity getPlayerLivesText() {
        return playerLivesText;
    }

    public TextEntity getLevelText() {
        return levelText;
    }

    @Override
    public void setupScene() {
        setBackgroundImage("backgrounds/universe2.jpg");
        isPaused = false;
    }

    @Override
    public void setupEntities() {
        setupPlayer();
        setupUIElements();
        setupTextEntities();
        setupButtons();
    }

    private void setupPlayer() {
        player = new Player(new Coordinate2D(getWidth() / 2, 550), this, app);
        player.setWeapon((IShootable) weaponTypeSpawner);
        addEntity(player);
    }

    private void setupUIElements() {
        addEntity(new BlackRectangle(new Coordinate2D(0, 0), new Size(getWidth(), 80)));
        addEntity(new PlayerLivesSprite(new Coordinate2D(20, 20)));
    }

    private void setupTextEntities() {
        double textMenu = 20;
        playerLivesText = createTextEntity(new Coordinate2D(60, textMenu), ": " + player.getLives(), textMenu);
        levelText = createTextEntity(new Coordinate2D(250, textMenu), "Level:" + level.getPlayerLevelNumber(), textMenu);
        scoreText = createTextEntity(new Coordinate2D(450, textMenu), "Score:" + player.getScore(), textMenu);
        pauseText = createTextEntity(new Coordinate2D(500, 400), "Press any key to continue!", 60);
        pauseText.setVisible(false);
    }

    private TextEntity createTextEntity(Coordinate2D position, String text, double fontSize) {
        TextEntity textEntity = new TextEntity(position, text);
        textEntity.setFill(Color.GOLD);
        textEntity.setFont(Font.font("Roboto", FontWeight.BOLD, fontSize));
        addEntity(textEntity);
        return textEntity;
    }

    private void setupButtons() {
        double textMenu = 20;
        addEntity(new MusicButton(new Coordinate2D(750, 10), new SoundClip("audio/swmaintheme.mp3", SoundClip.INDEFINITE), new Size(0.5 * textMenu, 0.5 * textMenu)));
        addEntity(new PauseButton(new Coordinate2D(800, 15), this));
        addEntity(new MenuButton(app, new Coordinate2D(980, textMenu)));
    }

    @Override
    public void setupEntitySpawners() {
        addEntitySpawner(weaponTypeSpawner);
        addEntitySpawner(new EnemySpawner(player, level, this, app));
        addEntitySpawner(new PowerUpSpawner(level, this));

        if (weaponTypeSpawner.isActive()) {
            weaponTypeSpawner.pause();
        }
    }

    @Override
    public void pause() {
        super.pause();
        pauseText.setVisible(true);
        isPaused = true;
    }

    @Override
    public void resume() {
        super.resume();
        pauseText.setVisible(false);
        isPaused = false;
    }

    public void onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D) {
        if (isPaused) {
            resume();
        } else {
            pause();
        }
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (isPaused) {
            resume();
        }
    }
}