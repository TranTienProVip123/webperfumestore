package web.Webperfume.service;


import web.Webperfume.model.CartItem;
import web.Webperfume.model.Order;
import web.Webperfume.model.OrderDetail;
import web.Webperfume.repository.OrderDetailRepository;
import web.Webperfume.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CartService cartService; // Assuming you have a CartService

    @Transactional
    public Order createOrder(String customerName, String shippingAddress, String notes, String phoneNumber, String email, String paymentMethod, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCustomerName(customerName);
        order.setNotes(notes);
        order.setPhoneNumber(phoneNumber);
        order.setEmail(email);
        order.setPaymentMethod(paymentMethod);
        order.setShippingAddress(shippingAddress);
        order.setOrderDate(LocalDateTime.now()); // Set the current date and time



        order = orderRepository.save(order);
        for (CartItem item : cartItems) {
            OrderDetail detail = new OrderDetail();
            detail.setOrder(order);
            detail.setProduct(item.getProduct());
            detail.setQuantity(item.getQuantity());
            detail.setPrice(item.getProduct().getPrice());
            orderDetailRepository.save(detail);
        }
        // Optionally clear the cart after order placement
        cartService.clearCart();
        return order;
    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    private Order getCurrentOrder() {
        // Logic to get the current order, create a new one if it doesn't exist
        // This is just a placeholder, you would need to implement this method
        return new Order();
    }

}
