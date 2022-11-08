package ro.dragomiralin.ecommerce.domain.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItemDO {
    private long id;
    private int quantity;
    private OrderDO order;
    private ProductDO product;
}
