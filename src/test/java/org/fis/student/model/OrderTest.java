package org.fis.student.model;

import org.apache.commons.io.FileUtils;
import org.fis.student.exceptions.ItemIsNotInTheCart;
import org.fis.student.exceptions.NotEnoughQuantity;
import org.fis.student.exceptions.ProductAlreadyExistsException;
import org.fis.student.exceptions.ProductDoesNotExist;
import org.fis.student.services.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.fis.student.services.FileSystemService;
import org.fis.student.services.OrderService;
import org.fis.student.services.ProductService;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testOrder";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        OrderService.initDatabase();
        ProductService.initDatabase();
        UserService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        OrderService.getDatabase().close();
        ProductService.getDatabase().close();
        UserService.getDatabase().close();
    }


    @Test
    void ExistaVerifica() throws ProductDoesNotExist, NotEnoughQuantity, ProductAlreadyExistsException {
        Order order=new Order();
        Product produs=new Product("Paine",10);
        ProductService.addProduct("Paine", "Paine", "001",10, 1);
        order.addProduct(produs);
        assertEquals(true, order.exista(produs));
    }

    @Test
    void AddProductTest() throws ProductDoesNotExist, NotEnoughQuantity, ProductAlreadyExistsException {
        Order order=new Order();
        Product produs=new Product("Paine",10);
        ProductService.addProduct("Paine", "Paine", "001",10, 1);
        order.addProduct(produs);
        assertEquals(1,order.getOrder().size());
    }

    @Test
    void RemoveProductTest() throws ProductAlreadyExistsException, ProductDoesNotExist, NotEnoughQuantity, ItemIsNotInTheCart {
        Order order=new Order();
        Product produs=new Product("Paine",10);
        ProductService.addProduct("Paine", "Paine", "001",10, 1);
        order.addProduct(produs);
        assertEquals(1,order.getOrder().size());
        order.removeProduct(produs);
        assertEquals(0,order.getOrder().size());
    }
}