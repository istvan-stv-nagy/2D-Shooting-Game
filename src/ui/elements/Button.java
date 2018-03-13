package ui.elements;

import org.newdawn.slick.Color;
import ui.Fonts;

import static helpers.Artist.drawRectangle;
import static helpers.Artist.drawText;

public class Button extends UIElement {

    protected String text;
    private String command;
    protected float originalX;

    public Button(String text, String command, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.originalX = x;
        this.text = text;
        this.command = command;
    }

    public void render() {
        if (isMouseOver()) {
            drawRectangle(x,y,width,height,Color.yellow, 2);
            drawText(x, y, text, Fonts.BUTTON.font, Color.yellow);
        } else {
            drawRectangle(x,y,width,height,Color.white, 2);
            drawText(x, y, text, Fonts.BUTTON.font, Color.white);
        }
    }

    public void move() {}

    public String getCommand() {
        return command;
    }
}
