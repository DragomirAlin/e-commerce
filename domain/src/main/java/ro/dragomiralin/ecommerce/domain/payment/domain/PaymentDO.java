package ro.dragomiralin.ecommerce.domain.payment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ro.dragomiralin.ecommerce.domain.order.domain.OrderDO;

import java.math.BigDecimal;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class PaymentDO {
    private long id;
    private BigDecimal amount;
    private PaymentDOCurrency currency;
    private PaymentDOGateway gateway;
    private PaymentDOStatus status;
    private OrderDO order;
}
