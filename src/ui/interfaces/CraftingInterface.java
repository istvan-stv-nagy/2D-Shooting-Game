package ui.interfaces;

import assets.CraftingBook;
import assets.CraftingRecipe;
import assets.Inventory;
import assets.Item;
import game.Game;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import ui.elements.Button;
import ui.elements.CraftingWindow;
import ui.elements.ItemWindow;

import java.util.ArrayList;
import java.util.List;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class CraftingInterface extends UserInterface {

    private static final int OFFSET_X = 10;
    private static final int OFFSET_Y = 10;
    private static final int SPACE_X = 10;
    private static final int SPACE_Y = 10;
    private static final int ROWS = 6;

    private static final int INFO_X = 600;
    private static final int INFO_Y = 50;
    private static final int INFO_WIDTH = 600;
    private static final int INFO_HEIGHT = 850;

    private CraftingBook book;
    private Inventory inventory;
    private List<ItemWindow> itemWindows;
    private CraftingWindow craftingWindow;

    public CraftingInterface(CraftingBook book, Inventory inventory) {
        super(getImage("background2"));
        this.book = book;
        this.inventory = inventory;
        itemWindows = new ArrayList<>();
        int counter = 0;
        for (CraftingRecipe r : book.getRecipes()) {
            int amount = 0;
            if (inventory.getItems().containsKey(r.getTargetItem())) amount = inventory.getItems().get(r.getTargetItem());
            itemWindows.add(new ItemWindow((counter / ROWS)*(ItemWindow.WIDTH + SPACE_X) + OFFSET_X, (counter % ROWS)*(ItemWindow.HEIGHT + SPACE_Y) + OFFSET_Y, Color.black, Color.gray, r.getTargetItem(), amount));
            counter++;
        }
        buttons.add(new Button("Craft", "craft", 100f, 790f, BUTTON_WIDTH, BUTTON_HEIGHT));
        buttons.add(new Button("Back", "back", 100f, 850f, BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    public void update() {
        if (Mouse.isButtonDown(0) && !pressed) {
            if (buttons.get(0).isMouseOver() && craftingWindow!=null)
                command = buttons.get(0).getCommand();
            if (buttons.get(1).isMouseOver())
                command = buttons.get(1).getCommand();
            for (ItemWindow itemWindow : itemWindows) {
                if (itemWindow.isMouseOver()) {
                    if (craftingWindow == null) craftingWindow = new CraftingWindow(INFO_X, INFO_Y, INFO_WIDTH, INFO_HEIGHT, inventory);
                    craftingWindow.setRecipe(book.getRecipe(itemWindow.getItem()));
                }
            }
        }
        pressed = Mouse.isButtonDown(0);
    }

    public void updateItemWindow() {
        Item item = craftingWindow.getRecipe().getTargetItem();
        for (ItemWindow itemWindow : itemWindows) {
            if (itemWindow.getItem().equals(item)) {
                int amount = 0;
                if (inventory.getItems().containsKey(item)) amount = inventory.getItems().get(item);
                itemWindow.setAmount(amount);
            }
        }
    }

    @Override
    public void render() {
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        for (ItemWindow itemWindow : itemWindows)
            itemWindow.render();
        if (craftingWindow != null) {
            craftingWindow.render();
            buttons.get(0).render();
        }
        buttons.get(1).render();
    }

    public CraftingRecipe getSelectedReceip() {
        return craftingWindow.getRecipe();
    }
}
