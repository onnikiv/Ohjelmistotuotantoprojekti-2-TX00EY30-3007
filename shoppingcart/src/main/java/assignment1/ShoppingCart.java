package assignment1;

import java.util.ArrayList;

public class ShoppingCart {

    private int totalCost;
    private final ArrayList<CartItem> cartItems;
    private int index = 1;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addCartItem(int count, float pricePer) {
        int cost = (int) (count * pricePer);
        totalCost += cost;
        this.cartItems.add(new CartItem(index, cost));
        index++;
    }

    public double getTotalCost() {
        return this.totalCost;
    }
}
