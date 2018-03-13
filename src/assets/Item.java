package assets;

import java.util.Objects;

public class Item implements java.io.Serializable {

    private ItemType type;

    public Item(ItemType type) {
        this.type = type;
    }

    public ItemType getType() {
        return type;
    }

    /**
     *
     * @param o object we want to check
     * @return true if the two items have the same type, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return type == item.type;
    }

    @Override
    public int hashCode() {

        return Objects.hash(type);
    }
}
