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
    private long id;
    private long userId;
    private long productId;
    private Integer quantity;
}
