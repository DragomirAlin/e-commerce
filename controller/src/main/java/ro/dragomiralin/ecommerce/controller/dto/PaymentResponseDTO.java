package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

@Builder
public record PaymentResponseDTO(
        String paymentExternalId,
        String paymentUri,
        boolean success,
        String errorMessage) {
}
