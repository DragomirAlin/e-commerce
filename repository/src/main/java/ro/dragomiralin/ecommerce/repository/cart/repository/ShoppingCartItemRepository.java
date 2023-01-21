package ro.dragomiralin.ecommerce.repository.cart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCartItem;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingCartItemRepository extends JpaRepository<ShoppingCartItem, Long> {
    List<ShoppingCartItem> findAllByUser(User user);

    Page<ShoppingCartItem> findAllByUser(User user, Pageable pageable);

    Optional<ShoppingCartItem> findByIdAndUser(User user, long id);
}
