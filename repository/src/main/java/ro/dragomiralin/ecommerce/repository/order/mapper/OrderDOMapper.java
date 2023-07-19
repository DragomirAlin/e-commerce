package ro.dragomiralin.ecommerce.repository.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;
import ro.dragomiralin.ecommerce.repository.product.mapper.ProductDOMapper;
import ro.dragomiralin.ecommerce.repository.user.mapper.UserDOMapper;

@Mapper(componentModel = "spring", uses = {UserDOMapper.class, ProductDOMapper.class})
public interface OrderDOMapper {

    @Mapping(target = "orderItems", source = "order.items")
    OrderDO toOrderDO(Order order);

    @Mapping(target = "user", source = "orderDO.userDO")
    @Mapping(target = "items", source = "orderDO.orderItems")
    Order toOrder(OrderDO orderDO);

    PageDO<OrderDO> toPageDO(Page<Order> page);
}
