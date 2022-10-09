package ro.dragomiralin.ecommerce.domain.order.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.util.List;
import java.util.Optional;

public interface OrderPort {

    long save(OrderDO orderDO);

    Optional<OrderDO> findById(long id);

    void delete(long id);

    PageDO<OrderDO> list(int page, int size);

    List<OrderDO> list();
}
