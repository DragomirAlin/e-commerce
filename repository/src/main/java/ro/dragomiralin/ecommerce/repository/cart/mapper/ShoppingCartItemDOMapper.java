package ro.dragomiralin.ecommerce.repository.cart.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCartItem;

@Mapper(componentModel = "spring")
public interface ShoppingCartItemDOMapper {

    ShoppingCartItemDO toShoppingCartItemDO(ShoppingCartItem shoppingCartItem);

    ShoppingCartItem toShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);

    PageDO<ShoppingCartItemDO> toPageDO(Page<ShoppingCartItem> page);
}
