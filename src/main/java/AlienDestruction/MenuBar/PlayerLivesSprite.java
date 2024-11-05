package AlienDestruction.MenuBar;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.entities.impl.DynamicSpriteEntity;

/**
 * De PlayerLivesSprite is een  class die representeert een sprite die het aantal levens van de speler weergeeft.
 * DynamicSpriteEntity: PlayerLivesSprite erft van de DynamicSpriteEntity klasse.
 */
public class PlayerLivesSprite extends DynamicSpriteEntity {

    /**
     * public PlayerLivesSprite(Coordinate2D location): De constructor initialiseert de PlayerLivesSprite met de volgende eigenschappen:
     *  Afbeelding: "sprites/xWingV1.png" (vermoedelijk een afbeelding van een ruimteschip)
     *  Locatie: location (de positie waar de sprite getekend moet worden)
     *  Grootte: Size(30, 60) (de breedte en hoogte van de sprite)
     * @param location de positie waar de sprite getekend moet worden
     */
    public PlayerLivesSprite(Coordinate2D location) {
        super("sprites/xWingV1.png", location, new Size(30,60));
    }
}
