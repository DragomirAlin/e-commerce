package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

@Builder
public record ShoppingCartItemDTO(
        long id,
        long productId,
        Integer quantity) {
}
