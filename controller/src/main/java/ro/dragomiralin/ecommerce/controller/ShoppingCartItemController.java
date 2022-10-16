package ro.dragomiralin.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ro.dragomiralin.ecommerce.controller.dto.ListResponse;
import ro.dragomiralin.ecommerce.controller.dto.ShoppingCartItemDTO;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.ShoppingCartItemDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CreateShoppingCartItem;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.cart.ShoppingCartItemService;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartItemController {
    private final ShoppingCartItemDTOMapper mapper;
    private final ShoppingCartItemService shoppingCartItemService;

    @PostMapping
    public ResponseEntity<CustomResponse<Long>> create(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CreateShoppingCartItem createShoppingCartItem) {
        var cart = shoppingCartItemService.create(userDTO.getId(), mapper.toShoppingCartItemDO(createShoppingCartItem));
        return ResponseEntity.ok(CustomResponse.single(cart));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ShoppingCartItemDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @RequestBody long id) {
        var cart = shoppingCartItemService.get(userDTO.getId(), id);
        return ResponseEntity.ok(CustomResponse.single(mapper.toShoppingCartItemDTO(cart)));
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<ShoppingCartItemDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var cart = shoppingCartItemService.list(userDTO.getId(), page, size);
        var list = ListResponse.build(mapper.toPageDTO(cart));
        return ResponseEntity.ok(CustomResponse.list(list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @RequestBody long id) {
        shoppingCartItemService.delete(userDTO.getId(), id);
        return ResponseEntity.ok(CustomResponse.empty());
    }

    @PostMapping("/checkout")
    public ResponseEntity<CustomResponse<Void>> checkout(@AuthenticationPrincipal UserDTO userDTO) {
        shoppingCartItemService.checkout(userDTO.getId());
        return ResponseEntity.ok(CustomResponse.empty());
    }

}
