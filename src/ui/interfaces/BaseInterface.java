package ui.interfaces;

import game.Game;
import ui.elements.Button;
import ui.elements.MovingButton;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class BaseInterface extends UserInterface {

    public BaseInterface() {
        super(getImage("background2"));
        buttons.add(new MovingButton("Go on a mission", "go", 100f, 300f, BUTTON_WIDTH, BUTTON_HEIGHT, "mission"));
        buttons.add(new MovingButton("Inventory", "inventory", 100f, 360f, BUTTON_WIDTH, BUTTON_HEIGHT, "inventory"));
        buttons.add(new MovingButton("Crafting", "craft", 100f, 420, BUTTON_WIDTH, BUTTON_HEIGHT, "crafting"));
        buttons.add(new MovingButton("Shipyard", "check-ships", 100f, 480, BUTTON_WIDTH, BUTTON_HEIGHT, "shipyard"));
        buttons.add(new MovingButton("Library", "library", 100f, 540, BUTTON_WIDTH, BUTTON_HEIGHT, "library"));
        buttons.add(new MovingButton("Back to Menu", "back", 100f, 600, BUTTON_WIDTH, BUTTON_HEIGHT, "back"));
    }

    public void update() {
        updateButtons();
        for (Button b : buttons) {
            MovingButton movingButton = (MovingButton)b;
            if (movingButton.isMouseOver())
                movingButton.move();
            else
                movingButton.resetPosition();
        }
    }

    @Override
    public void render() {
        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        for (Button b : buttons) {
            b.render();
        }
    }
}
