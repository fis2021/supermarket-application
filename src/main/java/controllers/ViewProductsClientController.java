package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.text.Text;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.collections.ObservableList;

import model.Product;
import services.ProductService;
import java.io.IOException;
import java.util.Objects;


public class ViewProductsClientController{

    @FXML
    private Text viewProductsClientMessage;

    @FXML
    private TableView tableView;

    @FXML
    public void initialize() {
        ObservableList<Product> data = tableView.getItems();
        for (Product product : ProductService.productRepository.find()) {
            data.add(new Product(product.getName(), product.getCategory(), product.getCode(), product.getQuantity()));
        }
        tableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleBackButtonAction()
    {
        try {
            Stage stage = (Stage) viewProductsClientMessage.getScene().getWindow();
            Parent Login = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
            Scene scene = new Scene(Login, 900, 600);
            stage.setScene(scene);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

}
