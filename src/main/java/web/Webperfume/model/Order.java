package web.Webperfume.model;


import java.time.LocalDateTime;
import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String shippingAddress;
    private String phoneNumber;
    private String email;
    private String notes;
    private String paymentMethod;

    @Column(name = "order_date") // Column name in the database
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmail() {
        return email;
    }

    public String getNotes() {
        return notes;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Transient
    private String orderDateFormatted;

    // Getter và Setter cho orderDateFormatted
    public String getOrderDateFormatted() {
        return orderDateFormatted;
    }

    public void setOrderDateFormatted(String orderDateFormatted) {
        this.orderDateFormatted = orderDateFormatted;
    }
}
