module AlienDestruction {
    requires hanyaeger;
    exports AlienDestruction;
    exports AlienDestruction.Buttons;
    exports AlienDestruction.Scenes;
    exports AlienDestruction.MenuBar;

    opens audio;
    opens backgrounds;
    opens sprites;
    exports AlienDestruction.Game;
    exports AlienDestruction.Entities;
    exports AlienDestruction.Entities.Obstacles;
    exports AlienDestruction.Entities.Enemies;
    exports AlienDestruction.Weapons;
}