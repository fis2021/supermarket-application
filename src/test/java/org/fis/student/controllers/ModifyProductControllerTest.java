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

import static org.testfx.assertions.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class ModifyControllerTest {

    public static final String NAME = "name";
    public static final String CATEGORY = "category";
    public static final String CODE = "code";
    public static final Integer QUANTITY = 10;
    public static final Integer PRICE = 10;
    public static final String NEWVALUE = "newValue";
    public static final Integer NEWVALUEINT = 10;

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testModifyProduct";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("modifyProduct.fxml"));
        primaryStage.setTitle("Add Product Example");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

    @Test
    void testModifyProductName(FxRobot robot) {
        try {
            ProductService.addProduct(NAME, CATEGORY, CODE, QUANTITY, PRICE);
        }catch (ProductAlreadyExistsException e){};
        robot.clickOn("#codeModifyProductAdmin");
        robot.write(CODE);
        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Name");
        robot.clickOn("#newValue");
        robot.write(NEWVALUE);
        robot.clickOn("#modifyProductButton");
        assertEquals(ProductService.getAllProducts().get(0).toString(), new Product(NEWVALUE, CATEGORY, CODE, QUANTITY, PRICE).toString());
    }

    @Test
    void testModifyProductCategory(FxRobot robot) {
        try {
            ProductService.addProduct(NAME, CATEGORY, CODE, QUANTITY, PRICE);
        }catch (ProductAlreadyExistsException e){};
        robot.clickOn("#codeModifyProductAdmin");
        robot.write(CODE);
        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Category");
        robot.clickOn("#newValue");
        robot.write(NEWVALUE);
        robot.clickOn("#modifyProductButton");
        assertEquals(ProductService.getAllProducts().get(0).toString(), new Product(NAME, NEWVALUE, CODE, QUANTITY, PRICE).toString());
    }

    @Test
    void testModifyProductQuantity(FxRobot robot) {
        try {
            ProductService.addProduct(NAME, CATEGORY, CODE, QUANTITY, PRICE);
        }catch (ProductAlreadyExistsException e){};
        robot.clickOn("#codeModifyProductAdmin");
        robot.write(CODE);
        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Quantity");
        robot.clickOn("#newValue");
        robot.write(NEWVALUEINT.toString());
        robot.clickOn("#modifyProductButton");
        assertEquals(ProductService.getAllProducts().get(0).toString(), new Product(NAME, CATEGORY, CODE,  NEWVALUEINT, PRICE).toString());
    }

    @Test
    void testModifyProductPrice(FxRobot robot) {
        try {
            ProductService.addProduct(NAME, CATEGORY, CODE, QUANTITY, PRICE);
        }catch (ProductAlreadyExistsException e){};
        robot.clickOn("#codeModifyProductAdmin");
        robot.write(CODE);
        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Price");
        robot.clickOn("#newValue");
        robot.write(NEWVALUEINT.toString());
        robot.clickOn("#modifyProductButton");
        assertEquals(ProductService.getAllProducts().get(0).toString(), new Product(NAME, CATEGORY, CODE, QUANTITY, NEWVALUEINT).toString());
    }

    @Test
    void testModifyProductIncomplete(FxRobot robot) {
        robot.clickOn("#backModifyProductAdmin");
        robot.clickOn("#adminModifyProduct");

        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("Please type in the Code!");
        robot.clickOn("#codeModifyProductAdmin");
        robot.write(CODE);

        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("Please select a Property!");
        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Quantity");

        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("Please type in the New Value!");
        robot.clickOn("#newValue");
        robot.write(NEWVALUE);

        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("Quantity must be an Integer!");

        robot.clickOn("#propertyModifyProduct");
        robot.clickOn("Price");
        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("Price must be an Integer!");

        robot.clickOn("#newValue");
        robot.eraseText(8);
        robot.write(NEWVALUEINT.toString());
        robot.clickOn("#modifyProductButton");
        assertThat(robot.lookup("#modifyProductMessage").queryText()).hasText("This product doesn't exist");
    }

}