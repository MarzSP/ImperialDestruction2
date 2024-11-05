package AlienDestruction.Game;

import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.Set;

/**
 * Booster is een implementatie van TimerContainer:
 *  de "Booster" knop [W] op het toetsenbord, activeert in Game Screen een tijdelijke boost voor de speler met een cooldown periode.
 *  * Implements: TimerContainer van Yaeger

 * Booster:
 * BoostTime: Statische final variabele met de duur van de boost in seconden (10). Deze is statisch final omdat deze niet zal veranderen.
 * CoolDownTime: Statische final variabele met de duur van de cooldown periode in seconden (20).
 * boosterActive: Boolean is eem variabele die aangeeft of de boost actief is (true) of niet actief (false).
 * coolingDownActive: Boolean variabele die aangeeft of de cooldown periode actief is (true) of niet (false).
 * timer: `SelfTimer` object dat gebruikt wordt om de boost en cooldown te timen.

 */
public class Booster implements TimerContainer {
    static final int BoostTime = 5;
    static final int CoolDownTime = 10;

    protected boolean boosterActive = false;
    protected boolean coolingDownActive = false;
    public SelfTimer timer = new SelfTimer(0);

    /**
     * isActive():
     * @return Retourneert `true` als de boost actief is, anders `false`.
     */
    public boolean isActive() {
        return this.boosterActive;
    }

    /**
     *  setBoosterActive(boolean boosterActive):
     * @param boosterActive Stelt de `boosterActive` variabele in.
     */
    public void setBoosterActive(boolean boosterActive) {
        this.boosterActive = boosterActive;
    }

    /**
     * setCoolingDownActive(boolean coolingDownActive):
     * @param coolingDownActive Stelt de `coolingDownActive` variabele in.
     * @return of de coolingDownActive is of niet.
     */
    public boolean setCoolingDownActive(boolean coolingDownActive) {
        this.coolingDownActive = coolingDownActive;
        return coolingDownActive;
    }

    /**
     * setupTimers(): Pauzeert de interne timer en voegt deze toe aan de lijst met timers.
     */
    @Override
    public void setupTimers() {
        this.timer.pause();
        addTimer(this.timer);
    }

 /** onMouseButtonPressed(MouseButton button, Coordinate2D coordinate2D):
            *  Controleert of de boost of cooldown actief is, zo ja, dan gebeurt er niks.
            *   Activeert de boost - start de timer voor de boost tijd en hervat de timer.
 **/
    public void onPressedKeysChange(Set<KeyCode> pressedKeys){
        if (this.boosterActive || this.coolingDownActive) {
           return;
        }

        setBoosterActive(true);
        timer.setIntervalInMs(BoostTime*500);
        timer.resume();
    }

    /**
     * Geeft een lijst terug van timers die aan het object zijn gekoppeld.
     * @return een lijst van timers die aan het object zijn gekoppeld, of null als er geen timers zijn ingesteld
     */
    @Override
    public List<Timer> getTimers() {
        return null;
    }

    /**
    Booster Innerlijke klasse:
     *  SelfTimer: Extends de `Timer` klasse en handelt de boost en cooldown functies af.
    **/

    public class SelfTimer extends Timer {
        protected SelfTimer(long intervalInMs) {
            super(intervalInMs);
        }

        /**
         *`onAnimationUpdate(long timestamp)`:
         *      Pauzeert zichzelf.
         *      Controleert of de boost actief is:
         *      Als boost actief, schakelt deze over naar cooldown, start de cooldown timer en hervat de timer.
         *      Controleert of de cooldown actief is:
         *      Als cooldown actief, schakelt deze de cooldown uit.
         * @param timestamp the timestamp of the current frame given in nanoseconds
         */
        @Override
        public void onAnimationUpdate(long timestamp) {
            this.pause();
            if (boosterActive) {
                setBoosterActive(false);
                setCoolingDownActive(true);
                this.setIntervalInMs(CoolDownTime*1000);
                this.resume();
            } else if (coolingDownActive) {
                setCoolingDownActive(false);
            }
        }
    }

}
