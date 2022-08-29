package ro.dragomiralin.ecommerce.repository.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.order.entity.OrderItem;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {

}
