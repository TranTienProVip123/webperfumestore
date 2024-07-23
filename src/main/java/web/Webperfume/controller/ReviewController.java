//package web.Webperfume.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import web.Webperfume.model.Product;
//import web.Webperfume.model.Review;
//import web.Webperfume.model.User;
//import web.Webperfume.service.ProductService;
//import web.Webperfume.service.ReviewService;
//import web.Webperfume.service.UserService;
//
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//@RequestMapping("/reviews")
//public class ReviewController {
//
//    @Autowired
//    private ReviewService reviewService;
//    @Autowired
//    private ProductService productService;
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/add")
//    public String showReviewForm(@RequestParam("productId") Long productId, Model model, Principal principal) {
//        Product product = productService.findById(productId); // Fetch product based on product ID
//        model.addAttribute("product", product);
//        model.addAttribute("review", new Review()); // Empty review object for form
//        model.addAttribute("currentUser", userService.findByUsername(principal.getName())); // Current user
//        return "products/user-review"; // The view where users can submit their review
//    }
//
//    @PostMapping("/submit")
//    public String submitReview(@RequestParam("productId") Long productId,
//                               @RequestParam("rating") int rating,
//                               @RequestParam("comment") String comment,
//                               Principal principal) {
//        User user = userService.findByUsername(principal.getName());
//        if (user != null) {
//            reviewService.saveReview(productId, user.getId(), rating, comment);
//        }
//        return "redirect:/products";
//    }
//}
//
//
