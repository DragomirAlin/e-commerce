package ro.dragomiralin.ecommerce.repository.order.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ro.dragomiralin.ecommerce.domain.common.page.PageDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.repository.order.entity.Order;
import ro.dragomiralin.ecommerce.repository.order.entity.OrderItem;
import ro.dragomiralin.ecommerce.repository.product.mapper.ProductDOMapper;
import ro.dragomiralin.ecommerce.repository.user.mapper.UserDOMapper;

@Mapper(componentModel = "spring", uses = {ProductDOMapper.class, UserDOMapper.class})
public interface OrderBaseMapper {

    @Mapping(target = "productDO", source = "orderItem.product")
    @Mapping(target = "orderDO", ignore = true)
    OrderItemDO toOrderItemDO(OrderItem orderItem);

    @Mapping(target = "product", source = "orderItemDO.productDO")
    @Mapping(target = "order", source = "orderItemDO.orderDO")
    OrderItem toOrderItem(OrderItemDO orderItemDO);

    @Mapping(target = "userDO", source = "order.user")
    @Mapping(target = "orderItems", source = "order.items")
    OrderDO toOrderDO(Order order);

    @Mapping(target = "user", source = "orderDO.userDO")
    @Mapping(target = "items", source = "orderDO.orderItems")
    Order toOrder(OrderDO orderDO);

    PageDO<OrderDO> toPageDO(Page<Order> page);
}
