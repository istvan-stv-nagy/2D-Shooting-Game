package entity.projectile;

import entity.Entity;

public class Projectile extends Entity implements java.io.Serializable {

    private ProjectileType type;
    private float speedX;
    private boolean alive;

    public Projectile(float x, float y, float speedX, ProjectileType type) {
        super(x, y, type.getWidth(), type.getHeight(), 0, type.getSpeedY(), type.getTexture());
        this.type = type;
        this.alive = true;
        this.speedX = speedX;
    }

    @Override
    public void update() {
        y += type.getSpeedY();
        x += speedX;
    }

    public boolean isInRange() {
        return y >= 0;
    }

    public boolean isAlive() {
        return alive;
    }

    public void die() {
        alive = false;
    }

    public ProjectileType getType() {
        return type;
    }
}
