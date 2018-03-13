package ui.interfaces;

import assets.Inventory;
import assets.Item;
import game.Game;
import org.newdawn.slick.Color;
import ui.elements.ItemWindow;

import java.util.ArrayList;
import java.util.List;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class InventoryInterface extends UserInterface {
    private Inventory inventory;
    private List<ItemWindow> itemWindows;

    private static final int OFFSET_X = 100;
    private static final int OFFSET_Y = 100;
    private static final int SPACE_X = 10;
    private static final int SPACE_Y = 10;

    public InventoryInterface(Inventory inventory) {
        super(getImage("background2"));
        this.inventory = inventory;
        itemWindows = new ArrayList<>();
        int counter = 0;
        for (Item i : inventory.getItems().keySet()) {
            itemWindows.add(new ItemWindow((counter / 5)*(ItemWindow.WIDTH + SPACE_X) + OFFSET_X, (counter % 5)*(ItemWindow.HEIGHT + SPACE_Y) + OFFSET_Y, Color.black, Color.gray, i, inventory.getItems().get(i)));
            counter++;
        }
    }

    public void update() {
        updateButtons();
    }

    @Override
    public void render() {
        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        for (ItemWindow itemWindow : itemWindows)
            itemWindow.render();
    }
}
