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
class AddProductControllerTest {

    public static final String NAME = "product1";
    public static final String CATEGORY = "category";
    public static final String CODE = "code";
    public static final String QUANTITY = "10";
    public static final String PRICE = "10";

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testAddProduct";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addProduct.fxml"));
        primaryStage.setTitle("Add Product Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testAddProduct(FxRobot robot) {
        robot.clickOn("#nameAddProduct");
        robot.write(NAME);
        robot.clickOn("#categoryAddProduct");
        robot.write(CATEGORY);
        robot.clickOn("#codeAddProduct");
        robot.write(CODE);
        robot.clickOn("#quantityAddProduct");
        robot.write(QUANTITY);
        robot.clickOn("#priceAddProduct");
        robot.write(PRICE);
        robot.clickOn("#addProductAddProductButton");
        assertThat(ProductService.getAllProducts()).size().isEqualTo(1);

        robot.clickOn("#nameAddProduct");
        robot.write(NAME);
        robot.clickOn("#categoryAddProduct");
        robot.write(CATEGORY);
        robot.clickOn("#codeAddProduct");
        robot.write(CODE);
        robot.clickOn("#quantityAddProduct");
        robot.write(QUANTITY);
        robot.clickOn("#priceAddProduct");
        robot.write(PRICE);
        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText(
                String.format("The product %s already exists!", NAME));

        robot.clickOn("#nameAddProduct");
        robot.eraseText(1);
        robot.write("2");
        robot.clickOn("#codeAddProduct");
        robot.write("2");
        robot.clickOn("#addProductAddProductButton");
        assertThat(ProductService.getAllProducts()).size().isEqualTo(2);
    }

    @Test
    void testAddProductIncomplete(FxRobot robot) {
        robot.clickOn("#backAddProductAdmin");
        robot.clickOn("#adminAddProductButton");

        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Please type in the Name!");
        robot.clickOn("#nameAddProduct");
        robot.write(NAME);

        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Please type in the Category!");
        robot.clickOn("#categoryAddProduct");
        robot.write(CATEGORY);

        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Please type in the Code!");
        robot.clickOn("#codeAddProduct");
        robot.write(CODE);

        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Please type in the Quantity!");
        robot.clickOn("#quantityAddProduct");
        robot.write("test");
        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Quantity must be an Integer!");
        robot.clickOn("#quantityAddProduct");
        robot.eraseText(4);
        robot.write(QUANTITY);

        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Please type in the Price!");
        robot.clickOn("#priceAddProduct");
        robot.write("test");
        robot.clickOn("#addProductAddProductButton");
        assertThat(robot.lookup("#addProductMessage").queryText()).hasText("Price must be an Integer!");
        robot.clickOn("#priceAddProduct");
        robot.eraseText(4);
        robot.write(PRICE);

        robot.clickOn("#addProductAddProductButton");

    }
}