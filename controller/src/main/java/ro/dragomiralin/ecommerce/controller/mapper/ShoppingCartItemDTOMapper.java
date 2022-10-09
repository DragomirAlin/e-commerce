package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.PageDTO;
import ro.dragomiralin.ecommerce.controller.dto.ProductDTO;
import ro.dragomiralin.ecommerce.controller.dto.ShoppingCartItemDTO;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.product.domain.ProductDO;

@Mapper(componentModel = "spring")
public interface ShoppingCartItemDTOMapper {

    ShoppingCartItemDTO toShoppingCartItemDTO(ShoppingCartItemDO shoppingCartItemDO);

    ShoppingCartItemDO toShoppingCartItemDO(ShoppingCartItemDTO shoppingCartItemDTO);

    PageDTO<ShoppingCartItemDTO> toPageDTO(PageDO<ShoppingCartItemDO> page);

}
