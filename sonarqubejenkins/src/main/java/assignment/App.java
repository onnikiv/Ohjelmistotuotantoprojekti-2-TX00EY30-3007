package assignment;

import java.util.Scanner;
import java.util.logging.Logger;

public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {

        logger.info("Hello and welcome!");

        int count;

        try (Scanner scanner = new Scanner(System.in)) {
            logger.info("How many numbers to display in the loops? ");
            count = scanner.nextInt();
        }

        displayNumbers(count);
    }

    public static void displayNumbers(int count) {
        for (int i = 1; i <= count; i++) {
            final int current = i;
            logger.info(() -> "i = " + current);
        }
    }
}
