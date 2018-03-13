package assets;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements java.io.Serializable {

    /**
     * Key of the map are the existing items, and the value for each key is the quantity
     */
    private Map<Item, Integer> items;

    public Inventory() {
        items = new HashMap<>();
    }

    /**
     * Adds an item to the inventory, if the item does not exist in the inventory
     * Otherwise, it adds the amount to the value for the given item
     * @param item the item that will be added
     * @param amount the amount that it will be added
     */
    public void add(Item item, int amount) {
        if (items.containsKey(item)) {
            items.put(item, items.get(item) + amount);
        } else {
            items.put(item, amount);
        }
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    /**
     * Copies the content of an inventory to the current instance
     * @param cargo the inventory whose content will be copied to the current instance
     */
    public void load(Inventory cargo) {
        for (Item item : cargo.getItems().keySet()) {
            add(item, cargo.getItems().get(item));
        }
        cargo.empty();
    }

    /**
     * Use a given amount of an item from the inventory.
     * Only succeeds if the item is present in the inventory and its quantity is greater or equal to the given amount
     * @param item the item that will be used
     * @param amount the amount that will be used
     */
    public void use(Item item, int amount) {
        if (items.containsKey(item)) {
            if (items.get(item) >= amount)
                items.put(item, items.get(item) - amount);
        }
    }

    /**
     * Throw away the current content of the inventory and generate an empty inventory
     */
    private void empty() {
        items = new HashMap<>();
    }

    public boolean hasItem(ItemType type, int amount) {
        Item item = new Item(type);
        return items.containsKey(item) && items.get(item) >= amount;
    }

    public int getAmount(Item item) {
        if (!items.containsKey(item))
            return 0;
        return items.get(item);
    }
}
