package assignment2.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import assignment2.model.CartItem;
import assignment2.model.ShoppingCart;
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
    private Button addButton;
    @FXML
    private Button clearButton;
    @FXML
    private TableView<CartItem> cartTable;
    @FXML
    private TableColumn<CartItem, Integer> nameColumn;
    @FXML
    private TableColumn<CartItem, Double> costColumn;
    @FXML
    private Label totalLabel;

    private ShoppingCart shoppingCart = new ShoppingCart();
    private final ObservableList<CartItem> cartItems = FXCollections.observableArrayList();
    private ResourceBundle bundle = ResourceBundle.getBundle("localization.MessagesBundle", Locale.of("en", "US"));

    @FXML
    public void initialize() {
        // Initialize language ComboBox
        ObservableList<String> languages = FXCollections.observableArrayList(
                "English", "Suomi", "Svenska", "日本語"
        );
        languageComboBox.setItems(languages);
        languageComboBox.setValue("Suomi");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        costColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        cartTable.setItems(cartItems);
        updateTexts();
    }

    @FXML
    private void handleAddItem() {
        try {
            int count = Integer.parseInt(itemCountField.getText());
            float price = Float.parseFloat(itemPriceField.getText());
            shoppingCart.addCartItem(count, price);
            CartItem item = new CartItem(cartItems.size() + 1, count * price);
            cartItems.add(item);
            totalLabel.setText(String.format("%.2f", shoppingCart.getTotalCost()));
            itemCountField.clear();
            itemPriceField.clear();
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
            case "English":
                changeLanguage("en", "US");
                break;
            case "Suomi":
                changeLanguage("fi", "FI");
                break;
            case "Svenska":
                changeLanguage("sv", "SE");
                break;
            case "日本語":
                changeLanguage("ja", "JP");
                break;
        }
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
        addButton.setText(bundle.getString("add"));
        clearButton.setText(bundle.getString("empty"));
        totalTextLabel.setText(bundle.getString("total"));
        totalLabel.setText(String.format("%.2f", shoppingCart.getTotalCost()));
        nameColumn.setText(bundle.getString("count"));
        costColumn.setText(bundle.getString("price"));

        if (languageLabel.getScene() != null) {
            Stage stage = (Stage) languageLabel.getScene().getWindow();
            stage.setTitle(bundle.getString("stagetitle"));
        }
    }
}
