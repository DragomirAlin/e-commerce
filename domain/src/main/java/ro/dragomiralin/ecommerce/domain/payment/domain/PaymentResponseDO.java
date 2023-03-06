package ro.dragomiralin.ecommerce.domain.payment.domain;

import lombok.Builder;

@Builder
public record PaymentResponseDO(
        String paymentExternalId,
        String paymentUri,
        boolean success,
        String errorMessage) {
}
