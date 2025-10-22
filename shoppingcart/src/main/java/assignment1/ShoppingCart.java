package assignment1;

import java.util.ArrayList;

public class ShoppingCart {

    private int totalCost = 0;
    private final ArrayList<CartItem> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addCartItem(int selection) {

        switch (selection) {
            case 1 -> {
                this.cartItems.add(new CartItem("Banana", 0.5));
            }
            case 2 -> {
                this.cartItems.add(new CartItem("Milk", 1));
            }
            case 3 -> {
                this.cartItems.add(new CartItem("Sausage", 4));
            }
            case 4 -> {
                this.cartItems.add(new CartItem("Bread", 2));
            }
            case 5 -> {
                this.cartItems.add(new CartItem("Porridge", 1.5));
            }
            default ->
                throw new AssertionError();
        }
    }

    public void removeCartItem(String itemName) {
        for (CartItem item : cartItems) {
            if (item.getName().equals(itemName)) {
                cartItems.remove(item);
            }
        }
    }

    public double getTotalCost() {
        for (CartItem item : cartItems) {
            this.totalCost += item.getCost();
        }
        return this.totalCost;
    }
}
