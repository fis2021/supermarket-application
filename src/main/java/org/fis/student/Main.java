package org.fis.student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.fis.student.services.*;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //initDirectory();
        UserService.initDatabase();
        ProductService.initDatabase();
        OrderService.initDatabase();

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Supermarket Application");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
