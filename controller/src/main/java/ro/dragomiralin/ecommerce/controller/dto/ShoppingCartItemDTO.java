package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

@Builder
public record ShoppingCartItemDTO(
        long id,
        UserDTO userDTO,
        long productId,
        Integer quantity) {
}
