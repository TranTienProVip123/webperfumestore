package web.Webperfume.service;

import web.Webperfume.model.CartItem;
import web.Webperfume.model.Order;
import web.Webperfume.model.OrderDetail;
import web.Webperfume.model.Product;
import web.Webperfume.repository.OrderDetailRepository;
import web.Webperfume.repository.OrderRepository;
import web.Webperfume.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<CartItem>();

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(product, quantity));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void removeFromCart(Long productId) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().getId().equals(productId));
    }

    public void clearCart() {
        cartItems.clear();
    }

    public void updateQuantity(Long productId, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(quantity);
                return;
            }
        }
        throw new IllegalArgumentException("Product not found in cart: " + productId);
    }

    public double getTotalPrice() {
        return cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
    }

    public void checkout(Order order) {
        List<OrderDetail> orderDetails = cartItems.stream()
                .map(cartItem -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrder(order);
                    orderDetail.setProduct(cartItem.getProduct());
                    orderDetail.setQuantity(cartItem.getQuantity());
                    orderDetail.setPrice(cartItem.getProduct().getPrice());
                    return orderDetail;
                })
                .collect(Collectors.toList());

        order.setOrderDetails(orderDetails);
        orderRepository.save(order);

        // Clear the cart
        cartItems.clear();
    }
}
