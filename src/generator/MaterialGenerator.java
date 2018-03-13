package generator;

import assets.ItemType;
import entity.map.PlanetResource;

import java.util.*;

public class MaterialGenerator {

    private int dropChance;
    private List<PlanetResource> probabilities;

    public MaterialGenerator(int dropChance, List<PlanetResource> probabilities) {
        this.dropChance = dropChance;
        this.probabilities = probabilities;
    }

    public ItemType generate() {
        Random rand = new Random();
        if (dropChance >= Math.abs(rand.nextInt()) % 100) {
            int value = (Math.abs(rand.nextInt()) % 100) + 1;
            for (PlanetResource resource : probabilities) {
                if (resource.isInRange(value))
                    return resource.getItemType();
            }
        }
        return null;
    }

}
