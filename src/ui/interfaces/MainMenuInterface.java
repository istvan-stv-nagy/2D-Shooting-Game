package ui.interfaces;

import game.Game;
import loader.AudioMaster;
import org.newdawn.slick.Color;
import ui.Fonts;
import ui.elements.Button;
import ui.elements.MovingButton;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawText;
import static loader.ImageLoader.getImage;

public class MainMenuInterface extends UserInterface{

    private static final String TITLE = "Star WARS : Beyond Explored!";
    private static final float TITLE_X = 100;
    private static final float TITLE_Y = 100;

    public MainMenuInterface() {
        super(getImage("background0"));
        buttons.add(new MovingButton("Play Game", "play", 100f, 300f, BUTTON_WIDTH, BUTTON_HEIGHT, "play"));
        buttons.add(new MovingButton("Instructions", "instr", 100f, 360, BUTTON_WIDTH, BUTTON_HEIGHT, "info"));
        buttons.add(new MovingButton("Achievements", "achieve", 100f, 420, BUTTON_WIDTH, BUTTON_HEIGHT, "achievements"));
        buttons.add(new MovingButton("Load Game", "load", 100f, 480, BUTTON_WIDTH, BUTTON_HEIGHT, "load"));
        buttons.add(new MovingButton("Settings", "set", 100f, 540, BUTTON_WIDTH, BUTTON_HEIGHT, "settings"));
        buttons.add(new MovingButton("About", "about", 100f, 600, BUTTON_WIDTH, BUTTON_HEIGHT, "about"));
        buttons.add(new MovingButton("Exit Game", "exit", 100f, 660, BUTTON_WIDTH, BUTTON_HEIGHT, "exit"));
        AudioMaster.playMusic("background_music");
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

    public void render() {
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        drawText(TITLE_X, TITLE_Y, TITLE, Fonts.TITLE.font, Color.white);
        for (Button b : buttons) {
            b.render();
        }
    }
}
