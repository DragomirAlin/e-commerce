package ro.dragomiralin.ecommerce.domain.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.error.OrderException;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.OrderItemService;
import ro.dragomiralin.ecommerce.domain.order.OrderService;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDOStatus;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;
import ro.dragomiralin.ecommerce.domain.payment.PaymentService;
import ro.dragomiralin.ecommerce.domain.payment.domain.CreatedPaymentDO;
import ro.dragomiralin.ecommerce.domain.payment.domain.PaymentResponseDO;
import ro.dragomiralin.ecommerce.domain.product.ProductService;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderPort orderPort;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final OrderItemService orderItemService;

    @Override
    public OrderDO create(UserDO userDO, OrderDO orderDO) {
        return orderPort.save(OrderDO.builder()
                .userDO(userDO)
                .status(OrderDOStatus.PENDING)
                .customerComments(orderDO.getCustomerComments())
                .orderedDate(Instant.now())
                .build());
    }

    @Override
    public PaymentResponseDO pay(UserDO user, long orderId) {
        OrderDO orderDO = get(user, orderId);

        if (orderDO.getStatus() != OrderDOStatus.PENDING) {
            throw new OrderException("Order is already processed");
        }

        if (orderDO.getOrderItems() == null || orderDO.getOrderItems().isEmpty()) {
            throw new OrderException("Order has no items");
        }

        CreatedPaymentDO createdPaymentDO = paymentService.createPayment(user, orderDO);
        orderDO.setStatus(OrderDOStatus.PAYMENT_PENDING);
        orderPort.update(orderDO);
        return createdPaymentDO.paymentResponseDO();
    }

    @Override
    public OrderDO get(long id) {
        return orderPort.findById(id)
                .orElseThrow(() -> new OrderException("Order not found"));
    }

    @Override
    public OrderDO get(UserDO user, long orderId) {
        return orderPort.findById(user.getId(), orderId)
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
    public void delete(UserDO user, long id) {
        var orderDO = get(user, id);
        orderPort.delete(orderDO.getId());
    }

    @Override
    public List<OrderDO> list(UserDO user) {
        return orderPort.list(user.getId());
    }

    @Override
    public PageDO<OrderDO> list(UserDO user, int page, int size) {
        return orderPort.list(user.getId(), page, size);
    }

    @Override
    public void checkout(UserDO user, List<ShoppingCartItemDO> shoppingCartItems) {
        OrderDO orderDO = OrderDO.builder()
                .userDO(user)
                .customerComments("No comments")
                .orderedDate(Instant.now())
                .build();


        OrderDO createdOrder = create(user, orderDO);
        shoppingCartItems.forEach(item -> orderItemService.create(OrderItemDO.builder()
                .orderDO(createdOrder)
                .productDO(productService.get(item.getProductId()))
                .quantity(item.getQuantity())
                .build()));
    }
}
