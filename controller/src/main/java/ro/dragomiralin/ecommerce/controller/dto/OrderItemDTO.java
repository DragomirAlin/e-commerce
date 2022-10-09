package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private int quantity;
    private OrderDTO order;
    private ProductDTO product;
}
