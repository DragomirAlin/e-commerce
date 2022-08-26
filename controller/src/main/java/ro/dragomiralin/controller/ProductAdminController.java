package ro.dragomiralin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.domain.service.CategoryService;
import ro.dragomiralin.ecommerce.domain.service.ProductService;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.CustomResponse;
import ro.dragomiralin.ecommerce.infra.api.rest.dto.ProductCreateReq;
import ro.dragomiralin.ecommerce.infra.api.rest.mapper.ProductMapper;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
