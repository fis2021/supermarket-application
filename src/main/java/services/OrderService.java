package services;

import exceptions.CartIsEmptyException;
import model.Order;
import model.Product;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Order;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import org.dizitart.no2.objects.filters.ObjectFilters;
import services.ProductService;

import static services.FileSystemService.getPathToFile;

public class OrderService {

    public static ObjectRepository<Order> orderRepository;

    public static void initDatabase() {
        FileSystemService.initDirectory();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("Orders.db").toFile())
                .openOrCreate("test2", "test2");

        orderRepository = database.getRepository(Order.class);
    }

    public static List<Order> getAllOrders(){
        return orderRepository.find().toList();
    }

    public static void placeOrder(Order order) throws CartIsEmptyException{
        if(order.getContor() == 0)
            throw new CartIsEmptyException();
        else {
            orderRepository.insert(order);
            for (int i = 0; i < order.getContor(); i++)
                for (Product product : ProductService.productRepository.find())
                    if (Objects.equals(order.getOrder().get(i).getName(), product.getName()))
                        ProductService.modifyProduct(order.getOrder().get(i).getName(), product.getQuantity() - order.getOrder().get(i).getQuantity());
        }
    }

    public static void removeAllOrders() {
        orderRepository.remove(ObjectFilters.ALL);
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