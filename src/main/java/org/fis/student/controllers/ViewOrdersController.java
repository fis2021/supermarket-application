package org.fis.student.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import org.fis.student.model.Order;
import org.fis.student.model.ViewOrdersTableModel;
import org.fis.student.services.OrderService;

import java.io.IOException;


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

