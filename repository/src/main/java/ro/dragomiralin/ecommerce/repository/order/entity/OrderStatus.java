package ro.dragomiralin.ecommerce.repository.order.entity;

public enum OrderStatus {
    PENDING,
    PAYMENT_PENDING,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED
}
