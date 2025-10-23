package assignment1;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ShoppingCart shoppingCart = new ShoppingCart();
            System.out.println("Select a language: en, fi, ja, sv");
            String language = sc.nextLine().toLowerCase();
            System.out.println("Select a country: US, FI, JP, SE");
            String country = sc.nextLine().toUpperCase();
            Locale myLocale = new Locale(language, country);
            ResourceBundle bundle = ResourceBundle.getBundle("localization.MessagesBundle", myLocale);

            System.out.println(bundle.getString("count"));
            int itemCount = sc.nextInt();
            System.out.println(bundle.getString("price"));
            float itemPrice = sc.nextFloat();

            shoppingCart.addCartItem(itemCount, itemPrice);
            System.out.println(bundle.getString("total") + shoppingCart.getTotalCost());

        }
    }
}
