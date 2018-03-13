package entity.ship;

public enum ShipType implements java.io.Serializable {

    BASIC("Basic",128, 128, 10, 10, 100, 50, 1000, "ship1"),
    RAIDER("Raider", 100, 90, 12, 12, 110, 25, 500,"ship2"),
    CRUISER("Cruiser", 128, 140, 20, 15, 180, 75, 750, "ship3");

    private String brand;
    private int width;
    private int height;
    private int velX, velY;
    private int maxHealth;
    private int maxArmour;
    private int fuelCapacity;
    private String texture;

    ShipType(String brand, int width, int height, int velX, int velY, int maxHealth, int maxArmour, int fuelCapacity, String texture) {
        this.brand = brand;
        this.width = width;
        this.height = height;
        this.velX = velX;
        this.velY = velY;
        this.maxHealth = maxHealth;
        this.maxArmour = maxArmour;
        this.fuelCapacity = fuelCapacity;
        this.texture = texture;
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

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getMaxArmour() {
        return maxArmour;
    }

    public String getBrand() {
        return brand;
    }

    public String getTexture() {
        return texture;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }
}
