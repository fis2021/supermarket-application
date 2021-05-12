package controllers;

import exceptions.ProductAlreadyExistsException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import model.Order;
import model.Product;
import services.OrderService;
import services.ProductService;
import java.io.IOException;
import java.util.Objects;


public class ViewCartController{


    @FXML
    private Text viewCartMessage;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        ObservableList<Product> data = tableView.getItems();
        for (int i=0;i<ClientController.comanda.getContor();i++) {
            data.add(new Product(ClientController.comanda.getOrder().get(i).getName(), ClientController.comanda.getOrder().get(i).getQuantity()));
        }
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) viewCartMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSendOrderButton() {

            OrderService.placeOrder(ClientController.comanda);
    }
}
