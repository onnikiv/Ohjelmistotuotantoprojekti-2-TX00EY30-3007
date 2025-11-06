package assignment3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {

    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            // connect if necessary
            try {
                // Use shopping_cart_db with auto-create and ensure schema exists
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/shopping_cart_db?user=root&password=root&createDatabaseIfNotExist=true");
                System.out.println("Connection successful.");
                ensureSchema(conn);
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
                return null;
            }
        }
        return conn;
    }

    private static void ensureSchema(Connection connection) {
        try (Statement st = connection.createStatement()) {
            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS items (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      price DOUBLE NOT NULL,
                      quantity INT NOT NULL
                    )
                    """);

            st.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS item_translations (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      item_id INT NOT NULL,
                      lang VARCHAR(5) NOT NULL,
                      name VARCHAR(255) NOT NULL,
                      FOREIGN KEY (item_id) REFERENCES items(id) ON DELETE CASCADE
                    )
                    """);
        } catch (SQLException e) {
            System.out.println("Failed to ensure schema: " + e.getMessage());
        }
    }

    public static void terminate() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Closing the connection");
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close the connection");
        }
    }
}
