package ro.dragomiralin.ecommerce.domain.order.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    Long save(OrderDO orderDO);

    Optional<OrderDO> findById(Long id);

    Optional<OrderDO> findById(Long userId, Long id);

    OrderDO update(OrderDO orderDO);

    void delete(Long id);

    PageDO<OrderDO> list(Long userId, int page, int size);

    List<OrderDO> list(Long userId);
}
