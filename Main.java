public class Main {
    public static void main(String[] args) {
        final Item A = new Item("A", 4.99f); // get second for one dollar
        final Item B = new Item("B", 9.99f); // get fifth for free
        final Item C = new Item("C", 19.99f); // no discount in this item
        final Rule[] rules = { new Rule(A, 2, 5.99f), new Rule(B, 5, 39.99f) };

        final Checkout co = new Checkout(rules);

        for (int i = 1; i <= 3; i++) {
            co.addItem(A);
        }
        for (int i = 1; i <= 13; i++) {
            co.addItem(B);
        }
        co.addItem(C);

        System.out.println(co.evaluateTotal());
    }
}
