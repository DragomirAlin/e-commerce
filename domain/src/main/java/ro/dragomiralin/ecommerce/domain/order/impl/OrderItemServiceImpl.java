package ro.dragomiralin.ecommerce.domain.order.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.order.OrderItemService;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.port.OrderItemPort;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemPort orderItemPort;

    @Override
    public OrderItemDO create(OrderItemDO orderItemDO) {
        return orderItemPort.save(orderItemDO);
    }
}
