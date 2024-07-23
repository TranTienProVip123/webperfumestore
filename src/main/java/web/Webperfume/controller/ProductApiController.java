package web.Webperfume.controller;




import web.Webperfume.model.Product;
import web.Webperfume.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/products")
public class ProductApiController {
    @Autowired
    private ProductService productService;

    @GetMapping
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    @ResponseBody
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        final Product updatedProduct = productService.addProduct(product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found on :: " + id));
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/management")
    public String showProductManagementPage() {
        return "/product";
    }
}

