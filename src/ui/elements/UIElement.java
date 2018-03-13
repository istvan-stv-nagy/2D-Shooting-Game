package ui.elements;

import game.Game;
import org.lwjgl.input.Mouse;

public abstract class UIElement {

    protected float x;
    protected float y;
    protected int width, height;

    public UIElement(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isMouseOver() {
        int mouseX = Mouse.getX();
        int mouseY = Game.SCREEN_HEIGHT - Mouse.getY();
        return x <= mouseX && mouseX <= x + width && y <= mouseY && mouseY <= y + height;
    }

    public abstract void render();

}
