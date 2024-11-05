package AlienDestruction.Entities;

import AlienDestruction.App;
import AlienDestruction.Game.Booster;
import AlienDestruction.Entities.PowerUps.PowerUpLaser;
import AlienDestruction.Entities.PowerUps.PowerUpLives;
import AlienDestruction.Helper;
import AlienDestruction.Game.Highscore;
import AlienDestruction.Scenes.GameScreen;
import AlienDestruction.Weapons.IShootable;
import AlienDestruction.Weapons.LaserBeam;
import AlienDestruction.Weapons.WeaponType;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Newtonian;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;
import com.github.hanyaeger.api.entities.impl.TextEntity;
import com.github.hanyaeger.api.media.SoundClip;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

import java.util.List;
import java.util.Set;

/**
 * DynamicSpriteEntity: Erft van de DynamicSpriteEntity klasse die functionalit kan geven aan een bewegende sprite entity met een afbeelding.
 * KeyListener: Implementeert de KeyListener interface om toetsenbord-input te detecteren.
 * SceneBorderTouchingWatcher: Implementeert de SceneBorderTouchingWatcher interface om te detecteren wanneer de speler de rand van het scherm raakt.
 * Newtonian: Implementeert de Newtonian interface om physics simulatie toe te passen zoals zwaartekracht..
 * Collided: Implementeert de Collided interface om te detecteren wanneer de speler met andere objecten in het spel botst.
 */
public class Player extends DynamicSpriteEntity implements KeyListener, SceneBorderTouchingWatcher, Newtonian, Collided {

    /**
     *   App app is final:
     *  Dit betekent dat de App-instantie altijd een geldige referentie naar de Player-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    private final App app;

    /**
     *  GameScreen gameScreen is final:
     *  Dit betekent dat de GameScreen-instantie altijd een geldige referentie naar de Player-instantie heeft.
     * Dit voorkomt dat er fouten optreden door null-waarden of ongeldige verwijzingen.
     */
    private final GameScreen gameScreen;
    private IShootable shootable;
    private int lives;
    private int score;
    private static int finalScore;
    private boolean canShoot = true;
    private Booster booster;
    private boolean laserPowerUpActive = false; // Boolean voor PowerUpLaser
    private long laserPowerUpEndTime = 0; // int voor PowerUpLaser

    Highscore highscore = new Highscore(this);

    /**
     * Player(Coordinate2D location, GameScreen gameScreen, App app):
     * Initialiseert de speler entity met locatie, referentie naar GameScreen (waar je de speler ziet )en App (waar de speelschermen worden aangemaakt).
     * Stelt het aantal levens in op 3 en initialiseert de booster.
     * @param location De locatie van de speler
     * @param gameScreen Een referentie van het GameScreen waar de speler zich op bevind
     * @param app De referentie van app staat hier tussen, zodat zodra de levens van de speler <0 zijn, er naar het eindscherm genavigeerd kan worden.
     */
    public Player(Coordinate2D location, GameScreen gameScreen, App app) {
        super("sprites/xWingV1.png", location, new Size(80,80));
        this.gameScreen = gameScreen;
        this.app = app;
        this.setLives(3);
        this.booster = new Booster();
        setGravityConstant(0.080);
        setFrictionConstant(0.00);
    }


    public int getLives() {
        return lives;
    }
    public int getScore() {
        return score;
    }
    public static int getFinalScore() {
        return finalScore;

    }
    public void setScore(int score) {
        this.score = score;
        gameScreen.getScoreText().setText("Score: " + this.getScore());
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    /**
     * Stelt de beweging van de (sprite) speler in, rekening houdend met de booster.
     * @param speed     the speed as a {@code double}
     * @param direction the direction in degrees as a {@code double}
     */
    @Override
    public void setMotion( double speed, final double direction) {
        boolean boosterActive = booster.isActive();
        if (boosterActive) {
            speed *= 1.5;
        }
        super.setMotion(speed, direction);
    }

    /**
     * Reageert op veranderingen in ingedrukte toetsen. Behandelt schieten, bewegen met pijltjestoetsen, boosten en hoogtecontrole.
     * @param pressedKeys A {@link Set} of {@code KeyCode} representations of the keys that are currently pressed
     * Voor de leesbaarheid van de code wordt er gebruik gemaakt van constanten uit de Helper class.
     */
    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if(pressedKeys.contains(KeyCode.SPACE) && canShoot) {
            if (laserPowerUpActive && System.currentTimeMillis() < laserPowerUpEndTime) {
                shoot();
            } else {
                shoot();
                canShoot = false;
            }
        }
        if(pressedKeys.contains(Helper.KeyStroke.LEFT)){
            setMotion(Helper.Speed.HIGH,Helper.Direction.GOLEFT);
        } else if(pressedKeys.contains(Helper.KeyStroke.RIGHT)){
            setMotion(Helper.Speed.HIGH,Helper.Direction.GORIGHT);
        } else if(pressedKeys.contains(Helper.KeyStroke.BOOST) && canShoot){
            activateBooster();
            checkMaxHeight();
            setMotion(Helper.Speed.HIGH,Helper.Direction.GOUP);
        } else if(pressedKeys.contains(Helper.KeyStroke.RIGHTBOOST)){
            activateBooster();
            checkMaxHeight();
            setMotion(Helper.Speed.HIGH,Helper.Direction.GORIGHTUP);
        } else if(pressedKeys.contains(Helper.KeyStroke.LEFTBOOST)){
            activateBooster();
            checkMaxHeight();
            setMotion(Helper.Speed.HIGH,Helper.Direction.GOLEFTUP);
        } else if(pressedKeys.contains(Helper.KeyStroke.BRAKE)) {
            setMotion(Helper.Speed.LOW,Helper.Direction.DOWN);
        }
        if(!pressedKeys.contains(Helper.KeyStroke.FIRE)) {
            canShoot = true;
        }
    }


    /**
     * Als op de [W] toets wordt gedrukt activeert deze methode de Booster als deze beschikbaar is.
     */
    private void activateBooster() {
        if (booster != null && !booster.isActive() && !booster.setCoolingDownActive(false)) {
            booster.setBoosterActive(true);
            booster.timer.resume();
        }
    }

    /**
     * notifyBoundaryTouching: reageert op het raken van de speelschermranden.
     * @param border the border of the YaegerScene the YaegerEntity is touching
     */
    @Override
    public void notifyBoundaryTouching(SceneBorder border){
        setSpeed(0);

        switch(border){
            case BOTTOM:
                setAnchorLocationY(getSceneHeight() - getHeight() - 1);
                break;
            case LEFT:
                setAnchorLocationX(1);
                break;
            case RIGHT:
                setAnchorLocationX(getSceneWidth() - getWidth() - 1);
            default:
                break;
        }
    }

    /**
     * onCollision(List<Collider> collidingObject):Detecteert botsingen en handelt deze af.
     * Verhoogt levens bij power-up, activeert laser power-up en vermindert levens bij aanraking met vijanden.
     * @param collidingObject a {@link List} of all instances of {@link Collider} this {@link Collided} has collided
     *                         with, during the last Game World Update.
     */
    @Override
    public void onCollision(List<Collider> collidingObject) {

        for (Collider collider : collidingObject) {
            boolean powerUpCollision = false;

            if (collider instanceof PowerUpLives) { //PowerUpLives
                lives = lives + 1;
                updateLives();
                checkLives();
                ((PowerUpLives) collider).remove(); //verwijderd de sprite van het scherm
                break;
            } else if (collider instanceof PowerUpLaser) { //PowerUpLaserschot x2
                powerUpCollision = true;
                activateLaserPowerUp();
                ((PowerUpLaser) collider).remove(); // verwijderd sprite uit het scherm
                break;
            } else if (collider instanceof GameEntities) {
                setAnchorLocation(new Coordinate2D((getSceneWidth() - getWidth()) / 2, 550));
                lives = lives - 1;
                updateLives();
                checkLives();
                ((GameEntities) collider).remove();
                increaseScore(((GameEntities) collider).getPoints());
                break;
            } else if (collider instanceof WeaponType) {
                WeaponType weaponType = (WeaponType) collider;

                // Dont get hit by your own laser beams
                if (weaponType.isOwnedBy(this)) {
                    powerUpCollision = true;
                } else {
                    weaponType.remove();
                }
            }

//            if(!powerUpCollision) {
//                setAnchorLocation(new Coordinate2D((getSceneWidth() - getWidth()) / 2, 550));
//                lives = lives - 1;
//                updateLives();
//                checkLives();
//            }
        }



    }

    /**
     * updateLives(): Update de levens tekst op het scherm.
     */
    private void updateLives() {
        TextEntity playerLivesText;
        gameScreen.getPlayerLivesText().setText(": " + this.getLives());
    }

    /**
     * checkMaxHeight(): Controleert of de speler niet te hoog boost.
     */
    public void checkMaxHeight() {
        if(getLocationInScene().getY() < 600) {
            setAnchorLocationY(600);
        }
    }

    /**
     * checkLives(): Controleert of de speler nog levens heeft en wisselt van scene naar EndScreen als er geen levens meer zijn.
     */
    public void checkLives(){
        if(lives < 0) {
            finalScore = score;
            app.setActiveScene(Helper.SceneIds.EndScreen);
        }
    }

    /**
     * shoot(): Schiet twee laser beams vanuit de speler.
     */
    public void shoot(){
        double x = getLocationInScene().getX();
        double y = getLocationInScene().getY();
        shootable.shoot(new LaserBeam(true, new Coordinate2D(x + 5, y), this, false));
        shootable.shoot(new LaserBeam(true, new Coordinate2D(x + 70, y),this, false));
        soundLaser();
    }

    /**
     * updateScore: update de tekst van de score zodat deze kan worden weergegeven op het gameScherm
     */
    private void updateScore() {
        TextEntity scoreText;
        gameScreen.getScoreText().setText("Score: " + getScore());
    }

    /**
     * soundLaser(): Speelt het laser geluid af.
     */
    public void soundLaser() {
        var xWingLaser = new SoundClip("audio/xwinglaser.mp3");
        xWingLaser.play();
    }

    /**
     * setWeapon(IShootable weapon): Stelt het wapen van de speler in (implementatie van IShootable).
     * @param weapon Een verwijzing naar een (object) wapen in interface IShootable
     */
    public void setWeapon(IShootable weapon) {
        this.shootable = weapon;
    }

    /**
     * activateLaserPowerUp(): Activeert de laser power-up (tijdelijk dubbele schoten PowerUpLaser).
     */
    public void activateLaserPowerUp() {
        laserPowerUpActive = true;
        laserPowerUpEndTime = System.currentTimeMillis() + 10000; // 10 seconds
    }

    /**
     * increaseScore(int points): Verhoogt de score van de speler en update de score tekst en highscore.
     * @param points int, vertegenwoordigd het aantal punten dat de speler ontvangt.
     */
    public void increaseScore(int points){
        this.score += points;
        highscore.updateCurrentScore();
        highscore.checkHighScore();
        updateScore();
    }

    public IShootable getGun() {
        return shootable;
    }

}
