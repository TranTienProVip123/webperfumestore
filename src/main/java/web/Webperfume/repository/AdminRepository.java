package web.Webperfume.repository;

import web.Webperfume.model.Product;
import web.Webperfume.model.Product;
import web.Webperfume.model.User;
import web.Webperfume.model.Order;

import java.util.List;

public interface AdminRepository {
    // Perfume-related methods
    List<Product> getProduct();
    List<Product> searchProduct(String keyword);
    Product getProductById(Long id);
    Product editProduct(Product product);

    // User-related methods
    List<User> getUsers();
    List<User> searchUsers(String keyword);
    User getUserById(Long id);

    // Order-related methods
    Order getOrder(Long id);
    List<Order> getOrders();
    List<Order> searchOrders(String keyword);
}
