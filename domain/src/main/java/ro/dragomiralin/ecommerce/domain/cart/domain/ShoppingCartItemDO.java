package ro.dragomiralin.ecommerce.domain.cart.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class ShoppingCartItemDO {
    private long id;
    private UserDO userDO;
    private long productId;
    private Integer quantity;
}
