//package web.Webperfume.model;
//
//import jakarta.persistence.*;
//
//import java.time.LocalDateTime;
//
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;
//
//    private int rating;
//    private String comment;
//    private LocalDateTime createdAt;
//
//    // Constructor không tham số
//    public Review() {
//    }
//
//    // Constructor có tham số
//    public Review(Product product, User user, int rating, String comment, LocalDateTime createdAt) {
//        this.product = product;
//        this.user = user;
//        this.rating = rating;
//        this.comment = comment;
//        this.createdAt = createdAt;
//    }
//
//    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//    public LocalDateTime getCreatedAt() {
//        return createdAt;
//    }
//
//    public void setCreatedAt(LocalDateTime createdAt) {
//        this.createdAt = createdAt;
//    }
//}
