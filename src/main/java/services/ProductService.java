package services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ProductAlreadyExistsException;
import model.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static services.FileSystemService.getPathToFile;

public class ProductService {

    public static ObjectRepository<Product> productRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Products.db").toFile())
                .openOrCreate("test1", "test1");

        productRepository = database.getRepository(Product.class);
    }

    public static void checkNameAndQuantity(String name, int quantity) throws ProductAlreadyExistsException {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName())&&Objects.equals(quantity, product.getQuantity()))
                throw new ProductAlreadyExistsException(name);
        }
    }

    public static void addProduct(String name, String category, String code, Integer quantity) throws ProductAlreadyExistsException {
        checkProductDoesNotAlreadyExist(name);
        productRepository.insert(new Product(name, category, code, quantity));
    }

    public static void removeProduct(String name, String code) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName()) && Objects.equals(code, product.getCode()))
                        productRepository.remove(product);
        }
    }

    public static void modifyProduct(String code, String property, String newValue) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(code, product.getCode())){
                if(property.equals("Name"))
                    product.setName(newValue);
                else if(property.equals("Category"))
                    product.setCategory(newValue);
                else if(property.equals("Quantity"))
                    product.setQuantity(Integer.parseInt(newValue));
                productRepository.update(product);
            }
        }
    }


    public static void modifyProduct(String name ,Integer newValue) {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName())){
                    product.setQuantity(newValue);
                productRepository.update(product);
            }
        }
    }

    private static void checkProductDoesNotAlreadyExist(String name) throws ProductAlreadyExistsException {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName()))
                throw new ProductAlreadyExistsException(name);
        }
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }
}
