package ro.dragomiralin.ecommerce.repository.order.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.port.OrderItemPort;
import ro.dragomiralin.ecommerce.repository.order.entity.OrderItem;
import ro.dragomiralin.ecommerce.repository.order.mapper.OrderBaseMapper;
import ro.dragomiralin.ecommerce.repository.order.repository.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemAdapter implements OrderItemPort {
    private final OrderBaseMapper mapper;
    private final OrderItemRepository orderItemsRepository;

    @Override
    public OrderItemDO save(OrderItemDO orderItemDO) {
        OrderItem orderItem = mapper.toOrderItem(orderItemDO);
        return mapper.toOrderItemDO(orderItemsRepository.save(orderItem));
    }

    @Override
    public Optional<OrderItemDO> findById(long id) {
        return orderItemsRepository.findById(id)
                .map(mapper::toOrderItemDO);
    }

    @Override
    public Optional<OrderItemDO> findById(long orderId, long id) {
        return orderItemsRepository.findByIdAndOrderId(id, orderId)
                .map(mapper::toOrderItemDO);
    }

    @Override
    public OrderItemDO update(OrderItemDO orderItemDO) {
        OrderItem orderItem = mapper.toOrderItem(orderItemDO);
        return mapper.toOrderItemDO(orderItemsRepository.save(orderItem));
    }

    @Override
    public void delete(long id) {
        orderItemsRepository.deleteById(id);
    }

    @Override
    public List<OrderItemDO> list(long orderId) {
        return orderItemsRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderItemDO)
                .toList();
    }
}
