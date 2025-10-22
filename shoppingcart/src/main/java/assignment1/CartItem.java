package assignment1;

public class CartItem {

    private final String itemName;
    private final double itemCost;

    public CartItem(String itemName, double itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public double getCost() {
        return itemCost;
    }

    public String getName() {
        return itemName;
    }
}
