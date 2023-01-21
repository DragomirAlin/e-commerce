package ro.dragomiralin.ecommerce.domain.cart.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPort {

    ShoppingCartItemDO save(ShoppingCartItemDO shoppingCartItemDO);

    Optional<ShoppingCartItemDO> findById(long id);

    Optional<ShoppingCartItemDO> findByIdAndUser(UserDO userDO, long id);

    void delete(long id);

    PageDO<ShoppingCartItemDO> list(UserDO userDO, int page, int size);

    List<ShoppingCartItemDO> list(UserDO userDO);

}
