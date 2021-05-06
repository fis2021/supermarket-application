package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.UserService;

import java.io.IOException;

public class AdministratorController {

    @FXML
    private Text administratorMessage;

    @FXML
    public void handleSeeProductsAction() {
        administratorMessage.setText("SeeProducts");
    }

    @FXML
    public void handleAddProductAction() {
        try {
            Stage stage = (Stage) administratorMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("addProduct.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleModifyProductAction() {
        administratorMessage.setText("ModifyProduct");
    }

    @FXML
    public void handleDeleteProductAction() {
        administratorMessage.setText("DeleteProduct");
    }

    @FXML
    public void handleDisconnectAction()
    {
        try {
            Stage stage = (Stage) administratorMessage.getScene().getWindow();
            Parent viewStudentsRoot = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
            Scene scene = new Scene(viewStudentsRoot, 600, 400);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
