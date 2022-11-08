package ro.dragomiralin.ecommerce.repository.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCartItem;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findAllByUserId(long userId);

    Page<ShoppingCartItem> findAllByUserId(long userId, Pageable pageable);

    Optional<ShoppingCartItem> findByIdAndUserId(long id, long userId);
}
