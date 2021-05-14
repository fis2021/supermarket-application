package controllers;

import exceptions.ProductDoesNotExist;
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
        property.getItems().addAll("Name", "Category", "Quantity", "Price");
    }

    @FXML
    public void handleModifyProductButton() {
        if(codeField.getText() == null || codeField.getText().isEmpty()){
            modifyProductMessage.setText("Please type in the Code!");
            return;
        }
        if((String) property.getValue() == null || ((String) property.getValue()).isEmpty()){
            modifyProductMessage.setText("Please select a Property!");
            return;
        }
        if(newValueField.getText() == null || newValueField.getText().isEmpty()){
            modifyProductMessage.setText("Please type in the New Value!");
            return;
        }
        if((String) property.getValue() == "Quantity"){
            try
            {
                Integer.parseInt(newValueField.getText());
            } catch (NumberFormatException ex) {
                modifyProductMessage.setText("Quantity must be an Integer!");
                return;
            }
        }
        if((String) property.getValue() == "Price"){
            try
            {
                Integer.parseInt(newValueField.getText());
            } catch (NumberFormatException ex) {
                modifyProductMessage.setText("Price must be an Integer!");
                return;
            }
        }

        try {
            ProductService.modifyProduct(codeField.getText(), (String) property.getValue(), newValueField.getText());
            modifyProductMessage.setText("Product modified successfully!");
            Stage stage = (Stage) modifyProductMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("modifyProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }catch(ProductDoesNotExist e){
            modifyProductMessage.setText("This product doesn't exist");
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

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleModifyProductButton();
    }

}
