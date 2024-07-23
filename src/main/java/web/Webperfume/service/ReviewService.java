//package web.Webperfume.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import web.Webperfume.model.Product;
//import web.Webperfume.model.Review;
//import web.Webperfume.model.User;
//import web.Webperfume.repository.ReviewRepository;
//import web.Webperfume.service.ProductService;
//import web.Webperfume.service.UserService;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@Service
//public class ReviewService {
//
//    @Autowired
//    private ReviewRepository reviewRepository;
//
//    @Autowired
//    private ProductService productService;
//
//    @Autowired
//    private UserService userService;
//
//    public List<Review> getReviewsByProductId(Long productId) {
//        return reviewRepository.findByProductId(productId);
//    }
//
//    public void saveReview(Long productId, Long userId, int rating, String comment) {
//        Product product = productService.findById(productId);
//        User user = userService.findById(userId);
//
//        if (product != null && user != null) {
//            Review review = new Review(product, user, rating, comment, LocalDateTime.now());
//            reviewRepository.save(review);
//        }
//    }
//    public List<Review> getAllReviews() {
//        return reviewRepository.findAll();
//    }
//}
