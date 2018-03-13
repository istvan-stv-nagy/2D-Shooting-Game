package entity.map;

import assets.ItemType;

public class PlanetResource implements java.io.Serializable {

    private ItemType itemType;
    private int lowerProbability;
    private int upperProbability;

    public PlanetResource(ItemType itemType, int lowerProbability, int upperProbability) {
        this.itemType = itemType;
        this.lowerProbability = lowerProbability;
        this.upperProbability = upperProbability;
    }

    public boolean isInRange(int value) {
        return (lowerProbability <= value) && (value <= upperProbability);
    }

    public ItemType getItemType() {
        return itemType;
    }

    public int getProbability() {
        return upperProbability - lowerProbability + 1;
    }

}
