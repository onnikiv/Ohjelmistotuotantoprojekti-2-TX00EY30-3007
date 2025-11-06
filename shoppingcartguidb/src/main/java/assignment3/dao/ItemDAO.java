package assignment3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import assignment3.model.CartItem;

public class ItemDAO {

    public int insertItem(double price, int quantity) throws SQLException {
        String sql = "INSERT INTO items(price, quantity) VALUES(?, ?)";
        Connection c = DbConnection.getConnection();
        if (c == null) {
            // DB not available; skip persistence
            return 0;
        }
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setDouble(1, price);
            ps.setInt(2, quantity);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        throw new SQLException("Failed to insert item, no id returned");
    }

    public void insertTranslation(int itemId, String lang, String name) throws SQLException {
        if (itemId <= 0) {
            return; // nothing to do if item not persisted

                }String sql = "INSERT INTO item_translations(item_id, lang, name) VALUES(?, ?, ?)";
        Connection c = DbConnection.getConnection();
        if (c == null) {
            return;
        }
        try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ps.setString(2, lang);
            ps.setString(3, name);
            ps.executeUpdate();
        }
    }

    public List<CartItem> getItemsLocalized(String lang) throws SQLException {
        String sql = "SELECT t.name, (i.price * i.quantity) AS cost "
                + "FROM items i JOIN item_translations t ON t.item_id = i.id "
                + "WHERE t.lang = ? ORDER BY i.id";
        List<CartItem> result = new ArrayList<>();
        Connection c = DbConnection.getConnection();
        if (c == null) {
            return result; // DB not available

                }try (PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, lang);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString(1);
                    double cost = rs.getDouble(2);
                    result.add(new CartItem(name, cost));
                }
            }
        }
        return result;
    }
}
