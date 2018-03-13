package ui.interfaces;

import game.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import ui.Fonts;
import ui.elements.Button;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawText;

public class MissionEndInterface extends UserInterface {

    private static final String MESSAGE_WINNER = "Stage Cleared. Well done!";
    private static final String MESSAGE_LOSER = "You died. No worries man!";
    private static final float MESSAGE_X = 50;
    private static final float MESSAGE_Y = 50;

    private boolean winner;

    public MissionEndInterface(Texture background, boolean winner) {
        super(background);
        this.winner = winner;
        buttons.add(new Button("Back to Base", "back", 100f, 850f, BUTTON_WIDTH, BUTTON_HEIGHT));
    }

    public void update() {
        super.updateButtons();
    }

    @Override
    public void render() {
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);
        if (winner) {
            drawText(MESSAGE_X, MESSAGE_Y, MESSAGE_WINNER, Fonts.TITLE.font, Color.white);
        }
        for (Button b : buttons)
            b.render();
    }
}
