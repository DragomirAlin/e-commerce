package ro.dragomiralin.ecommerce.domain.cart;

import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;

import java.util.List;

public interface ShoppingCartService {

    long create(ShoppingCartItemDO shoppingCartItemDO);

    ShoppingCartItemDO get(long id);

    ShoppingCartItemDO get(long userId, long id);

    ShoppingCartItemDO update(ShoppingCartItemDO shoppingCartItemDO);

    List<ShoppingCartItemDO> list(long userId);

    void delete(long userId, long id);

    void checkout(long userId);
}
