package ro.dragomiralin.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.ProductDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.ProductDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.product.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@Tag(name = "Product", description = "Product API")
public class ProductController implements BaseController {
    private final ProductDTOMapper mapper;
    private final ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get all products"),
    })
    @GetMapping
    public ResponseEntity<CustomResponse<List<ProductDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var productsPageDO = productService.list(page, size);
        var productsPageDTO = mapper.toPageDTO(productsPageDO);
        var customResponse = CustomResponse.list(ListResponse.build(productsPageDTO));
        return ResponseEntity.ok(customResponse);
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ProductDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var productDO = productService.get(id);
        var productDTO = mapper.toProductDTO(productDO);
        var customResponse = CustomResponse.single(productDTO);
        return ResponseEntity.ok(customResponse);
    }

}
