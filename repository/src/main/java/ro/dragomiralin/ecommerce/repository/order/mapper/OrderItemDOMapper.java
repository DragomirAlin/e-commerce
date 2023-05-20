package ro.dragomiralin.ecommerce.repository.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.repository.order.entity.OrderItem;
import ro.dragomiralin.ecommerce.repository.product.mapper.ProductDOMapper;

@Mapper(componentModel = "spring", uses = {OrderDOMapper.class, ProductDOMapper.class})
public interface OrderItemDOMapper {

    OrderItemDO toOrderItemDO(OrderItem orderItem);

    @Mapping(target = "order", source = "orderItemDO.orderDO")
    @Mapping(target = "product", source = "orderItemDO.productDO")
    OrderItem toOrderItem(OrderItemDO orderItemDO);
}
