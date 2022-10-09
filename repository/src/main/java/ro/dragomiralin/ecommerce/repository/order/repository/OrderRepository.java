package ro.dragomiralin.ecommerce.repository.order.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserId(Long userId);

    Page<Order> findAllByUserId(Long userId, Pageable pageable);

    Optional<Order> findByUserIdAndId(Long userId, Long id);
}
