package assignment2;

import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

import assignment2.model.ShoppingCart;

public class ShoppingCartGuiTest {

    private ShoppingCart cart;

    @Before
    public void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    public void initialTotalIsZero() {
        assertEquals("Initial total should be 0", 0.0, cart.getTotalCost(), 0.0001);
    }

    @Test
    public void addSingleItemUpdatesTotal() {
        cart.addCartItem(2, 5); // 2 items * 5 = 10
        assertEquals("Total after adding 2 items at price 5 should be 10", 10.0, cart.getTotalCost(), 0.0001);
    }

    @Test
    public void addMultipleItemsCumulativeTotal() {
        cart.addCartItem(1, 3); // 1 * 3 = 3
        cart.addCartItem(3, 2); // 3 * 2 = 6
        assertEquals("Cumulative total should equal sum of integer costs", 9.0, cart.getTotalCost(), 0.0001);
    }

    @Test
    public void addingZeroCountDoesNotChangeTotal() {
        cart.addCartItem(0, 10);
        assertEquals("Adding zero count should leave total 0", 0.0, cart.getTotalCost(), 0.0001);
    }

    @Test
    public void testAllLanguageResourceBundles() {
        // Test that all four language bundles load and contain required keys
        Locale[] locales = {
            new Locale("en", "US"), // English
            new Locale("fi", "FI"), // Finnish
            new Locale("ja", "JP"), // Japanese
            new Locale("sv", "SE") // Swedish
        };

        for (Locale locale : locales) {
            ResourceBundle bundle = ResourceBundle.getBundle("localization.MessagesBundle", locale);
            assertNotNull("Resource bundle for " + locale + " should load", bundle);
            assertNotNull("Bundle should contain 'count' key", bundle.getString("count"));
            assertNotNull("Bundle should contain 'price' key", bundle.getString("price"));
            assertNotNull("Bundle should contain 'total' key", bundle.getString("total"));
            assertNotNull("Bundle should contain 'continue' key", bundle.getString("continue"));
            assertNotNull("Bundle should contain 'exit' key", bundle.getString("exit"));
        }
    }
}
