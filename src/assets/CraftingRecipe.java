package assets;

import java.util.List;
import java.util.Map;

public class CraftingRecipe {

    private Item targetItem;
    private List<Resource> resources;

    /**
     * Crafting recipe which describes what resources(items and quantities) are needed to craft a target item
     * @param targetItem the item which can be crafted
     * @param resources the list of resources needed to craft that item
     */
    public CraftingRecipe(Item targetItem, List<Resource> resources) {
        this.targetItem = targetItem;
        this.resources = resources;
    }

    /**
     * Checks whether the current instance is craftable, given an inventory
     * @param inventory the inventory which contains the available items and their quantities
     * @return true if inventory contains all items with the required quantities, false otherwise
     */
    public boolean isCraftable(Inventory inventory) {
        for (Resource r : resources) {
            Map<Item, Integer> map = inventory.getItems();
            if (!map.containsKey(r.getItem()))
                return false;
            if (!r.isEnough(map.get(r.getItem())))
                return false;
        }
        return true;
    }

    /**
     * Uses resources from the inventory in order to craft a target item, which is added to the inventory
     * @param inventory the inventory from which the resources will be used
     */
    public void craft(Inventory inventory) {
        for (Resource r : resources) {
            inventory.use(r.getItem(), r.getAmount());
        }
        inventory.add(targetItem, 1);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(targetItem.getType().getName());
        for (Resource r : resources)
            result.append(r.toString());
        return result.toString();
    }

    public Item getTargetItem() {
        return targetItem;
    }

    public List<Resource> getResources() {
        return resources;
    }
}
