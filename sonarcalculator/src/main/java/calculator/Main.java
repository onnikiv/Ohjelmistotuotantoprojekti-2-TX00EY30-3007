package calculator;

import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Calculator calculator = new Calculator();
            Logger logger = Logger.getLogger(Main.class.getName());

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
