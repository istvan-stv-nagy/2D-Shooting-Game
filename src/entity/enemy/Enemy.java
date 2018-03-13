package entity.enemy;

import entity.Entity;
import game.Game;
import org.newdawn.slick.Color;

import static helpers.Artist.drawBar;

public class Enemy extends Entity {

    private static final int HEALTH_BAR_WIDTH = 100;
    private static final int HEALTH_BAR_HEIGHT = 5;

    protected EnemyType type;
    protected int health;
    protected boolean alive;
    protected boolean killed;

    public Enemy(float x, float y, EnemyType type) {
        super(x, y, type.getWidth(), type.getHeight(), type.getVelX(), type.getVelY(), type.getTexture());
        this.type = type;
        this.alive = true;
        this.health = type.getHealth();
        this.killed = false;
    }

    public boolean isInRange() {
        return !(y + height > Game.SCREEN_HEIGHT);
    }

    public boolean isAlive() {
        return alive;
    }

    public void damage(int amount) {
        if (health > amount) {
            health -= amount;
        } else {
            health = 0;
            die();
        }

    }

    public void die() {
        alive = false;
    }

    public EnemyType getType() {
        return type;
    }

    public void render() {
        super.render();
        drawBar(x + (width - HEALTH_BAR_WIDTH)/2, y - HEALTH_BAR_HEIGHT, HEALTH_BAR_WIDTH, HEALTH_BAR_HEIGHT, health, type.getHealth(), type.getHealth(), Color.black, Color.gray, Color.green);
    }

    public boolean isKilled() {
        return killed;
    }

    public void kill() {
        this.killed = true;
    }
}
