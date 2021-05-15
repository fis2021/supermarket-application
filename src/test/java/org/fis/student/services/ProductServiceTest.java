package org.fis.student.services;

import org.apache.commons.io.FileUtils;
import org.fis.student.exceptions.ProductAlreadyExistsException;
import org.fis.student.exceptions.ProductDoesNotExist;
import org.fis.student.model.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".testProduse";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        ProductService.initDatabase();
    }

    @AfterEach
    void tearDown() {
        ProductService.getDatabase().close();
    }

    @Test
    void TestBazaDeDateGoala(){
        assertNotEquals(null,ProductService.getAllProducts());    //assertThat(ProductService.getAllProducts()).isNotNull();
        assertTrue(ProductService.getAllProducts().isEmpty());     //assertThat(ProductService.getAllProducts()).isEmpty();
    }

    @Test
    void ProductIsAdded() throws ProductAlreadyExistsException {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        assertFalse(ProductService.getAllProducts().isEmpty());      //assertThat(ProductService.getAllProducts()).isNotEmpty();
        assertEquals(1, ProductService.getAllProducts().size());       //assertThat(ProductService.getAllProducts()).size().isEqualTo(1);
        Product product =ProductService.getAllProducts().get(0);
        assertNotEquals(null, product);       //assertThat(product).isNotNull();
        assertEquals("Paine", product.getName());      //assertThat(product.getName()).isEqualTo("Paine");
        assertEquals(10, product.getQuantity());     //assertThat(product.getQuantity()).isEqualTo(10);
        assertEquals("001", product.getCode());     //assertThat(product.getCode()).isEqualTo("001");
        assertEquals(10, product.getPrice());      //assertThat(product.getPrice()).isEqualTo(10);
        assertEquals("Paine", product.getCategory());     //assertThat(product.getCategory()).isEqualTo("Paine");
    }

    @Test
    void ProductIsRemoved() throws ProductAlreadyExistsException, ProductDoesNotExist {
        assertNotEquals(null,ProductService.getAllProducts());     //assertThat(ProductService.getAllProducts()).isNotNull();
        assertTrue(ProductService.getAllProducts().isEmpty());       //assertThat(ProductService.getAllProducts()).isEmpty();
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        assertFalse(ProductService.getAllProducts().isEmpty());     //assertThat(ProductService.getAllProducts()).isNotEmpty();
        assertEquals(1, ProductService.getAllProducts().size());     //assertThat(ProductService.getAllProducts()).size().isEqualTo(1);
        ProductService.removeProduct("Paine", "001");
        assertNotEquals(null,ProductService.getAllProducts());       //assertThat(ProductService.getAllProducts()).isNotNull();
        assertTrue(ProductService.getAllProducts().isEmpty());       //assertThat(ProductService.getAllProducts()).isEmpty();
    }

    @Test
    void ProductIsModifiedComplex() throws ProductAlreadyExistsException, ProductDoesNotExist {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        ProductService.modifyProduct("001", "Name", "Paine2");
        assertEquals("Paine2", ProductService.getAllProducts().get(0).getName());      //assertThat(ProductService.getAllProducts().get(0).getName()).isEqualTo("Paine2");
        ProductService.modifyProduct("001", "Quantity", "5");
        assertEquals(5, ProductService.getAllProducts().get(0).getQuantity());        //assertThat(ProductService.getAllProducts().get(0).getQuantity()).isEqualTo(5);
        ProductService.modifyProduct("001", "Category", "Paine2");
        assertEquals("Paine2", ProductService.getAllProducts().get(0).getCategory());       //assertThat(ProductService.getAllProducts().get(0).getCategory()).isEqualTo("Paine2");
        ProductService.modifyProduct("001", "Price", "10");
        assertEquals(10, ProductService.getAllProducts().get(0).getPrice());       //assertThat(ProductService.getAllProducts().get(0).getPrice()).isEqualTo(10);
    }

    @Test
    void ProductIsModifiedSimple() throws ProductAlreadyExistsException, ProductDoesNotExist {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        ProductService.modifyProduct("Paine", 20);
        assertEquals(20, ProductService.getAllProducts().get(0).getQuantity());        //assertThat(ProductService.getAllProducts().get(0).getQuantity()).isEqualTo(20);
    }

    @Test
    void PriceIsCalculatedCorrectly() throws ProductAlreadyExistsException {
        ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        int temp1= ProductService.getPrice("Paine", 5);
        assertEquals(50, temp1);        //assertThat(temp1).isEqualTo(50);
    }

    @Test
    void ProductCannotBeAddedTwice() {
        assertThrows(ProductAlreadyExistsException.class, () ->{
            ProductService.addProduct("Paine", "Paine", "001", 10, 10);
            ProductService.addProduct("Paine", "Paine", "001", 10, 10);
        });
    }
}