package ui.interfaces;

import assets.Item;
import entity.ship.Ship;
import game.Game;
import game.Mission;
import org.newdawn.slick.Color;
import ui.Fonts;

import java.util.Map;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class MissionInterface extends UserInterface{
    private static final float SHIP_ICON_X = 10;
    private static final float SHIP_ICON_Y = 10;
    private static final int SHIP_ICON_WIDTH = 100;
    private static final int SHIP_ICON_HEIGHT = 100;
    private static final String SHIP_BACKGROUND = "background_ship";
    private static final int BORDER = 5;

    private static final int BAR_WIDTH = 200;
    private static final int BAR_HEIGHT = 19;
    private static final int BAR_SPACING = 27;

    private static final float INVENTORY_X = 10;
    private static final float INVENTORY_Y = 250;
    private static final float INVENTORY_SPACE = 40;

    private Mission mission;

    public MissionInterface(Mission mission) {
        super(getImage("background1"));
        this.mission = mission;
    }

    public void render() {
        Ship ship = mission.getShip();

        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        //draw ship
        drawQuadTex(getImage(SHIP_BACKGROUND), SHIP_ICON_X, SHIP_ICON_Y, SHIP_ICON_WIDTH, SHIP_ICON_HEIGHT);
        drawRectangle(SHIP_ICON_X, SHIP_ICON_Y, SHIP_ICON_WIDTH, SHIP_ICON_HEIGHT, Color.white, 2);
        drawQuadTex(getImage(ship.getType().getTexture()), SHIP_ICON_X + BORDER, SHIP_ICON_Y + BORDER, SHIP_ICON_WIDTH - 2*BORDER, SHIP_ICON_HEIGHT - 2*BORDER);

        //health bar
        drawBar(SHIP_ICON_X + SHIP_ICON_WIDTH, SHIP_ICON_Y, BAR_WIDTH, BAR_HEIGHT, "Health", ship.getHealth(), ship.getType().getMaxHealth(), ship.getType().getMaxHealth(), Color.white, Color.black, Color.red);
        //armour bar
        drawBar(SHIP_ICON_X + SHIP_ICON_WIDTH, SHIP_ICON_Y + BAR_SPACING, BAR_WIDTH, BAR_HEIGHT, "Amour", ship.getArmour(), ship.getType().getMaxArmour(), ship.getType().getMaxArmour(), Color.white, Color.black, Color.gray);
        //fuel bar
        drawBar(SHIP_ICON_X + SHIP_ICON_WIDTH, SHIP_ICON_Y + 2*BAR_SPACING, BAR_WIDTH, BAR_HEIGHT, "Fuel", ship.getFuel(), ship.getType().getFuelCapacity(), ship.getType().getFuelCapacity(), Color.white, Color.black, Color.green);
        //ammo bar
        drawBar(SHIP_ICON_X + SHIP_ICON_WIDTH, SHIP_ICON_Y + 3*BAR_SPACING, BAR_WIDTH, BAR_HEIGHT, "Ammo", 75, 100, 100, Color.white, Color.black, Color.orange);

        //inventory
        int j = 0;
        Map<Item, Integer> items = mission.getShip().getInventory().getItems();
        for (Item i : items.keySet()) {
            drawQuadTex(getImage(i.getType().getTexture()), INVENTORY_X, INVENTORY_Y + j*INVENTORY_SPACE, i.getType().getWidth(), i.getType().getHeight());
            drawText(INVENTORY_X + i.getType().getWidth(), INVENTORY_Y + j*INVENTORY_SPACE, " " + items.get(i), Fonts.DEFAULT.font, Color.green);
            j++;
        }
    }

}
