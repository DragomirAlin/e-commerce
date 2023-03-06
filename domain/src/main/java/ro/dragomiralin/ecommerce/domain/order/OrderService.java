package ro.dragomiralin.ecommerce.domain.order;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.List;

public interface OrderService {
    OrderDO create(UserDO user, OrderDO orderDO);

    PaymentResponseDO pay(UserDO user, long orderId);

    OrderDO get(long id);

    OrderDO get(UserDO user, long orderId);

    OrderDO update(OrderDO orderDO);

    void delete(UserDO user, long id);

    List<OrderDO> list(UserDO user);

    PageDO<OrderDO> list(UserDO user, int page, int size);

    void checkout(UserDO user, List<ShoppingCartItemDO> shoppingCartItems);
}
