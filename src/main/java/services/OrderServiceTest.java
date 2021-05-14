package services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testInregistrare";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        OrderService.initDatabase();
    }

    @Test
    void TestBazaDeDateGoala(){
        //assertThat(OrderService.getAllOrders()).isNotNull();
        //assertThat(OrderService.getAllOrders()).isEmpty();
    }

}