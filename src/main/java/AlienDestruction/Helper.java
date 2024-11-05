package AlienDestruction;

import javafx.scene.input.KeyCode;

import java.util.Random;

/**De Helper klasse is een hulpklasse die statische final (constanten) groepeert voor verschillende game-elementen.
 */

public class Helper {

    private static Random random = new Random();

    public static class SceneIds {
        public static final int StartScreen = 0;
        public static final int GameScreen = 1;
        public static final int EndScreen = 2;
        public static final int GameMenu = 3;
    }

    public static class KeyStroke {
        public static final KeyCode LEFT = KeyCode.A;
        public static final KeyCode RIGHT = KeyCode.D;
        public static final KeyCode BOOST = KeyCode.W;
        public static final KeyCode LEFTBOOST = KeyCode.Q;
        public static final KeyCode RIGHTBOOST = KeyCode.E;
        public static final KeyCode BRAKE = KeyCode.S;
        public static final KeyCode FIRE = KeyCode.SPACE;
    }

    public static class Direction {
        public static final double GOLEFT = 270;
        public static final double GORIGHT = 90;
        public static final double GOUP = 180;
        public static final double DOWN = 0;
        public static final double GOLEFTUP = 225;
        public static final double GORIGHTUP = 135;
        public static final double GOLEFTDOWN = 315;
        public static final double GORIGHTDOWN = 45;
    }

    public static class Speed {
        public static final double LOW = 1.5;
        public static final double MEDIUM = 3;
        public static final double HIGH = 5;
    }

    public static class Size {
        public  static final double SMALL = 30;     // default 40
        public static final double MEDIUM = 60;     // default 80
        public static final double LARGE = 90;     // default 120
        public static final double HUGE = 120;      // default 160
    }

    public static int getRandomInt(int min, int max){
        return random.nextInt(max - min) + min;
    }

    public static double getRandomDouble(double min, double max){
        double randomDouble = random.nextDouble() * (max - min) + min;
        return Math.round(randomDouble * 10.0) / 10.0;
    }

    public static int getRandomX(double width){
        int minX = 40;
        int maxX = (int) width - 40;

        return Helper.getRandomInt(minX, maxX);
    }
}
