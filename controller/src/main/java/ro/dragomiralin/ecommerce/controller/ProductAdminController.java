package ro.dragomiralin.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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

import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/admin/product")
@RequiredArgsConstructor
@Tag(name = "Product Admin", description = "Product Admin API")
public class ProductAdminController implements BaseController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductDTOMapper productMapper;


    @Operation(summary = "Create product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Created the product"),
    })
    @PostMapping
    public ResponseEntity<CustomResponse<ProductDTO>> add(@AuthenticationPrincipal UserDTO userDTO, @RequestBody ProductCreateReq productCreateReq) {
        var categories = productCreateReq.getCategories().stream()
                .map(categoryService::get)
                .collect(Collectors.toList());

        var productDO = productMapper.toProduct(productCreateReq, categories);
        var createdProduct = productService.add(productDO);

        return ResponseEntity.ok(CustomResponse.single(productMapper.toProductDTO(createdProduct)));
    }

    @Operation(summary = "Delete product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the product"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        productService.delete(id);
        return ResponseEntity.ok(CustomResponse.empty());
    }
}
