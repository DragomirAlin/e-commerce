package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.OrderDTO;
import ro.dragomiralin.ecommerce.controller.dto.PageDTO;
import ro.dragomiralin.ecommerce.controller.dto.ShoppingCartItemDTO;
import ro.dragomiralin.ecommerce.domain.cart.domain.ShoppingCartItemDO;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

@Mapper(componentModel = "spring", uses = {OrderItemDTOMapper.class})
public interface OrderDTOMapper {

    OrderDTO toOrderDTO(OrderDO orderDO);

    OrderDO toOrderDO(OrderDTO orderDTO);

    PageDTO<OrderDTO> toPageDTO(PageDO<OrderDO> page);

}
