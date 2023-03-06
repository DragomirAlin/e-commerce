package ro.dragomiralin.ecommerce.domain.payment.domain;

import lombok.Builder;

@Builder
public record CreatedPaymentDO(
        PaymentDO paymentDO,
        PaymentResponseDO paymentResponseDO) {

}
