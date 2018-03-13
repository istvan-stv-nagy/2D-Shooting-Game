package ui.elements;

import org.newdawn.slick.Color;
import ui.Fonts;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawRectangle;
import static helpers.Artist.drawText;
import static loader.ImageLoader.getImage;

public class MovingButton extends Button {

    private static final int SPEED = 100;
    private static final int AMPLITUDE = 15;
    private static final int BORDER = 5;

    private String textureName;
    private int counter;
    private long time = 0;

    public MovingButton(String text, String command, float x, float y, int width, int height, String textureName) {
        super(text, command, x, y, width, height);
        this.textureName = textureName;
    }

    @Override
    public void move() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - time > SPEED) {
            time = currentTime;
            counter++;
            if (counter == Integer.MAX_VALUE) counter = 0;
        }
        x = originalX + (float)Math.sin(counter) * AMPLITUDE;
    }

    public void resetPosition() {
        x = originalX;
    }

    @Override
    public void render() {
        if (isMouseOver()) {
            drawRectangle(x,y,width,height, Color.yellow, 2);
            drawText(x, y, text, Fonts.BUTTON.font, Color.yellow);
            drawQuadTex(getImage(textureName + "-active"), x + width - height - BORDER, y + BORDER, height - 2*BORDER, height - 2*BORDER);
        } else {
            drawRectangle(x,y,width,height,Color.white, 2);
            drawText(x, y, text, Fonts.BUTTON.font, Color.white);
            drawQuadTex(getImage(textureName + "-inactive"), x + width - height - BORDER, y + BORDER, height - 2*BORDER, height - 2*BORDER);
        }
    }
}
