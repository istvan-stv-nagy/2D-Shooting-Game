package entity.projectile;

import java.util.Random;

public enum ProjectileType implements java.io.Serializable {

    BASIC(20, 20, -20, 1, "projectile1"),
    BASIC2(20, 20, -20, 1, "projectile2"),
    BASIC3(20, 20, -20, 1, "projectile3"),
    ENEMY(30,30,10,5,"fire");

    private int width;
    private int height;
    private int speedY;
    private int damage;
    private String texture;

    ProjectileType(int width, int height, int speedY, int damage, String texture) {
        this.width = width;
        this.height = height;
        this.damage = damage;
        this.speedY = speedY;
        this.texture = texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeedY() {
        return speedY;
    }

    public String getTexture() {
        return texture;
    }

    public static ProjectileType getRandomProjectile() {
        int index = Math.abs(new Random().nextInt()) % 3;
        switch (index) {
            case 0 : return BASIC;
            case 1 : return BASIC2;
            case 2 : return BASIC3;
        }
        return BASIC;
    }
}
