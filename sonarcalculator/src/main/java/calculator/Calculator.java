package calculator;

import java.util.Scanner;
import java.util.logging.Logger;

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

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Calculator calculator = new Calculator();
            Logger logger = Logger.getLogger(Calculator.class.getName());

            logger.info("Calculator - onnikiv");
            logger.info("Choose operation: +, -, *, /");
            String operation = scanner.next();

            logger.info("Enter first number: ");
            double firstNumber = scanner.nextDouble();

            logger.info("Enter second number: ");
            double secondNumber = scanner.nextDouble();
            try {
                double result = calculator.calculate(firstNumber, secondNumber, operation);
                logger.info(() -> String.format("Result: %.2f", result));
            } catch (IllegalArgumentException e) {
                logger.info(() -> String.format("Error: %s", e.getMessage()));
            }
        }
    }

}
