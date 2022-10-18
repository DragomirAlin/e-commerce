package ro.dragomiralin.ecommerce.domain.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDO {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
}
