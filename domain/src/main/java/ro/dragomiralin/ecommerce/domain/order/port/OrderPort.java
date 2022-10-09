package ro.dragomiralin.ecommerce.domain.order.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    long save(OrderDO orderDO);

    Optional<OrderDO> findById(long id);

    Optional<OrderDO> findById(long userId, long id);

    OrderDO update(OrderDO orderDO);

    void delete(long id);

    PageDO<OrderDO> list(long userId, int page, int size);

    List<OrderDO> list(long userId);
}
