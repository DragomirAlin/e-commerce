package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.ProductDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.controller.request.ProductCreateReq;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;
import ro.dragomiralin.ecommerce.domain.product.ProductService;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductDTOMapper productMapper;

    @PostMapping
    public ResponseEntity<CustomResponse<Long>> add(@AuthenticationPrincipal UserDTO userDTO, @RequestBody ProductCreateReq productCreateReq) {
        var categories = productCreateReq.getCategories().stream()
                .map(categoryService::get)
                .collect(Collectors.toList());

        var product = productMapper.toProduct(productCreateReq, categories);
        return ResponseEntity.ok(CustomResponse.single(productService.add(product)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
