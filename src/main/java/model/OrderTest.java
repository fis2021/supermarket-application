package model;

import exceptions.ItemIsNotInTheCart;
import exceptions.NotEnoughQuantity;
import exceptions.ProductAlreadyExistsException;
import exceptions.ProductDoesNotExist;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.FileSystemService;
import services.OrderService;
import services.ProductService;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {


    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testOrder";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        OrderService.initDatabase();
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