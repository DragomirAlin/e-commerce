package ro.dragomiralin.ecommerce.domain.cart.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPort {

    ShoppingCartItemDO save(ShoppingCartItemDO shoppingCartItemDO);

    Optional<ShoppingCartItemDO> findById(long id);

    Optional<ShoppingCartItemDO> findByIdAndUserId(long id, long userId);

    void delete(long id);

    PageDO<ShoppingCartItemDO> list(long userId, int page, int size);

    List<ShoppingCartItemDO> list(long userId);

}
