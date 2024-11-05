package AlienDestruction.Scenes;

import com.github.hanyaeger.api.scenes.StaticScene;
import com.github.hanyaeger.api.scenes.YaegerScene;

/** De `MenuManager` klasse is een abstracte klasse die fungeert als basis voor het beheren van menu's in het startscherm, gamescherm en eindscherm.
 Het bevat alleen de methodes setupScene() en setupEntities() die in de subclasses geimplementeerd wordt.
 Implements: YaegerScene
**/

 public abstract class MenuManager extends StaticScene implements YaegerScene {

    /**
     * de abstrate methode setupScene moet worden geïmplementeerd door klassen die MenuManager erven. Deze kan de visuele opmaak van de menuschermen verzorgen.
     */
    public void setupScene() {

    }

    /**
     * dde abstracte methode setupEntities moet worden gegeïmplementeerd door klassen die MenuManager erven.
     * Deze kan entiteiten (visuele elementen) toevoegen aan een menuscherm, zoals knoppen en tekstlabels.
     */
    public void setupEntities() {
    }
}





