package services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import exceptions.ProductExists;
import model.Product;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static services.FileSystemService.getPathToFile;

public class ProductService {

    private static ObjectRepository<Product> productRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("registration-example.db").toFile())
                .openOrCreate("test", "test");

        productRepository = database.getRepository(Product.class);
    }

    public static void checkNameAndQuantity(String name,int quantity) throws ProductExists {
        for (Product product : productRepository.find()) {
            if (Objects.equals(name, product.getName())&&Objects.equals(quantity, product.getQuantity()))
                throw new ProductExists(name);
        }
    }

    public static void addUser(String name, String category, String role, String code, int quantity) throws ProductAlreadyExistsException {
        checkProductDoesNotAlreadyExist(name);
        productRepository.insert(new Product(name, category, role, name, quantity));
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
