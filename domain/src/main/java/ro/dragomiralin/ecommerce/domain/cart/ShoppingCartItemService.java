package ro.dragomiralin.ecommerce.domain.cart;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;

import java.util.List;

public interface ShoppingCartItemService {

    ShoppingCartItemDO create(long userId, ShoppingCartItemDO shoppingCartItemDO);

    ShoppingCartItemDO get(long id);

    ShoppingCartItemDO get(long id, long userId);

    ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO);

    PageDO<ShoppingCartItemDO> list(long userId, int page, int size);

    List<ShoppingCartItemDO> list(long userId);

    void delete(long userId, long id);

    void checkout(long userId);
}
