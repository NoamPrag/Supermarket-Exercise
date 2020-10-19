public class Item implements Comparable<Item> {
    public final String name;
    public final int price;

    public Item(final String name, final int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int compareTo(final Item other) {
        return name.compareTo(other.name);
    }
}
