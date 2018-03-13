package ui.elements;

import entity.ship.Ship;
import org.newdawn.slick.Color;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class ShipWindow extends UIElement {

    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    private static final int SHIP_WIDTH = 100;
    private static final int SHIP_HEIGHT = 100;
    private static final float BLEND_FACTOR = 0.75f;

    private Color frameColor;
    private Color selectedColor;
    private Color backgroundColor;
    private Ship ship;

    public ShipWindow(float x, float y, Color frameColor, Color backgroundColor, Ship ship) {
        super(x, y, WIDTH, HEIGHT);
        this.frameColor = frameColor;
        this.selectedColor = Color.orange;
        this.backgroundColor = backgroundColor;
        this.ship = ship;
    }

    public void render() {
        drawQuad(x, y, WIDTH, HEIGHT, backgroundColor, BLEND_FACTOR);
        Color lineColor = frameColor;
        if (ship.isSelected()) lineColor = selectedColor;
        drawRectangle(x, y, WIDTH, HEIGHT, lineColor, 3);
        drawQuadTex(getImage(ship.getType().getTexture()), x + 10, y + 10, SHIP_WIDTH, SHIP_HEIGHT);
    }

    public Ship getShip() {
        return ship;
    }
}
