package ui.elements;

import assets.Item;
import org.newdawn.slick.Color;
import ui.Fonts;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class ItemWindow extends UIElement{

    public static final int WIDTH = 120;
    public static final int HEIGHT = 120;
    public static final int ITEM_WIDTH = 60;
    public static final int ITEM_HEIGHT = 60;
    private static final float BLEND_FACTOR = 0.75f;

    private Color frameColor;
    private Color backgroundColor;
    private Item item;
    private int amount;

    public ItemWindow(float x, float y, Color frameColor, Color backgroundColor, Item item, int amount) {
        super(x, y, WIDTH, HEIGHT);
        this.frameColor = frameColor;
        this.backgroundColor = backgroundColor;
        this.item = item;
        this.amount = amount;
    }

    public void render() {
        drawQuad(x, y, WIDTH, HEIGHT, backgroundColor, BLEND_FACTOR);
        drawRectangle(x, y, WIDTH, HEIGHT, frameColor, 3);
        drawText(x + 5, y + 1, item.getType().getName(), Fonts.INFO.font, Color.white);
        drawQuadTex(getImage(item.getType().getTexture()), x + (WIDTH - ITEM_WIDTH)/2, y + 25, ITEM_WIDTH, ITEM_HEIGHT);
        drawText(x +5, y + HEIGHT - 30, "Amount", Fonts.INFO.font, Color.white);
        drawText(x + 80, y + HEIGHT - 30, " " + amount, Fonts.INFO.font, Color.white);
    }

    public Item getItem() {
        return item;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
