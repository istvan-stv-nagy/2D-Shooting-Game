package entity.map;

import assets.ItemType;
import entity.Entity;
import game.Level;
import org.newdawn.slick.Color;
import ui.Fonts;

import java.util.ArrayList;
import java.util.List;

import static helpers.Artist.drawQuadTex;
import static helpers.Artist.drawText;
import static loader.ImageLoader.getImage;

public class Planet extends Entity implements java.io.Serializable {

    private Level level;
    private String name;
    private int dropProbability;
    private boolean canBeVisited;
    private boolean finished;
    private List<PlanetResource> resources;

    public Planet(Level level, float x, float y, int width, int height, String texture, String name, int dropProbability) {
        super(x, y, width, height, 0, 0, texture);
        resources = new ArrayList<>();
        this.level = level;
        this.name = name;
        canBeVisited = false;
        finished = false;
        this.dropProbability = dropProbability;
    }

    public void add(ItemType itemType, int lowerPercentage, int upperPercentage) {
        resources.add(new PlanetResource(itemType, lowerPercentage, upperPercentage));
    }

    public float getCenterX() {
        return x+width/2;
    }

    public float getCenterY() {
        return y+height/2;
    }

    @Override
    public void render() {
        if (canBeVisited)
            super.render();
        else
            drawQuadTex(getImage("planet0"), x, y, width, height);
        if (isMouseOver() && !finished) {
            drawText(x, y-15, name, Fonts.INFO.font, Color.white);
        }
        else if (finished) {
            drawText(x, y-15, name, Fonts.INFO.font, Color.yellow);
        }
    }

    public boolean canBeExplored() {
        return canBeVisited;
    }

    public int getLevelID() {
        return level.getId();
    }

    public Level getLevel() {
        return level;
    }

    public void setCanBeVisited(boolean canBeVisited) {
        this.canBeVisited = canBeVisited;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getDropProbability() {
        return dropProbability;
    }

    public List<PlanetResource> getResources() {
        return resources;
    }

    public String getName() {
        return name;
    }
}
