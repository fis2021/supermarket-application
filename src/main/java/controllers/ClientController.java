package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class ClientController {

    @FXML
    private Text clientMessage;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;

    @FXML
    public void handleViewProducts() {
        clientMessage.setText("ViewProducts");
    }

    @FXML
    public void handleAddToCart() {
        clientMessage.setText("handleAddToCart");
    }

    @FXML
    public void handleRemoveProduct() {
        clientMessage.setText("handleRemoveProduct");
    }

    @FXML
    public void handleViewCart() {
        clientMessage.setText("#handleViewCart");
    }

    @FXML
    public void handleDisconnect()
    {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
