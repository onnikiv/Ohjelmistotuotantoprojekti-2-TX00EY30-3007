package assignment3.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import assignment3.dao.ItemDAO;
import assignment3.model.CartItem;
import assignment3.model.ShoppingCart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ShoppingCartController {

    @FXML
    private ComboBox<String> languageComboBox;
    @FXML
    private Label languageLabel;
    @FXML
    private Label itemCountLabel;
    @FXML
    private Label pricePerLabel;
    @FXML
    private Label totalTextLabel;
    @FXML
    private TextField itemCountField;
    @FXML
    private TextField itemPriceField;
    @FXML
    private TextField itemNameField;
    @FXML
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem, String> nameColumn;
    @FXML
    private TableColumn<CartItem, Double> costColumn;
    @FXML
    private Label totalLabel;

    private ShoppingCart shoppingCart = new ShoppingCart();
    private final ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
    private ResourceBundle bundle = ResourceBundle.getBundle("localization.MessagesBundle", Locale.of("en", "US"));
    private final ItemDAO itemDao = new ItemDAO();

    @FXML
    public void initialize() {
        // Initialize language ComboBox
        ObservableList<String> languages = FXCollections.observableArrayList(
                "English", "Suomi", "Svenska", "日本語"
        );
        languageComboBox.setItems(languages);
        languageComboBox.setValue("English");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        cartTable.setItems(cartItems);
        updateTexts();
        // initial load of items for default language
        reloadItemsForSelectedLanguage();
    }

    @FXML
    private void handleAddItem() {
        try {
            int count = Integer.parseInt(itemCountField.getText());
            float price = Float.parseFloat(itemPriceField.getText());
            String name = itemNameField.getText();
            String lang = getSelectedLangCode();

            // update local total
            shoppingCart.addCartItem(count, price);

            // persist to DB and update UI
            try {
                int itemId = itemDao.insertItem(price, count);
                if (itemId > 0) {
                    itemDao.insertTranslation(itemId, lang, name);
                }
            } catch (SQLException e) {
                // For a classroom app, log and continue updating UI
                System.out.println("DB insert failed: " + e.getMessage());
            }

            CartItem item = new CartItem(name, count * price);
            cartItems.add(item);
            totalLabel.setText(String.format("%.2f", shoppingCart.getTotalCost()));
            itemCountField.clear();
            itemPriceField.clear();
            itemNameField.clear();
        } catch (NumberFormatException e) {
            // Optionally show error
        }
    }

    @FXML
    private void handleClearCart() {
        shoppingCart = new ShoppingCart();
        cartItems.clear();
        totalLabel.setText("0.00");
    }

    @FXML
    private void handleLanguageChange() {
        String selectedLanguage = languageComboBox.getValue();
        switch (selectedLanguage) {
            case "English" ->
                changeLanguage("en", "US");
            case "Suomi" ->
                changeLanguage("fi", "FI");
            case "Svenska" ->
                changeLanguage("sv", "SE");
            case "日本語" ->
                changeLanguage("ja", "JP");
        }
        reloadItemsForSelectedLanguage();
    }

    public void changeLanguage(String language, String country) {
        Locale locale = Locale.of(language, country);
        bundle = ResourceBundle.getBundle("localization.MessagesBundle", locale);
        updateTexts();
    }

    private void updateTexts() {
        languageLabel.setText(bundle.getString("language"));
        itemCountLabel.setText(bundle.getString("itemCount"));
        pricePerLabel.setText(bundle.getString("pricePerItem"));
        itemCountField.setPromptText(bundle.getString("count"));
        itemPriceField.setPromptText(bundle.getString("price"));
        if (itemNameField != null) {
            itemNameField.setPromptText(bundle.getString("itemName"));
        }
        addButton.setText(bundle.getString("add"));
        clearButton.setText(bundle.getString("empty"));
        totalTextLabel.setText(bundle.getString("total"));
        totalLabel.setText(String.format("%.2f", shoppingCart.getTotalCost()));
        nameColumn.setText(bundle.getString("itemName"));
        costColumn.setText(bundle.getString("cost"));

        if (languageLabel.getScene() != null) {
            Stage stage = (Stage) languageLabel.getScene().getWindow();
            stage.setTitle(bundle.getString("stagetitle"));
        }
    }

    private String getSelectedLangCode() {
        String selectedLanguage = languageComboBox.getValue();
        return switch (selectedLanguage) {
            case "Suomi" ->
                "fi";
            case "Svenska" ->
                "sv";
            case "日本語" ->
                "ja";
            default ->
                "en";
        };
    }

    private void reloadItemsForSelectedLanguage() {
        try {
            String lang = getSelectedLangCode();
            List<CartItem> items = itemDao.getItemsLocalized(lang);
            cartItems.setAll(items);
        } catch (SQLException e) {
            System.out.println("Failed to load items for language: " + e.getMessage());
            // keep existing list
        }
    }
}
