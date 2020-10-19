public class Main {
    public static void main(final String[] args) {
        final Item a = new Item("A", 499); // get second for one dollar
        final Item b = new Item("B", 999); // get fifth for free
        final Item c = new Item("C", 1999); // no discount in this item
        final Rule[] rules = { new Rule(a, 2, 599), new Rule(b, 5, 3999) };

        final Checkout co = new Checkout(rules);

        for (int i = 0; i <= 2; i++) {
            co.addItem(a);
        }
        for (int i = 0; i <= 12; i++) {
            co.addItem(b);
        }
        co.addItem(c);

        System.out.println(co.evaluateTotal());
    }
}
