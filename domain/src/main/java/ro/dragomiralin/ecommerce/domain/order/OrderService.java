package ro.dragomiralin.ecommerce.domain.order;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.util.List;

public interface OrderService {
    long create(long userId, OrderDO orderDO);

    OrderDO get(long id);

    OrderDO get(long userId, long orderId);

    OrderDO update(OrderDO orderDO);

    void delete(long userId, long id);

    List<OrderDO> list(long userId);

    PageDO<OrderDO> list(long userId, int page, int size);

    void checkout(long userId, List<ShoppingCartItemDO> shoppingCartItems);
}
