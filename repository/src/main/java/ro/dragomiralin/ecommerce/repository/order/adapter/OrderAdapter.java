package ro.dragomiralin.ecommerce.repository.order.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;
import ro.dragomiralin.ecommerce.repository.order.mapper.OrderDOMapper;
import ro.dragomiralin.ecommerce.repository.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderAdapter implements OrderPort {
    private final OrderDOMapper mapper;
    private final OrderRepository orderRepository;

    @Override
    public Long save(OrderDO orderDO) {
        var order = mapper.toOrder(orderDO);
        return orderRepository.save(order).getId();
    }

    @Override
    public Optional<OrderDO> findById(Long id) {
        return orderRepository.findById(id)
                .map(mapper::toOrderDO);
    }

    @Override
    public Optional<OrderDO> findById(Long userId, Long id) {
        return orderRepository.findByUserIdAndId(userId, id)
                .map(mapper::toOrderDO);
    }

    @Override
    public OrderDO update(OrderDO orderDO) {
        var order = mapper.toOrder(orderDO);
        return mapper.toOrderDO(orderRepository.save(order));
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }

    @Override
    public PageDO<OrderDO> list(Long userId, int page, int size) {
        var orders = orderRepository.findAllByUserId(userId, PageRequest.of(page, size));
        return mapper.toPageDO(orders);
    }

    @Override
    public List<OrderDO> list(Long userId) {
        return orderRepository.findAllByUserId(userId)
                .stream()
                .map(mapper::toOrderDO)
                .toList();
    }
}
