package ro.dragomiralin.ecommerce.repository.cart.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartDO;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCart;

@Mapper(componentModel = "spring")
public interface ShoppingCartDOMapper {

    ShoppingCartDO toShoppingCartDO(ShoppingCart shoppingCart);

    ShoppingCart toShoppingCart(ShoppingCartDO shoppingCartDO);
}
