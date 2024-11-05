package AlienDestruction.MenuBar;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.YaegerEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;

import java.util.ArrayList;

/**
 * De BlackRectangle class representeert een zwarte rechthoekige entiteit bovenin het scherm, welke fungeert als in-game menu balk.
 */
public class BlackRectangle extends RectangleEntity {

    /**
     * De constructor initialiseert dde zwarte rechthoek met de opgegeven beginpositie (Initial location) en grootte (size).
      * @param initialLocation beginpositie (Initial location
     * @param size  grootte (size)
     */
    public BlackRectangle(Coordinate2D initialLocation, Size size) {
        super(initialLocation, size);
    }

    YaegerEntity[] createEntities() {
        var list = new ArrayList<YaegerEntity>();

        return (YaegerEntity[]) list.toArray();
    }
}







