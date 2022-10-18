package ro.dragomiralin.ecommerce.domain.cart.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPort {

    Long save(ShoppingCartItemDO shoppingCartItemDO);

    Optional<ShoppingCartItemDO> findById(Long id);

    Optional<ShoppingCartItemDO> findByIdAndUserId(Long id, Long userId);

    void delete(Long id);

    PageDO<ShoppingCartItemDO> list(Long userId, int page, int size);

    List<ShoppingCartItemDO> list(Long userId);

}
