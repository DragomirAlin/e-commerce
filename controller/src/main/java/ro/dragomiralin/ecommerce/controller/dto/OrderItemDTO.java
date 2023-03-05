package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

@Builder
public record OrderItemDTO(long id,
                           int quantity,
                           OrderDTO order,
                           ProductDTO product) {
}
