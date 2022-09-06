package ro.dragomiralin.ecommerce.repository.cart.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCart;

@Mapper(componentModel = "spring")
public interface ShoppingCartDOMapper {

    ShoppingCartDO toShoppingCartDO(ShoppingCart shoppingCart);

    ShoppingCart toShoppingCart(ShoppingCartDO shoppingCartDO);

    PageDO<ShoppingCartDO> toPageDO(Page<ShoppingCart> page);
}
