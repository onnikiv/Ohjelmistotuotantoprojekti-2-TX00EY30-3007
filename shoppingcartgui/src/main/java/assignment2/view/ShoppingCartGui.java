package assignment2.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ShoppingCartGui extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/shoppingcartgui.fxml"));
        stage.setTitle("Shoppingcart - onnikiv");
        stage.setScene(new Scene(root, 600, 400));
        stage.show();
    }
}
