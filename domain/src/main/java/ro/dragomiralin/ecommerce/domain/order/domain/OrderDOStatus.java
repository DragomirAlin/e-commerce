package ro.dragomiralin.ecommerce.domain.order.domain;

public enum OrderDOStatus {
    PENDING,
    PAYMENT_PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
