package model;

import exceptions.NotEnoughQuantity;
import exceptions.ProductDoesNotExist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void ExistaVerifica() throws ProductDoesNotExist, NotEnoughQuantity {
        Order order=new Order();
        Product produs=new Product("Paine",10);
        order.getOrder().add(produs);
        assertEquals(false, order.exista(produs));
    }

}