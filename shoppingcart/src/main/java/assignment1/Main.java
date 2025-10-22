package assignment1;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ShoppingCart shoppingCart = new ShoppingCart();

            Locale myLocale = new Locale("en", "US");
            ResourceBundle bundle = ResourceBundle.getBundle("localization.MessagesBundle", myLocale);

            System.out.println(bundle.getString("wish"));
            System.out.println("Add items to shoppingcart: ");
            System.out.println("[1] Banana, 0.5e");
            System.out.println("[2] Milk, 1e");
            System.out.println("[3] Sausage, 4e");
            System.out.println("[4] Bread, 2e");
            System.out.println("[5] Porridge, 1.5e");

            shoppingCart.addCartItem(sc.nextInt());
            System.out.println(shoppingCart.getTotalCost());

        }
    }
}
