package ro.dragomiralin.ecommerce.repository.order.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.port.OrderPort;
import ro.dragomiralin.ecommerce.repository.order.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderAdapter implements OrderPort {
    private final OrderRepository orderRepository;

    @Override
    public long save(OrderDO orderDO) {
        return 0;
    }

    @Override
    public Optional<OrderDO> findById(long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public PageDO<OrderDO> list(int page, int size) {
        return null;
    }

    @Override
    public List<OrderDO> list() {
        return null;
    }
}
