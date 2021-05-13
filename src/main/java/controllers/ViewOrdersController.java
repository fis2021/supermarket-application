package controllers;

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
import model.ViewOrdersTableModel;
import services.OrderService;
import services.ProductService;
import java.io.IOException;
import java.util.Objects;


public class ViewOrdersController{

    @FXML
    private Text viewOrdersMessage;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        int i=1;
        ObservableList<ViewOrdersTableModel> data = tableView.getItems();
        for (Order order : OrderService.orderRepository.find()) {
            data.add(new ViewOrdersTableModel(i, order.pretTotal(), order.getUser()));
            i++;
        }
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleRemoveOrdersButton(){
        OrderService.removeAllOrders();
        try {
            Stage stage = (Stage) viewOrdersMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("viewOrders.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleBackButton()
    {
        try {
            Stage stage = (Stage) viewOrdersMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}

