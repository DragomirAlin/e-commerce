package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.OrderItemDTO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;

@Mapper(componentModel = "spring")
public interface OrderItemDTOMapper {

    OrderItemDTO toOrderItemDTO(OrderItemDO orderItemDO);

}
