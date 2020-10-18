import java.util.ArrayList;

public class Checkout {

    protected final Rule[] pricingRules;

    protected ArrayList<Item> items = new ArrayList<>();

    public Checkout(final Rule[] rules) {
        pricingRules = rules;
    }

    // Adding an item to the checkout. Inserting the item to it's sorted index in
    // the list, for making the evaluation easier later. The items are sorted by
    // their names.
    public void addItem(final Item newItem) {
        for (final Item item : items) {
            if (newItem.compareTo(item) <= 0) {
                items.add(items.indexOf(item), newItem);
                return;
            }
        }
        items.add(newItem);
    }

    /*
     * Evaluate a single item by the quantity and discounts. First search for the
     * number of items that are not included in the discount, and multiply by item's
     * price. Then find the number of items that are included in the discount, and
     * multiply by the corresponding price. If the checkout doesn't have a discount
     * for this item, return the quantity times the price of the item.
     */
    private float evaluateItem(final Item item, final int quantity) {
        for (final Rule rule : pricingRules) {
            if (rule.item.compareTo(item) == 0) {
                final int quantityNoDiscount = quantity % rule.quantity;
                final int quantityOfDiscounts = (quantity - quantityNoDiscount) / rule.quantity;
                return quantityNoDiscount * item.price + quantityOfDiscounts * rule.price;
            }
        }
        return quantity * item.price;
    }

    // Iterating over the items and evaluating the total price of the purchase.
    public float evaluateTotal() {
        // Variable to count the quantity of type of item.
        int itemCounter = 0;

        float totalPrice = 0;

        // initializing the object in order for the final evaluation to not throw an
        // error.
        Item currentItem = new Item("", 0);
        // initializing the second object because the first one was as well.
        Item prevItem = new Item("", 0);

        // the size of the array is calculated once before the loop, instead of every
        // iteration, to increase performance.
        final int totalQuantity = items.size();
        for (int i = 0; i < totalQuantity; i++) {

            // We can't compare the first item to items before to we only increment the
            // counter and continue to the next item.
            if (i == 0) {
                itemCounter++;
                continue;
            }

            // Getting the current and previous items to compare.
            currentItem = items.get(i);
            prevItem = items.get(i - 1);

            // If the current item is the same as the previous, the counter is incremented.
            // Else, the price of the previous item is added to the total, and the counter
            // is reset to one.
            if (currentItem.compareTo(prevItem) == 0) {
                itemCounter++;
            } else {
                totalPrice += evaluateItem(prevItem, itemCounter);
                itemCounter = 1;
            }
        }
        // Evaluating the price of the last item from the list.
        totalPrice += evaluateItem(currentItem, itemCounter);

        // Finally returning the total price of the purchase.
        return totalPrice;
    }
}
