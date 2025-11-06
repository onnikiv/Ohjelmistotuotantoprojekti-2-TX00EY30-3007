package assignment3.model;

public class CartItem {

    private final int itemName;
    private final double itemCost;

    public CartItem(int itemName, double itemCost) {
        this.itemName = itemName;
        this.itemCost = itemCost;
    }

    public double getCost() {
        return itemCost;
    }

    public int getName() {
        return itemName;
    }
}
