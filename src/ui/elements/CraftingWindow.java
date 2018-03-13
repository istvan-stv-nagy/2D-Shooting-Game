package ui.elements;

import assets.CraftingRecipe;
import assets.Inventory;
import assets.Resource;
import org.newdawn.slick.Color;
import ui.Fonts;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class CraftingWindow extends UIElement {

    private static final int SPACE = 50;
    private static final int BORDER = 5;
    private static final int SPACING = 200;

    private CraftingRecipe recipe;
    private Inventory inventory;

    public CraftingWindow(float x, float y, int width, int height, Inventory inventory) {
        super(x, y, width, height);
        this.inventory = inventory;
    }

    @Override
    public void render() {
        if (recipe != null) {
            //draw frame
            drawQuad(x, y, width, height, Color.darkGray);
            drawRectangle(x, y, width, height, Color.white, 3);

            //print info
            float offsetX = x + BORDER;
            float offsetY = y + BORDER;

            //draw item name
            drawText(offsetX, offsetY, "Item name:", Fonts.SUBTITLE.font, Color.green);
            drawText(offsetX  + Fonts.SUBTITLE.font.getWidth("Item name") + 2*SPACE, offsetY + 2, recipe.getTargetItem().getType().getName(), Fonts.PLANET.font, Color.white);
            offsetY += SPACE;

            //draw recipe
            drawText(offsetX, offsetY, "Necessary resources:", Fonts.SUBTITLE.font, Color.green);
            offsetY += SPACE;
            for (Resource r : recipe.getResources()) {
                drawText(offsetX, offsetY, r.getItem().getType().getName(), Fonts.PLANET.font, Color.white);
                drawQuadTex(getImage(r.getItem().getType().getTexture()), offsetX + SPACING, offsetY, 30, 30);
                drawText(offsetX + SPACING + 60, offsetY, r.getAmount() + "/" + inventory.getAmount(r.getItem()), Fonts.PLANET.font, Color.white);
                offsetY += SPACE;
            }
        }
    }

    public CraftingRecipe getRecipe() {
        return recipe;
    }

    public void setRecipe(CraftingRecipe recipe) {
        this.recipe = recipe;
    }
}
