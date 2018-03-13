package assets;

public enum ItemType implements java.io.Serializable {

    IRON("iron", 35, 35, "iron"),
    COPPER("copper", 35, 35, "copper"),
    TITANIUM("titanium", 35, 35, "titanium"),
    GOLD("gold", 35, 35, "gold"),
    COAL("coal", 35, 35, "coal"),
    SULFUR("sulfur", 35, 35, "sulfur"),
    FUEL("fuel", 50, 50, 200, "fuel"),
    BULLET("bullet", 10, 10, "bullet"),
    METAL("metal", 40, 40, "metal"),
    CIRCUIT("circuit", 40, 40, "circuit"),
    ARMOUR("armour", 40, 40, "armour"),
    GUNPOWDER("gunpowder", 40, 40, "gunpowder"),
    MISSILE("missile", 40, 40, "missile"),
    TURBO("turbo", 80, 80, "coal");

    private String name;
    private int width;
    private int height;
    private String texture;
    private int unit;

    ItemType(String name, int width, int height, int unit, String texture) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.unit = unit;
    }

    ItemType(String name, int width, int height, String texture) {
        this.name = name;
        this.width = width;
        this.height = height;
        this.texture = texture;
        this.unit = 0;
    }

    /**
     *
     * @param itemName - name of the item we want to convert
     * @return ItemType corresponding to the given string
     */
    public static ItemType stringToItem(String itemName) {
        for (ItemType itemType : ItemType.values()) {
            if (itemType.name.toUpperCase().equals(itemName))
                return itemType;
        }
        return null;
    }

    public String getTexture() {
        return texture;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public String getName() {
        return name;
    }

    public int getUnit() {
        return unit;
    }
}
