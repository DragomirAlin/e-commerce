package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderItemDO;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDOStatus;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private long id;
    private OrderDOStatus status;
    private Date orderedDate;
    private List<OrderItemDO> orderItems;
    private long userId;
    private String customerComments;
}
