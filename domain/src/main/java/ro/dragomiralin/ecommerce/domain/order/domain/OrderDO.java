package ro.dragomiralin.ecommerce.domain.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

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
    private List<OrderDOItem> orderItems;
    private long userId;
    private String customerComments;

}
