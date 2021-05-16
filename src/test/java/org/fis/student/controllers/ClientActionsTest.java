package org.fis.student.controllers;

import com.sun.org.apache.xpath.internal.operations.Or;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fis.student.exceptions.CartIsEmptyException;
import org.fis.student.exceptions.NotEnoughQuantity;
import org.fis.student.exceptions.ProductAlreadyExistsException;
import org.fis.student.exceptions.ProductDoesNotExist;
import org.fis.student.model.Order;
import org.fis.student.model.Product;
import org.fis.student.model.User;
import org.fis.student.services.FileSystemService;
import org.fis.student.services.OrderService;
import org.fis.student.services.ProductService;
import org.fis.student.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)

class ClientActionsTest {

    public static final String CODE = "001";
    public static final String NAME = "Paine";
    public static final String CATEGORY = "Paine";
    public static final String QUANTITY = "5";
    public static final String PRICE = "5";

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testRegister";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("client.fxml"));
        primaryStage.setTitle("User Actions Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void TestAddViewProducts(FxRobot robot) throws ProductAlreadyExistsException {
        robot.clickOn("#ClientViewProducts");
        robot.clickOn("#backViewProducts");

        robot.clickOn("#clientAddToCart");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("Please type in a name!");
        robot.clickOn("#productNameField");
        robot.write(NAME);

        robot.clickOn("#clientAddToCart");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("Please type in the quantity!");
        robot.clickOn("#quantityField");
        robot.write("abc");

        robot.clickOn("#clientAddToCart");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("Quantity must be an Integer!");
        robot.clickOn("#quantityField");
        robot.eraseText(3);
        robot.write(QUANTITY);

        robot.clickOn("#clientAddToCart");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("The product Paine doesn't exists!");
        ProductService.addProduct(NAME, CATEGORY, CODE, Integer.parseInt(QUANTITY), Integer.parseInt(PRICE));
        robot.clickOn("#clientAddToCart");
        robot.clickOn("#viewCart");
    }

    @Test
    void TestAddRemoveProduct(FxRobot robot) throws ProductAlreadyExistsException {
        robot.clickOn("#productNameField");
        robot.write(NAME);
        robot.clickOn("#quantityField");
        robot.write(QUANTITY);
        ProductService.addProduct(NAME, CATEGORY, CODE, Integer.parseInt(QUANTITY), Integer.parseInt(PRICE));
        robot.clickOn("#clientAddToCart");
        assertThat(ClientController.comanda.getOrder()).size().isEqualTo(1);
        robot.clickOn("#viewCart");
        robot.clickOn("#backCart");
        robot.clickOn("#clientRemoveProduct");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("Please type in a name!");
        robot.clickOn("#productNameField");
        robot.write(NAME);
        robot.clickOn("#clientRemoveProduct");
        assertThat(robot.lookup("#clientMessage").queryText()).hasText("Quantity must be an Integer!");
        robot.clickOn("#quantityField");
        robot.write(QUANTITY);
        robot.clickOn("#clientRemoveProduct");
        assertThat(ClientController.comanda.getOrder()).size().isEqualTo(0);
        robot.clickOn("#viewCart");
    }

    @Test
    void AddOrders(FxRobot robot) throws ProductAlreadyExistsException, ProductDoesNotExist, NotEnoughQuantity, CartIsEmptyException {
        ProductService.addProduct(NAME, CATEGORY, CODE, Integer.parseInt(QUANTITY)+50, Integer.parseInt(PRICE));
        robot.clickOn("#productNameField");
        robot.write(NAME);
        robot.clickOn("#quantityField");
        robot.write(QUANTITY);
        robot.clickOn("#clientAddToCart");
        robot.clickOn("#viewCart");
        robot.clickOn("#sendOrderClient");
        robot.clickOn("#backCart");
        assertEquals(1, OrderService.getAllOrders().size());
    }

    @Test
    void ViewOrders(FxRobot robot) {
        robot.clickOn("#clientViewOrders");
        robot.clickOn("#backClientOrders");
    }

    @Test
    void clientDisconnect(FxRobot robot) {
        robot.clickOn("#clientDisconnectButton");
    }

}