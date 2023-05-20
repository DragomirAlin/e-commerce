package ro.dragomiralin.ecommerce.domain.order.port;

import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;

import java.util.List;
import java.util.Optional;

public interface OrderItemPort {

    OrderItemDO save(OrderItemDO orderItemDO);

    Optional<OrderItemDO> findById(long id);

    Optional<OrderItemDO> findById(long orderId, long id);

    OrderItemDO update(OrderItemDO orderItemDO);

    void delete(long id);

    List<OrderItemDO> list(long orderId);
}
