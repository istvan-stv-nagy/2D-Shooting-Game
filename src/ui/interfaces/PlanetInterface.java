package ui.interfaces;

import entity.enemy.EnemyType;
import entity.map.Planet;
import entity.map.PlanetResource;
import game.Game;
import org.newdawn.slick.Color;
import ui.Fonts;
import ui.elements.Button;

import static helpers.Artist.*;
import static loader.ImageLoader.getImage;

public class PlanetInterface extends UserInterface {

    private static final String PLANET_BACKGROUND = "background_ship";

    private static final float PLANET_X = 50;
    private static final float PLANET_Y = 50;
    private static final int PLANET_WIDTH = 250;
    private static final int PLANET_HEIGHT = 250;

    private static final float INFO_X = 400;
    private static final float INFO_Y = 50;
    private static final int INFO_WIDTH = 800;
    private static final int INFO_HEIGHT = 850;
    private static final int BORDER = 5;
    private static final int SPACING = 200;
    private static final int SPACE = 40;

    private static final int ENEMY_SIZE = 50;

    private Planet targetPlanet;

    public PlanetInterface(Planet targetPlanet) {
        super(getImage("background1"));
        this.targetPlanet = targetPlanet;
        buttons.add(new Button("START!", "start", PLANET_X, 400, BUTTON_WIDTH/2, BUTTON_HEIGHT));
        buttons.add(new Button("Back", "back", PLANET_X, 460, BUTTON_WIDTH/2, BUTTON_HEIGHT));
    }

    public void update() {
        updateButtons();
    }

    @Override
    public void render() {
        //background
        drawQuadTex(background, 0, 0, Game.SCREEN_WIDTH, Game.SCREEN_HEIGHT);

        //draw name
        drawText(PLANET_X, PLANET_Y - Fonts.BUTTON.font.getHeight(), targetPlanet.getName(), Fonts.BUTTON.font, Color.white);

        //draw planet
        drawQuadTex(getImage(PLANET_BACKGROUND), PLANET_X, PLANET_Y, PLANET_WIDTH, PLANET_HEIGHT);
        drawRectangle(PLANET_X, PLANET_Y, PLANET_WIDTH, PLANET_HEIGHT, Color.white, 2);
        drawQuadTex(getImage(targetPlanet.getTexture()), PLANET_X, PLANET_Y, PLANET_WIDTH, PLANET_HEIGHT);

        //draw info
        drawQuadTex(getImage("background0"), INFO_X, INFO_Y, INFO_WIDTH, INFO_HEIGHT);
        drawRectangle(INFO_X, INFO_Y, INFO_WIDTH, INFO_HEIGHT, Color.white, 3);

        //draw name
        float infoX = INFO_X + BORDER;
        drawText(infoX, INFO_Y + BORDER, "Planet Name:", Fonts.SUBTITLE.font, Color.green);
        drawText(infoX + SPACING, INFO_Y + BORDER, targetPlanet.getName(), Fonts.PLANET.font, Color.white);

        //draw drop probability
        drawText(infoX, INFO_Y + BORDER + SPACE, "Drop Rate: ", Fonts.SUBTITLE.font, Color.green);
        drawText(infoX + SPACING - SPACE, INFO_Y + 2*BORDER + SPACE, targetPlanet.getDropProbability() + "%", Fonts.INFO.font, Color.white);
        drawBar(infoX + SPACING + SPACE, INFO_Y + 2*BORDER + SPACE, 200, 15, targetPlanet.getDropProbability(), 100, 100, Color.white, Color.gray, Color.yellow);

        //draw resources
        drawText(infoX, INFO_Y + BORDER + 2*SPACE, "Resources of the planet:", Fonts.SUBTITLE.font, Color.green);
        float startingY = INFO_Y + BORDER + 3*SPACE;
        for (PlanetResource r : targetPlanet.getResources()) {
            drawText(infoX, startingY, r.getItemType().getName(), Fonts.PLANET.font, Color.white);
            drawQuadTex(getImage(r.getItemType().getTexture()), infoX + SPACING, startingY, 30, 30);
            drawText(infoX + SPACING + 60, startingY, r.getProbability() + "%", Fonts.PLANET.font, Color.white);
            startingY += SPACE;
        }

        //draw defense
        drawText(infoX, startingY, "Defense of the planet:", Fonts.SUBTITLE.font, Color.green);
        drawText(infoX + SPACING + 2*SPACE, startingY + 3, targetPlanet.getLevel().getEnemyNumber() + "enemies", Fonts.INFO.font, Color.white);
        startingY += SPACE;
        float enemyX = infoX;
        int i = 0;
        for (EnemyType t : targetPlanet.getLevel().getEnemyTypes()) {
            i++;
            drawQuadTex(getImage(t.getTexture()), enemyX, startingY, ENEMY_SIZE, ENEMY_SIZE);
            drawText(enemyX, startingY + ENEMY_SIZE + BORDER, t.getName(), Fonts.PLANET.font, Color.white);
            enemyX += 4*ENEMY_SIZE + SPACE;
            if (i % 3 == 0 && i != targetPlanet.getLevel().getEnemyTypes().size()) {
                startingY += SPACE + ENEMY_SIZE;
                enemyX = infoX;
            }
        }

        startingY +=  SPACE + ENEMY_SIZE;

        //draw description
        drawText(infoX, startingY, "Description:", Fonts.SUBTITLE.font, Color.green);
        float textX = infoX;
        float textY = startingY + SPACE;
        for (String word : targetPlanet.getLevel().getDescriptionWords()) {
            int width = Fonts.DEFAULT.font.getWidth(word + " ");
            if (textX + width > INFO_X + INFO_WIDTH) {
                textX = infoX;
                textY += Fonts.DEFAULT.size + 5;
            }
            drawText(textX, textY, word + " ", Fonts.DEFAULT.font, Color.white);
            textX += width;
        }

        //draw button
       for (Button b : buttons)
           b.render();
    }
}
