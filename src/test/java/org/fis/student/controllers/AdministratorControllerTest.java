package org.fis.student.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.student.services.OrderService;
import org.fis.student.services.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.fis.student.services.FileSystemService;
import org.fis.student.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class AdministratorControllerTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testAdministrator";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        OrderService.initDatabase();
        ProductService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        OrderService.getDatabase().close();
        ProductService.getDatabase().close();
        UserService.getDatabase().close();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("administrator.fxml"));
        primaryStage.setTitle("Administrator Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testAdministrator(FxRobot robot) {
        robot.clickOn("#adminViewProducts");
        robot.clickOn("#backViewProductsAdmin");

        robot.clickOn("#adminAddProductButton");
        robot.clickOn("#backAddProductAdmin");

        robot.clickOn("#adminModifyProduct");
        robot.clickOn("#backModifyProductAdmin");

        robot.clickOn("#adminRemoveProduct");
        robot.clickOn("#backRemoveProductAdmin");

        robot.clickOn("#adminViewOrders");
        robot.clickOn("#backViewOrdersAdmin");

        robot.clickOn("#adminDisconnect");
    }

}