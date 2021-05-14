package services;

import controllers.ClientController;
import exceptions.CartIsEmptyException;
import exceptions.NotEnoughQuantity;
import exceptions.ProductAlreadyExistsException;
import exceptions.ProductDoesNotExist;
import model.Order;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testOrder";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        OrderService.initDatabase();
    }

    @Test
    void TestBazaDeDateGoala(){
        assertNotEquals(null, OrderService.getAllOrders());       //assertThat(OrderService.getAllOrders()).isNotNull();
        assertTrue(OrderService.getAllOrders().isEmpty());         //assertThat(OrderService.getAllOrders()).isEmpty();
    }

    @Test
    void OrderAdded() throws ProductAlreadyExistsException, ProductDoesNotExist, NotEnoughQuantity, CartIsEmptyException {
        Order order= new Order();
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        order.addProduct(new Product("Paine", 5));
        OrderService.placeOrder(order);
        assertEquals(order, OrderService.getAllOrders().get(0));      //assertThat(OrderService.getAllOrders().get(0)).isEqualTo(order);
    }

    @Test
    void OrdersAreRemoved() throws CartIsEmptyException, ProductDoesNotExist, NotEnoughQuantity, ProductAlreadyExistsException {
        Order order= new Order();
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        order.addProduct(new Product("Paine", 5));
        assertNotEquals(null, OrderService.getAllOrders());       //assertThat(OrderService.getAllOrders()).isNotNull();
        assertTrue(OrderService.getAllOrders().isEmpty());              //assertThat(OrderService.getAllOrders()).isEmpty();
        OrderService.placeOrder(order);
        assertFalse(OrderService.getAllOrders().isEmpty());        //assertThat(OrderService.getAllOrders()).isNotEmpty();
        assertEquals(1, OrderService.getAllOrders().size())    ;//assertThat(OrderService.getAllOrders()).size().isEqualTo(1);
        OrderService.removeAllOrders();
        assertNotEquals(null, OrderService.getAllOrders());         //assertThat(OrderService.getAllOrders()).isNotNull();
        assertTrue(OrderService.getAllOrders().isEmpty());          //assertThat(OrderService.getAllOrders()).isEmpty();
    }
}