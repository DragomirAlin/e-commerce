package ro.dragomiralin.ecommerce.repository.cart.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.repository.cart.entity.ShoppingCartItem;
import ro.dragomiralin.ecommerce.repository.user.mapper.UserDOMapper;

@Mapper(componentModel = "spring", uses = UserDOMapper.class)
public interface ShoppingCartItemDOMapper {

    ShoppingCartItemDO toShoppingCartItemDO(ShoppingCartItem shoppingCartItem);

    @Mapping(target = "user", source = "shoppingCartItemDO.userDO")
    ShoppingCartItem toShoppingCartItem(ShoppingCartItemDO shoppingCartItemDO);

    PageDO<ShoppingCartItemDO> toPageDO(Page<ShoppingCartItem> page);
}
