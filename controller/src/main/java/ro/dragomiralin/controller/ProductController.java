package ro.dragomiralin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ListResponse;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity<CustomResponse<List<Product>>> list(@RequestParam int page, @RequestParam int size) {
        var productsPage = productService.list(PageRequest.of(page, size));
        var customResponse = CustomResponse.list(ListResponse.build(productsPage));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Product>> get(@PathVariable long id) {
        var product = productService.get(id);
        var customResponse = CustomResponse.single(product);
        return ResponseEntity.ok(customResponse);
    }

}
