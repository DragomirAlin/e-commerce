package ro.dragomiralin.ecommerce.domain.cart;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.List;

public interface ShoppingCartItemService {

    Long create(Long userId, ShoppingCartItemDO shoppingCartItemDO);

    ShoppingCartItemDO get(Long id);

    ShoppingCartItemDO get(Long id, Long userId);

    ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO);

    PageDO<ShoppingCartItemDO> list(Long userId, int page, int size);

    List<ShoppingCartItemDO> list(Long userId);

    void delete(Long userId, Long id);

    void checkout(Long userId);
}
