package calculator;

public class Calculator {

    public double calculate(double a, double b, String operation) {
        switch (operation) {
            case "+" -> {
                return add(a, b);
            }
            case "-" -> {
                return subtract(a, b);
            }
            case "*" -> {
                return multiply(a, b);
            }
            case "/" -> {
                return divide(a, b);
            }
            default ->
                throw new IllegalArgumentException("Unknown operation: " + operation);
        }
    }

    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        return a / b;
    }
}
