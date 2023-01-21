package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.ProductDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.ProductDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.controller.request.ProductCreateReq;
import ro.dragomiralin.ecommerce.domain.category.CategoryService;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin/product")
@RequiredArgsConstructor
public class ProductAdminController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductDTOMapper productMapper;

    @PostMapping
    public ResponseEntity<CustomResponse<ProductDTO>> add(@AuthenticationPrincipal UserDTO userDTO, @RequestBody ProductCreateReq productCreateReq) {
        var categories = productCreateReq.getCategories().stream()
                .map(categoryService::get)
                .collect(Collectors.toList());

        var productDO = productMapper.toProduct(productCreateReq, categories);
        var createdProduct = productService.add(productDO);

        return ResponseEntity.ok(CustomResponse.single(productMapper.toProductDTO(createdProduct)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
