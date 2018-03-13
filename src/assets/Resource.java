package assets;

public class Resource {

    private Item item;
    private int amount;

    /**
     * Constructor used to create a resource containing the item and its amount.
     * Used when creating a crafting recipe
     * @param item the item we want to refer to
     * @param amount the quantity of the item
     */
    public Resource(Item item, int amount) {
        this.item = item;
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public boolean isEnough(int possessedAmount) {
        return possessedAmount >= amount;
    }

    public int getAmount() {
        return amount;
    }

    public String toString() {
        return "{"+item.getType().getName()+" - " +amount+"}";
    }
}
