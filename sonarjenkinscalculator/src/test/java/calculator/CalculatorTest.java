package calculator;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    public void testAdd() {
        assertEquals(5.0, calculator.add(2.0, 3.0), 0.001);
    }

    @Test
    public void testSubtract() {
        assertEquals(1.0, calculator.subtract(3.0, 2.0), 0.001);
    }

    @Test
    public void testMultiply() {
        assertEquals(6.0, Calculator.multiply(2.0, 3.0), 0.001);
    }

    @Test
    public void testDivide() {
        assertEquals(2.0, calculator.divide(6.0, 3.0), 0.001);
    }

    @Test
    public void testCalculateAdd() {
        assertEquals(8.0, calculator.calculate(5.0, 3.0, "+"), 0.001);
    }

    @Test
    public void testCalculateSubtract() {
        assertEquals(2.0, calculator.calculate(5.0, 3.0, "-"), 0.001);
    }

    @Test
    public void testCalculateMultiply() {
        assertEquals(15.0, calculator.calculate(5.0, 3.0, "*"), 0.001);
    }

    @Test
    public void testCalculateDivide() {
        assertEquals(2.5, calculator.calculate(5.0, 2.0, "/"), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateUnknownOperation() {
        calculator.calculate(5.0, 3.0, "^");
    }
}
