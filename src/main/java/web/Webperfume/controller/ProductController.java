package web.Webperfume.controller;

import web.Webperfume.model.Product;
//import web.Webperfume.model.Review;
import web.Webperfume.model.User;
import web.Webperfume.service.CategoryService;
import web.Webperfume.service.ProductService;
//import web.Webperfume.service.ReviewService;
import web.Webperfume.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

//    @Autowired
//    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products/products-list";
    }

    @GetMapping("/add")
    public String ShowAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/add-product";
    }

    @PostMapping("/add")
    public String addProduct(@Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "products/add-product";
        }

        // Dynamically set image URL based on product name or ID
        String imageUrl = "/static/uploads/" + product.getName() + ".jpg";
        product.setImageUrl(imageUrl);

        productService.addProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productService.getProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "products/update-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable("id") Long id, @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            product.setId(id);
            return "products/update-product";
        }
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProductById(id);
        return "redirect:/products";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable("id") Long id, Model model) {
        Product product = productService.findById(id);
//        List<Review> reviews = reviewService.getReviewsByProductId(id);
        model.addAttribute("product", product);
//        model.addAttribute("reviews", reviews);
        return "product-details";
    }

    @GetMapping("/male")
    public String getMaleProducts(Model model) {
        List<Product> maleProducts = productService.findByCategory("Nước Hoa Nam");
        model.addAttribute("products", maleProducts);
        return "products/products-list";
    }

    @GetMapping("/female")
    public String getFemaleProducts(Model model) {
        List<Product> femaleProducts = productService.findByCategory("Nước Hoa Nữ");
        model.addAttribute("products", femaleProducts);
        return "products/products-list";
    }

}
