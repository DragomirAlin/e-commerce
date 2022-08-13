package ro.dragomiralin.ecommerce.infra.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.infra.persistence.domain.Product;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Long> add(@RequestBody Product product) {
        return ResponseEntity.ok(productService.add(product));
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Product>> list(@RequestParam int page, @RequestParam int size) {
        return ResponseEntity.ok(productService.list(PageRequest.of(page, size)));
    }
}
