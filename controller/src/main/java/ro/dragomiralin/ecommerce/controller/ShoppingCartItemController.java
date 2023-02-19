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
import ro.dragomiralin.ecommerce.controller.mapper.UserDTOMapper;
import ro.dragomiralin.ecommerce.controller.request.CreateShoppingCartItem;
import ro.dragomiralin.ecommerce.controller.request.CustomResponse;
import ro.dragomiralin.ecommerce.domain.cart.ShoppingCartItemService;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartItemController implements BaseController {
    private final UserDTOMapper userDTOMapper;
    private final ShoppingCartItemDTOMapper mapper;
    private final ShoppingCartItemService shoppingCartItemService;

    @PostMapping
    public ResponseEntity<CustomResponse<ShoppingCartItemDTO>> create(@AuthenticationPrincipal UserDTO userDTO, @RequestBody CreateShoppingCartItem createShoppingCartItem) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.create(userDO, mapper.toShoppingCartItemDO(createShoppingCartItem));
        return ResponseEntity.ok(CustomResponse.single(mapper.toShoppingCartItemDTO(cart)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<ShoppingCartItemDTO>> get(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.get(userDO, userDTO.getId());
        return ResponseEntity.ok(CustomResponse.single(mapper.toShoppingCartItemDTO(cart)));
    }

    @GetMapping
    public ResponseEntity<CustomResponse<List<ShoppingCartItemDTO>>> list(@AuthenticationPrincipal UserDTO userDTO, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "50") int size) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        var cart = shoppingCartItemService.list(userDO, page, size);
        var list = ListResponse.build(mapper.toPageDTO(cart));
        return ResponseEntity.ok(CustomResponse.list(list));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Void>> delete(@AuthenticationPrincipal UserDTO userDTO, @PathVariable long id) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        shoppingCartItemService.delete(userDO, id);
        return ResponseEntity.ok(CustomResponse.empty());
    }

    @PostMapping("/checkout")
    public ResponseEntity<CustomResponse<Void>> checkout(@AuthenticationPrincipal UserDTO userDTO) {
        var userDO = userDTOMapper.toUserDO(userDTO);

        shoppingCartItemService.checkout(userDO);
        return ResponseEntity.ok(CustomResponse.empty());
    }

}
