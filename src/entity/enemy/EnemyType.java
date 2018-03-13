package entity.enemy;

import entity.explosion.ExplosionType;

public enum EnemyType implements java.io.Serializable{

    BASIC("BASIC",100, 100, 0, 2, 5, 5, 5, ExplosionType.BASIC, "enemy"),
    DIVIDER("DIVIDER",200, 200, 0, 1, 15, 10, 10, ExplosionType.FIRE, "enemy_divider"),
    ZAGGER("ZAGGER",150, 150, 2, 1, 20, 15, 25, ExplosionType.BLACKYELLOW, "enemy_zagger"),
    SHIELDED("SHIELDED",100, 100, 0, 1, 20, 7, 50, ExplosionType.ORANGE, "enemy_shielded"),
    SHOOTER("SHOOTER",110, 110, 0, 1, 25, 15, 75, ExplosionType.FIRE, "enemy_shooter"),
    FOLLOWER("FOLLOWER",80, 80, 3, 3, 10, 5, 100, ExplosionType.FIRE, "enemy_follower");

    private String name;
    private int width;
    private int height;
    private int velX, velY;
    private int damage;
    private int health;
    private int exp;
    private String texture;
    private ExplosionType explosionType;

    EnemyType(String name, int width, int height, int velX, int velY, int damage, int health, int exp, ExplosionType explosionType, String texture) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.damage = damage;
        this.health = health;
        this.exp = exp;
        this.explosionType = explosionType;
        this.texture = texture;
    }

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVelX() {
        return velX;
    }

    public int getVelY() {
        return velY;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getExp() {
        return exp;
    }

    public String getTexture() {
        return texture;
    }

    public ExplosionType getExplosionType() {
        return explosionType;
    }
}
