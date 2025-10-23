package assignment1;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class ShoppingCartTest {

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
}
