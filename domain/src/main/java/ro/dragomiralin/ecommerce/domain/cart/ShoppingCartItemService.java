package ro.dragomiralin.ecommerce.domain.cart;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.List;

public interface ShoppingCartItemService {

    ShoppingCartItemDO create(UserDO userDO, ShoppingCartItemDO shoppingCartItemDO);

    ShoppingCartItemDO get(long id);

    ShoppingCartItemDO get(UserDO userDO, long id);

    ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO);

    PageDO<ShoppingCartItemDO> list(UserDO userDO, int page, int size);

    List<ShoppingCartItemDO> list(UserDO userDO);

    void delete(UserDO userDO, long id);

    void checkout(UserDO userDO);
}
