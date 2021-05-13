package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import exceptions.ProductAlreadyExistsException;
import services.ProductService;

import java.io.IOException;

public class AddProductController{

    @FXML
    private Text addProductMessage;
    @FXML
    private TextField nameField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField codeField;
    @FXML
    private TextField quantityField;

    @FXML
    public void handleAddProductButton() {
        try {
            ProductService.addProduct(nameField.getText(), categoryField.getText(), codeField.getText(), Integer.parseInt(quantityField.getText()));
            addProductMessage.setText("Product added successfully!");
            Stage stage = (Stage) addProductMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("addProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
        catch (ProductAlreadyExistsException e) {
            addProductMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) addProductMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
