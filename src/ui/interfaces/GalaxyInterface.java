package ui.interfaces;

import entity.map.Galaxy;
import entity.map.Planet;
import game.Game;
import org.lwjgl.input.Mouse;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class GalaxyInterface extends UserInterface {

    private Galaxy galaxy;
    private Planet selectedPlanet;

    public GalaxyInterface(Galaxy galaxy) {
        super(getImage("background3"));
        this.galaxy = galaxy;
    }

    public void update() {
        if (Mouse.isButtonDown(0) && !pressed) {
            selectedPlanet = galaxy.getSelectedPlanet();
        }
        pressed = Mouse.isButtonDown(0);
    }

    @Override
    public void render() {
        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        galaxy.render();
    }

    public Planet getSelectedPlanet() {
        return selectedPlanet;
    }

    public void resetSelectedPlanet() {
        selectedPlanet = null;
    }
}
