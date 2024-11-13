package AlienDestruction.Game;

import com.github.hanyaeger.api.Timer;
import com.github.hanyaeger.api.TimerContainer;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.Set;

public class Booster implements TimerContainer {
    static final int BoostTime = 5;
    static final int CoolDownTime = 10;

    protected boolean boosterActive = false;
    protected boolean coolingDownActive = false;
    public SelfTimer timer = new SelfTimer(0);

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

    @Override
    public void setupTimers() {
        this.timer.pause();
        addTimer(this.timer);
    }

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
