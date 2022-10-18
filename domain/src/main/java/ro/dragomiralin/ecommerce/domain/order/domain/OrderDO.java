package ro.dragomiralin.ecommerce.domain.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDO {
    private Long id;
    private OrderDOStatus status;
    private Date orderedDate;
    private List<OrderItemDO> orderItems;
    private Long userId;
    private String customerComments;
}
