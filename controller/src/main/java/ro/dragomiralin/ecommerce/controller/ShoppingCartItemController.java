package ro.dragomiralin.ecommerce.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.ShoppingCartItemDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.ShoppingCartItemDTOMapper;
import ro.dragomiralin.ecommerce.controller.mapper.UserDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CreateShoppingCartItem;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.cart.ShoppingCartItemService;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
@Tag(name = "Shopping Cart", description = "Shopping Cart API")
public class ShoppingCartItemController implements BaseController {
    private final UserDTOMapper userDTOMapper;
    private final ShoppingCartItemDTOMapper mapper;
    private final ShoppingCartItemService shoppingCartItemService;

    @Operation(summary = "Add product to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product added to cart"),
    })
    @PostMapping
    public ResponseEntity<CustomResponse<ShoppingCartItemDTO>> create(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CreateShoppingCartItem createShoppingCartItem) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.create(userDO, mapper.toShoppingCartItemDO(createShoppingCartItem));
        return ResponseEntity.ok(CustomResponse.single(mapper.toShoppingCartItemDTO(cart)));
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ShoppingCartItemDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.get(userDO, userDTO.getId());
        return ResponseEntity.ok(CustomResponse.single(mapper.toShoppingCartItemDTO(cart)));
    }

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the products"),
    })
    @GetMapping
    public ResponseEntity<CustomResponse<List<ShoppingCartItemDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.list(userDO, page, size);
        var list = ListResponse.build(mapper.toPageDTO(cart));
        return ResponseEntity.ok(CustomResponse.list(list));
    }

    @Operation(summary = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        shoppingCartItemService.delete(userDO, id);
        return ResponseEntity.ok(CustomResponse.empty());
    }

    @Operation(summary = "Checkout cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart checked out"),
    })
    @PostMapping("/checkout")
    public ResponseEntity<CustomResponse<Void>> checkout(@AuthenticationPrincipal UserDTO userDTO) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        shoppingCartItemService.checkout(userDO);
        return ResponseEntity.ok(CustomResponse.empty());
    }

}
