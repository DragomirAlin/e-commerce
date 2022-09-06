package ro.dragomiralin.ecommerce.repository.order.mapper;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderDOMapper {

    OrderDO toOrderDO(Order order);

    Order toOrder(OrderDO orderDO);

    PageDO<OrderDO> toPageDO(Page<Order> page);
}
