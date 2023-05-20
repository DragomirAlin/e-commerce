package ro.dragomiralin.ecommerce.repository.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.order.entity.OrderItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    Optional<OrderItem> findByIdAndOrderId(long id, long orderId);

    List<OrderItem> findAllByOrderId(long orderId);

}
