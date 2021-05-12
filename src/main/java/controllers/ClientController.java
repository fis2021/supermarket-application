package controllers;

import exceptions.ProductAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Order;
import model.Product;
import model.User;
import services.OrderService;

import java.io.IOException;


public class ClientController {

    public static Order comanda=new Order();

    @FXML
    private Text clientMessage;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;

    @FXML
    public void initialize() {
        Order comanda=new Order();
    }

    @FXML
    public void handleViewProducts() {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("viewProductsClient.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleAddToCart() {
            comanda.addProduct(new Product(productNameField.getText(), Integer.parseInt(quantityField.getText())));

    }

    @FXML
    public void handleRemoveProduct() {
        comanda.removeProduct(new Product(productNameField.getText(), 1));
    }

    @FXML
    public void handleViewCart() {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("cart.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleDisconnect()
    {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
