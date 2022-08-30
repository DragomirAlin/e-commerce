package ro.dragomiralin.ecommerce.domain.cart.port;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPort {

    long save(ShoppingCartDO shoppingCartDO);

    Optional<ShoppingCartDO> findById(long id);

    boolean delete(long id);

    PageDO<ShoppingCartDO> list(int page, int size);

    List<ShoppingCartDO> list();
}
