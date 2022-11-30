package ro.dragomiralin.ecommerce.controller;

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
public class ProductController {
    private final ProductDTOMapper mapper;
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<ProductDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var productsPageDO = productService.list(page, size);
        var productsPageDTO = mapper.toPageDTO(productsPageDO);
        var customResponse = CustomResponse.list(ListResponse.build(productsPageDTO));
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ProductDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var productDO = productService.get(id);
        var productDTO = mapper.toProductDTO(productDO);
        var customResponse = CustomResponse.single(productDTO);
        return ResponseEntity.ok(customResponse);
    }

}
