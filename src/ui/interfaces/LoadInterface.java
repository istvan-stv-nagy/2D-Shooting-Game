package ui.interfaces;

import game.Game;
import ui.elements.Button;

import java.io.File;

import static helpers.Artist.drawQuadTex;
import static loader.ImageLoader.getImage;

public class LoadInterface extends UserInterface {

    public LoadInterface() {
        super(getImage("background0"));
        File folder = new File("src/res/saves");
        File[] files = folder.listFiles();
        for (int i=0; i<files.length; i++) {
            if (files[i].isFile()) {
                buttons.add(new Button(files[i].getName(), "load-file" + files[i].getName(), 100f, 300f + i*60, BUTTON_WIDTH, BUTTON_HEIGHT));
            }
        }
    }

    public void update() {
        updateButtons();
    }

    @Override
    public void render() {
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        for (Button b : buttons) {
            b.render();
        }
    }
}
