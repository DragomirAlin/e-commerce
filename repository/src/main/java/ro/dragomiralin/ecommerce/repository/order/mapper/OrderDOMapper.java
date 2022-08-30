package ro.dragomiralin.ecommerce.repository.order.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderDOMapper {

    OrderDO toOrderDO(Order order);

    Order toOrder(OrderDO orderDO);
}
