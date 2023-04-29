package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDOStatus;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Builder
public record OrderDTO(
        long id,
        OrderDOStatus status,
        Instant orderedDate,
        List<OrderItemDO> orderItems,
        long userId,
        String customerComments) {
}