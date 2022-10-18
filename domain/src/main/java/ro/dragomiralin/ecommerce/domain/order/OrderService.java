package ro.dragomiralin.ecommerce.domain.order;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.util.List;

public interface OrderService {
    Long create(Long userId, OrderDO orderDO);

    OrderDO get(Long id);

    OrderDO get(Long userId, Long orderId);

    OrderDO update(OrderDO orderDO);

    void delete(Long userId, Long id);

    List<OrderDO> list(Long userId);

    PageDO<OrderDO> list(Long userId, int page, int size);

    void checkout(Long userId, List<ShoppingCartItemDO> shoppingCartItems);
}
