package ro.dragomiralin.ecommerce.domain.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDO {
    private long id;
    private UserDO userDO;
    private OrderDOStatus status;
    private Instant orderedDate;
    private String customerComments;
    private List<OrderItemDO> orderItems;
}
