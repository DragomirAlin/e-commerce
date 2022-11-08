package ro.dragomiralin.ecommerce.domain.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.error.OrderException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.OrderService;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDOStatus;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;
import ro.dragomiralin.ecommerce.domain.product.ProductService;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderPort orderPort;
    private final ProductService productService;

    @Override
    public long create(long userId, OrderDO orderDO) {
        var req = OrderDO.builder()
                .userId(userId)
                .status(OrderDOStatus.PENDING)
                .orderItems(orderDO.getOrderItems())
                .customerComments(orderDO.getCustomerComments())
                .orderedDate(new Date())
                .build();

        return orderPort.save(req);
    }

    @Override
    public OrderDO get(long id) {
        return orderPort.findById(id)
                .orElseThrow(() -> new OrderException("Order not found"));
    }

    @Override
    public OrderDO get(long userId, long orderId) {
        return orderPort.findById(userId, orderId)
                .orElseThrow(() -> new OrderException("Order not found"));
    }

    @Override
    public OrderDO update(OrderDO orderDOReq) {
        var orderDO = get(orderDOReq.getId());
        orderDO.setStatus(orderDOReq.getStatus());
        orderDO.setOrderedDate(orderDOReq.getOrderedDate());
        orderDO.setCustomerComments(orderDOReq.getCustomerComments());
        orderDO.setOrderItems(orderDOReq.getOrderItems());
        return orderPort.update(orderDO);
    }

    @Override
    public void delete(long userId, long id) {
        var orderDO = get(userId, id);
        orderPort.delete(orderDO.getId());
    }

    @Override
    public List<OrderDO> list(long userId) {
        return orderPort.list(userId);
    }

    @Override
    public PageDO<OrderDO> list(long userId, int page, int size) {
        return orderPort.list(userId, page, size);
    }

    @Override
    public void checkout(long userId, List<ShoppingCartItemDO> shoppingCartItems) {
        var orderItems = shoppingCartItems.stream()
                .map(item -> OrderItemDO.builder()
                        .product(productService.get(item.getProductId()))
                        .quantity(item.getQuantity())
                        .build())
                .toList();

        var orderReq = OrderDO.builder()
                .orderItems(orderItems)
                .build();

        create(userId, orderReq);
    }
}
