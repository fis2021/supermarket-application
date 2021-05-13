package controllers;

import exceptions.ItemIsNotInTheCart;
import exceptions.NotEnoughQuantity;
import exceptions.ProductAlreadyExistsException;
import exceptions.ProductDoesNotExist;
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

    public static Order comanda=new Order("Luca");

    @FXML
    private Text clientMessage;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField quantityField;

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
        if(productNameField.getText() == null || productNameField.getText().isEmpty()){
            clientMessage.setText("Please type in a name!");
            return;
        }
        if(quantityField.getText() == null || quantityField.getText().isEmpty()){
            clientMessage.setText("Please type in the quantity!");
            return;
        }
        try {
            comanda.addProduct(new Product(productNameField.getText(), Integer.parseInt(quantityField.getText())));
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (ProductDoesNotExist e){
            clientMessage.setText(e.getMessage());
        } catch (NotEnoughQuantity e) {
            clientMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRemoveProduct() {
        if(productNameField.getText() == null || productNameField.getText().isEmpty()){
            clientMessage.setText("Please type in a name!");
            return;
        }
        try {
            comanda.removeProduct(new Product(productNameField.getText(), 1));
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        } catch (ItemIsNotInTheCart e) {
            clientMessage.setText(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    public void handleViewOrders()
    {
        try {
            Stage stage = (Stage) clientMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("viewOrdersClient.fxml"));
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
