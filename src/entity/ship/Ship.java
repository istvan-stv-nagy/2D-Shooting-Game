package entity.ship;

import assets.Inventory;
import assets.Item;
import entity.Entity;
import entity.projectile.Projectile;
import game.Game;
import org.lwjgl.input.Keyboard;

import java.util.List;

import static org.lwjgl.input.Keyboard.*;

public class Ship extends Entity implements java.io.Serializable {

    public static final int MAX_HEALTH = 300;
    public static final int MAX_VEL_X = 30;
    public static final int MAX_VEL_Y = 40;
    public static final int MAX_FIRING_SPEED = 50;
    public static final int MAX_ARMOUR = 200;
    public static final int MAX_FUEL_CAPACITY = 1500;

    private static final int FUEL_TIME = 100;

    private ShipType type;
    private String model;
    private Inventory inventory;
    private Gun gun;
    private int health;
    private int armour;
    private boolean selected;
    private int fuel;

    private long lastFuelTime;
    private int experience;

    public Ship(float x, float y, ShipType type, String model) {
        super(x, y, type.getWidth(), type.getHeight(), type.getVelX(), type.getVelY(), type.getTexture());
        this.type = type;
        this.model = model;
        inventory = new Inventory();
        this.health = type.getMaxHealth();
        this.armour = type.getMaxArmour();
        fuel = type.getFuelCapacity();
        selected = false;
        lastFuelTime = 0;
        gun = new Gun(this);
        experience = 0;
    }

    public void reset() {
        x = (Game.SCREEN_WIDTH - ShipType.BASIC.getWidth()) / 2;
        y = Game.SCREEN_HEIGHT - ShipType.BASIC.getHeight();
        gun.reset();
        experience = 0;
    }

    @Override
    public void update() {
        if (fuel > 0) {
            move();
            consumeFuel();
            shoot();
        }
        gun.update();
    }

    @Override
    public void render() {
        super.render();
        gun.render();
    }

    private void consumeFuel() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFuelTime >= FUEL_TIME) {
            lastFuelTime = currentTime;
            fuel--;
        }
    }

    private void move() {
        if (x > 0) {
            if (Keyboard.isKeyDown(KEY_LEFT)) {
                x -= velX;
            }
        } else x = 0;

        if (x + width < Game.SCREEN_WIDTH) {
            if (Keyboard.isKeyDown(KEY_RIGHT)) {
                x += velX;
            }
        } else x = Game.SCREEN_WIDTH - width;

        if (y > 0) {
            if (Keyboard.isKeyDown(KEY_UP)) {
                y -= velY;
            }
        } else y = 0;

        if (y + height < Game.SCREEN_HEIGHT) {
            if (Keyboard.isKeyDown(KEY_DOWN)) {
                y += velY;
            }
        } else y = Game.SCREEN_HEIGHT - height;
    }

    private void shoot() {
        gun.shoot();
    }

    public void damage(int amount) {
        if (health > amount) {
            health -= amount;
        } else {
            health = 0;
        }
    }

    public void hit(int amount) {
        if (armour >= amount) {
            armour -= amount;
        } else {
            damage(amount - armour);
            armour = 0;
        }
    }

    public void addExperience(int amount) {
        this.experience += amount;
    }

    public int getExperience() {
        int result = experience;
        experience = 0;
        return result;
    }

    public void fillTank(int amount) {
        if (fuel == type.getFuelCapacity())
            return;
        fuel += amount;
        if (fuel > type.getFuelCapacity())
            fuel = type.getFuelCapacity();
    }

    public void collectItem(Item item, int amount) {
        inventory.add(item, amount);
    }

    public List<Projectile> getProjectiles() {
        return gun.getProjectiles();
    }

    public int getHealth() {
        return health;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ShipType getType() {
        return type;
    }

    public int getFiringSpeed() {
        return gun.getFiringSpeed();
    }

    public boolean hasArmour() {
        return armour > 0;
    }

    public int getArmour() {
        return armour;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getModel() {
        return model;
    }

    public int getFuel() {
        return fuel;
    }
}
