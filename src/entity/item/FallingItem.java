package entity.item;

import assets.Item;
import entity.Entity;
import game.Game;

public class FallingItem extends Entity {

    private Item item;
    private boolean alive;

    public FallingItem(float x, float y, float velX, float velY, Item item) {
        super(x, y, item.getType().getWidth(), item.getType().getHeight(), velX, velY, item.getType().getTexture());
        this.item = item;
        alive = true;
    }

    public void die() {
        this.alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean isInRange() {
        return (y + height < Game.SCREEN_HEIGHT);
    }

    public Item getItem() {
        return item;
    }
}
