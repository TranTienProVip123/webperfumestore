package web.Webperfume.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.Webperfume.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.Webperfume.model.Product;
import web.Webperfume.model.User;
import web.Webperfume.model.Order;
import web.Webperfume.repository.AdminRepository;
import web.Webperfume.repository.ProductRepository;
import web.Webperfume.repository.UserRepository;
import web.Webperfume.repository.OrderRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
@Repository
public class AdminService implements AdminRepository {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    // Perfume-related methods
    @Override
    public List<Product> getProduct() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> searchProduct(String keyword) {
        return productRepository.findByNameContaining(keyword);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product editProduct(Product product) {
        return productRepository.save(product);
    }

    // User-related methods
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return userRepository.findByUsernameContaining(keyword);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Order-related methods
    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> searchOrders(String keyword) {
        return orderRepository.findByCustomerNameContaining(keyword);
    }

    public AdminService(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }
}
