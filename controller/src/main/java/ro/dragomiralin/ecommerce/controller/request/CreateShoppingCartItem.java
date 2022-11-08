package ro.dragomiralin.ecommerce.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class CreateShoppingCartItem {
    private long productId;
    private Integer quantity;
}
