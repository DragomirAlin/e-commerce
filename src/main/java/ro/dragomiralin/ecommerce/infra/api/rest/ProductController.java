package ro.dragomiralin.ecommerce.infra.api.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ListResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ProductCreateReq;
import ro.dragomiralin.ecommerce.infra.api.rest.mapper.ProductMapper;
import ro.dragomiralin.ecommerce.infra.persistence.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @PostMapping
    public ResponseEntity<CustomResponse<Long>> add(@RequestBody ProductCreateReq productCreateReq) {
        var categories = productCreateReq.getCategories().stream()
                .map(categoryService::get)
                .collect(Collectors.toList());

        var product = productMapper.toProduct(productCreateReq, categories);
        return ResponseEntity.ok(CustomResponse.single(productService.add(product)));
    }

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

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
