package ro.dragomiralin.ecommerce.domain.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.OrderService;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderPort orderPort;

    @Override
    public long create(OrderDO orderDO) {
        return 0;
    }

    @Override
    public OrderDO get(long id) {
        return null;
    }

    @Override
    public OrderDO get(long userId, long orderId) {
        return null;
    }

    @Override
    public OrderDO update(OrderDO orderDO) {
        return null;
    }

    @Override
    public void delete(long userId, long id) {

    }

    @Override
    public List<OrderDO> list(long userId) {
        return null;
    }

    @Override
    public PageDO<OrderDO> list(long userId, int page, int size) {
        return null;
    }

    @Override
    public void checkout(List<ShoppingCartItemDO> shoppingCartItems) {

    }
}
