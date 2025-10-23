package assignment1;

import java.util.ArrayList;

public class ShoppingCart {

    private int totalCost = 0;
    private final ArrayList<CartItem> cartItems;
    private int index = 1;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addCartItem(int count, float pricePer) {
        int cost = (int) (count * pricePer);
        this.cartItems.add(new CartItem(index, cost));
        index++;
    }

    // public void removeCartItem(String itemName) {
    //     for (CartItem item : cartItems) {
    //         if (item.getName().equals(itemName)) {
    //             cartItems.remove(item);
    //         }
    //     }
    // }
    public double getTotalCost() {
        for (CartItem item : cartItems) {
            this.totalCost += item.getCost();
        }
        return this.totalCost;
    }
}
