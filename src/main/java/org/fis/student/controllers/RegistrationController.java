package org.fis.student.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.fis.student.exceptions.UsernameAlreadyExistsException;
import org.fis.student.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField emailField;

    @FXML
    public void initialize() {
        role.getItems().addAll("Administrator", "Client");
    }

    @FXML
    public void handleRegisterAction() {
        if(usernameField.getText() == null || usernameField.getText().isEmpty()){
            registrationMessage.setText("Please type and Username!");
            return;
        }
        if(passwordField.getText() == null || passwordField.getText().isEmpty()){
            registrationMessage.setText("Please type a Password!");
            return;
        }
        if((String) role.getValue() == null || ((String) role.getValue()).isEmpty()){
            registrationMessage.setText("Please select a Role!");
            return;
        }
        if(nameField.getText() == null || nameField.getText().isEmpty()){
            registrationMessage.setText("Please type a Name!");
            return;
        }
        if(addressField.getText() == null || addressField.getText().isEmpty()){
            registrationMessage.setText("Please type an Address!");
            return;
        }
        if(emailField.getText() == null || emailField.getText().isEmpty()){
            registrationMessage.setText("Please type an Email!");
            return;
        }

        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue(), nameField.getText(), addressField.getText(), emailField.getText());
            registrationMessage.setText("Account added successfully!");
            Stage stage = (Stage) registrationMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
        catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText("Please type an Email!");
        }
    }


    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) registrationMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onEnter(javafx.event.ActionEvent actionEvent) {
        handleRegisterAction();
    }

}
