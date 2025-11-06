package assignment3.model;

import java.util.ArrayList;

public class ShoppingCart {

    private double totalCost;
    private final ArrayList<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
        this.totalCost = 0;
    }

    public void addCartItem(int count, float pricePer) {
        double cost = count * pricePer;
        totalCost += cost;
    }

    public double getTotalCost() {
        return this.totalCost;
    }
}
