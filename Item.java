public class Item implements Comparable<Item> {
    public final String name;
    public final float price;

    public Item(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(final Item other) {
        return this.name.compareTo(other.name);
    }
}
