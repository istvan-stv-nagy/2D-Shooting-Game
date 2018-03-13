package ui.interfaces;

import entity.ship.Ship;
import game.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import ui.Fonts;
import ui.elements.Button;
import ui.elements.ShipWindow;

import java.util.ArrayList;
import java.util.List;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class ShipyardInterface extends UserInterface {

    private static final int OFFSET_X = 100;
    private static final int OFFSET_Y = 100;
    private static final int SPACE_X = 10;
    private static final int SPACE_Y = 10;

    private static final int INFO_X = 640;
    private static final int INFO_Y = 100;
    private static final int INFO_WIDTH = 500;
    private static final int INFO_HEIGHT = 700;

    private static final Color DARK_GREEN = new Color(0,102,0);
    private static final Color LIGHT_BLUE = new Color(0,204,204);

    private static final int SHIP_SIZE = 200;

    private static final float BLEND = 0.75f;

    private List<Ship> ships;
    private Ship selectedShip;
    private List<ShipWindow> shipWindows;

    public ShipyardInterface(List<Ship> ships) {
        super(getImage("background2"));
        this.ships = ships;
        if (ships.size() > 0)
            selectedShip = ships.get(0);
        shipWindows = new ArrayList<>();
        int counter = 0;
        for (Ship s : ships) {
            shipWindows.add(new ShipWindow((counter / 5)*(ShipWindow.WIDTH + SPACE_X) + OFFSET_X, (counter % 5)*(ShipWindow.HEIGHT + SPACE_Y) + OFFSET_Y, Color.black, Color.gray, s));
            counter++;
        }
        buttons.add(new Button("Select", "select-ship", 100f, 790f, BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(new Button("Back", "back", 100f, 850f, BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(new Button("Tank up", "tank-up", 100f, 730f, BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    public void update() {
        if (Mouse.isButtonDown(0) && !pressed) {
            for (Button b : buttons) {
                if (b.isMouseOver()) {
                    command = b.getCommand();
                }
            }
            for (ShipWindow sw : shipWindows) {
                if (sw.isMouseOver()) {
                    selectedShip = sw.getShip();
                }
            }
        }
        pressed = Mouse.isButtonDown(0);
    }

    @Override
    public void render() {
        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        for (Button b : buttons) {
            b.render();
        }

        for (ShipWindow shipWindow : shipWindows)
            shipWindow.render();

        drawQuad(INFO_X, INFO_Y, INFO_WIDTH, INFO_HEIGHT, Color.lightGray, BLEND);
        drawRectangle(INFO_X, INFO_Y, INFO_WIDTH, INFO_HEIGHT, Color.black, 3);
        drawQuadTex(getImage(selectedShip.getType().getTexture()), INFO_X + 10, INFO_Y + 10, SHIP_SIZE, SHIP_SIZE);
        drawText(INFO_X + SHIP_SIZE + 5, INFO_Y + 10, selectedShip.getType().getBrand(), Fonts.BUTTON.font, Color.black);
        drawText(INFO_X + SHIP_SIZE + 5, INFO_Y + 50, selectedShip.getModel(), Fonts.BUTTON.font, DARK_GREEN);

        //draw health
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 20, "Health", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 20, 300, 25, selectedShip.getHealth(), selectedShip.getType().getMaxHealth(), Ship.MAX_HEALTH, Color.black, Color.darkGray, new Color((float)selectedShip.getHealth()/selectedShip.getType().getMaxHealth(),0f,0f));

        //draw armour
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 60, "Armour", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 60, 300, 25, selectedShip.getArmour(), selectedShip.getType().getMaxArmour(), Ship.MAX_ARMOUR, Color.black, Color.darkGray, new Color(50,50,50));

        //draw speed
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 100, "Lateral Speed", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 100, 300, 25, selectedShip.getVelX(), selectedShip.getVelX(), Ship.MAX_VEL_X, Color.black, Color.darkGray, LIGHT_BLUE);
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 140, "Forward Speed", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 140, 300, 25, selectedShip.getVelY(), selectedShip.getVelY(), Ship.MAX_VEL_Y, Color.black, Color.darkGray, LIGHT_BLUE);

        //draw firing speed
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 180, "Firing Speed", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 180, 300, 25, 1.0f/selectedShip.getFiringSpeed(), 1.0f/selectedShip.getFiringSpeed(),1.0f/Ship.MAX_FIRING_SPEED, Color.black, Color.darkGray, Color.orange);

        //draw fuel
        drawText(INFO_X + 10, INFO_Y + SHIP_SIZE + 220, "Fuel", Fonts.INFO.font, Color.black);
        drawBar(INFO_X + 160, INFO_Y + SHIP_SIZE + 220, 300, 25, selectedShip.getFuel(), selectedShip.getType().getFuelCapacity(),Ship.MAX_FUEL_CAPACITY, Color.black, Color.darkGray, DARK_GREEN);

    }

    public Ship getSelectedShip() {
        return selectedShip;
    }
}
