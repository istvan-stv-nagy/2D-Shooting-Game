package ui.elements;

import game.Profile;
import org.newdawn.slick.Color;
import ui.Fonts;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class ProfileWindow extends UIElement {

    private static final float PROFILE_X = 100;
    private static final float PROFILE_Y = 50;
    private static final int PROFILE_SIZE = 150;
    private static final int IMAGE_SIZE = 150;
    private static final int LEVEL_SIZE = 35;
    private static final String PROFILE_BACKGROUND = "background_ship";

    private static final int EXP_BAR_WIDTH = 300;
    private static final int EXP_BAR_HEIGHT = 30;

    private Profile profile;

    public ProfileWindow(Profile profile) {
        super(PROFILE_X, PROFILE_Y, PROFILE_SIZE, PROFILE_SIZE);
        this.profile = profile;
    }

    @Override
    public void render() {
        drawQuadTex(getImage(PROFILE_BACKGROUND), x, y, width, height);
        drawRectangle(x, y, width, height, Color.white, 2);
        drawQuadTex(getImage(profile.getTexture()), x + (width - IMAGE_SIZE)/2, y + (height - IMAGE_SIZE)/2, width, height);
        drawRectangle(x + width - LEVEL_SIZE, y, LEVEL_SIZE, LEVEL_SIZE, Color.white, 2);
        int center;
        if (profile.getLevel() < 10) center = LEVEL_SIZE/2 - 6;
        else center = 2;
        drawText(x + width - LEVEL_SIZE + center, y + 2, profile.getLevel()+ "", Fonts.DEFAULT.font, Color.yellow);

        //draw experience
        drawBar(x+width, y, EXP_BAR_WIDTH, EXP_BAR_HEIGHT, profile.getExperience() + "/" + profile.getNextLevelExperience(), profile.getExperience()-profile.getPrevLevelExperience(), profile.getNextLevelExperience() - profile.getPrevLevelExperience(), profile.getNextLevelExperience()-profile.getPrevLevelExperience(), Color.white, Color.gray, Color.blue);
    }
}
