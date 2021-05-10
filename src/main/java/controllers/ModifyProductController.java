package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import exceptions.ProductAlreadyExistsException;
import services.ProductService;

import java.io.IOException;

public class ModifyProductController{

    @FXML
    private Text modifyProductMessage;
    @FXML
    private ChoiceBox property;
    @FXML
    private TextField codeField;
    @FXML
    private TextField newValueField;

    @FXML
    public void initialize() {
        property.getItems().addAll("Name", "Category", "Quantity");
    }

    @FXML
    public void handleModifyProductButton() {
        try {
            ProductService.modifyProduct(codeField.getText(), (String) property.getValue(), newValueField.getText());
            modifyProductMessage.setText("Product modified successfully!");
            Stage stage = (Stage) modifyProductMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("modifyProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) modifyProductMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
