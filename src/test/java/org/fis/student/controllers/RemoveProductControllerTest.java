package org.fis.student.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.student.exceptions.ProductAlreadyExistsException;
import org.fis.student.exceptions.UsernameAlreadyExistsException;
import org.fis.student.model.Order;
import org.fis.student.model.Product;
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

import javax.naming.Name;

import static org.testfx.assertions.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class RemoveProductControllerTest {

    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String CODE = "code";
    public static final Integer QUANTITY = 10;
    public static final Integer PRICE = 10;

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testRemoveProduct";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("removeProduct.fxml"));
        primaryStage.setTitle("Remove Product Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testRemoveProduct(FxRobot robot) {
        try {
            ProductService.addProduct(NAME, CATEGORY, CODE, QUANTITY, PRICE);
        }catch (ProductAlreadyExistsException e){};
        robot.clickOn("#nameRemoveProduct");
        robot.write(NAME);
        robot.clickOn("#codeRemoveProduct");
        robot.write(CODE);
        robot.clickOn("#removeProductRemoveProduct");

        assertThat(ProductService.getAllProducts()).size().isEqualTo(0);
    }

    @Test
    void testRemoveProductIncomplete(FxRobot robot) {
        robot.clickOn("#backRemoveProductAdmin");
        robot.clickOn("#adminRemoveProduct");

        robot.clickOn("#removeProductRemoveProduct");
        assertThat(robot.lookup("#removeProductMessage").queryText()).hasText("Please type in the Name!");
        robot.clickOn("#nameRemoveProduct");
        robot.write(NAME);

        robot.clickOn("#removeProductRemoveProduct");
        assertThat(robot.lookup("#removeProductMessage").queryText()).hasText("Please type in the Code!");
        robot.clickOn("#codeRemoveProduct");
        robot.write(CODE);

        robot.clickOn("#removeProductRemoveProduct");
        assertThat(robot.lookup("#removeProductMessage").queryText()).hasText("This product doesn't exist");
    }

}