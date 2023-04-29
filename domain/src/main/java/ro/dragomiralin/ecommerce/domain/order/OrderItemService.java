package ro.dragomiralin.ecommerce.domain.order;

import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;

public interface OrderItemService {

    OrderItemDO create(OrderItemDO orderItemDO);
}
