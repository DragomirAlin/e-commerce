package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDTO {
    private long id;
    private UserDTO userDTO;
    private long productId;
    private Integer quantity;
}
