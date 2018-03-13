package ui.interfaces;

import loader.AudioMaster;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.Texture;
import ui.elements.Button;

import java.util.ArrayList;
import java.util.List;

public abstract class UserInterface{

    protected static final int BUTTON_WIDTH = 480;
    protected static final int BUTTON_HEIGHT = 40;

    private static final String NULL = "null";

    protected Texture background;
    protected List<Button> buttons;
    protected String command;

    protected static boolean pressed = false;

    public UserInterface(Texture background) {
        this.background = background;
        buttons = new ArrayList<>();
        command = "null";
    }

    public void updateButtons() {
        if (Mouse.isButtonDown(0) && !pressed) {
            for (Button b : buttons) {
                if (b.isMouseOver()) {
                    command = b.getCommand();
                    AudioMaster.playSoundEffect("click");
                }
            }
        }
        pressed = Mouse.isButtonDown(0);
    }

    public abstract void render();

    public String getCommand() {
        String result = command;
        command = NULL;
        return result;
    }

    public Texture getBackground() {
        return background;
    }
}
