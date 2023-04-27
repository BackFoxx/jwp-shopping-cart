package cart.controller;

import cart.entity.Product;
import cart.repository.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String productList(final Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "admin";
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody @Valid Product product) {
        long id = productRepository.save(product).getId();
        return ResponseEntity.created(URI.create("/admin/" + id)).build();
    }

    @PatchMapping("/edit")
    public ResponseEntity<Void> editProduct(@RequestBody Product product) {
        productRepository.update(product);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
