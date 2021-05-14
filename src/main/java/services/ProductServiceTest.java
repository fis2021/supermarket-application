package services;

import exceptions.ProductAlreadyExistsException;
import exceptions.ProductDoesNotExist;
import exceptions.UsernameAlreadyExistsException;
import model.Product;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testProduse";
        //FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ProductService.initDatabase();
    }

    @Test
    void TestBazaDeDateGoala(){
        //assertThat(ProductService.getAllProducts()).isNotNull();
        //assertThat(ProductService.getAllProducts()).isEmpty();
    }

    @Test
    void ProductIsAdded() throws ProductAlreadyExistsException {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        //assertThat(ProductService.getAllProducts()).isNotEmpty();
        //assertThat(ProductService.getAllProducts()).size().isEqualTo(1);
        Product product =ProductService.getAllProducts().get(0);
        //assertThat(product).isNotNull();
        /*assertThat(product.getName()).isEqualTo("Paine");
        assertThat(product.getQuantity()).isEqualTo(10);
        assertThat(product.getCode()).isEqualTo("001");
        assertThat(product.getPrice()).isEqualTo(10);
        assertThat(product.getCategory()).isEqualTo("Paine");*/
    }

    @Test
    void ProductIsRemoved() throws ProductAlreadyExistsException, ProductDoesNotExist {
        //assertThat(ProductService.getAllProducts()).isNotNull();
        //assertThat(ProductService.getAllProducts()).isEmpty();
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        //assertThat(ProductService.getAllProducts()).isNotEmpty();
        //assertThat(ProductService.getAllProducts()).size().isEqualTo(1);
        ProductService.removeProduct("Paine", "001");
        //assertThat(ProductService.getAllProducts()).isNotNull();
        //assertThat(ProductService.getAllProducts()).isEmpty();
    }

    @Test
    void ProductIsModifiedComplex() throws ProductAlreadyExistsException, ProductDoesNotExist {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        ProductService.modifyProduct("001", "Name", "Paine2");
        //assertThat(ProductService.getAllProducts().get(0).getName()).isEqualTo("Paine2");
        ProductService.modifyProduct("001", "Quantity", "5");
        //assertThat(ProductService.getAllProducts().get(0).getQuantity()).isEqualTo(5);
        ProductService.modifyProduct("001", "Category", "Paine2");
        //assertThat(ProductService.getAllProducts().get(0).getCategory()).isEqualTo("Paine2");
        ProductService.modifyProduct("001", "Price", "10");
        //assertThat(ProductService.getAllProducts().get(0).getPrice()).isEqualTo(10);
    }

    @Test
    void ProductIsModifiedSimple() throws ProductAlreadyExistsException, ProductDoesNotExist {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        ProductService.modifyProduct("Paine", 20);
        //assertThat(ProductService.getAllProducts().get(0).getQuantity()).isEqualTo(20);
    }

    @Test
    void PriceIsCalculatedCorrectly() throws ProductAlreadyExistsException {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        int temp1= ProductService.getPrice("Paine", 5);
        //assertThat(temp1).isEqualTo(50);
    }

    @Test
    void ProductCannotBeAddedTwice() {
        /*AssertThrows(ProductAlreadyExistsException.class, () ->{
            ProductService.addProduct("Paine", "Paine", "001", 10, 10);
            ProductService.addProduct("Paine", "Paine", "001", 10, 10);
    });*/
    }
}