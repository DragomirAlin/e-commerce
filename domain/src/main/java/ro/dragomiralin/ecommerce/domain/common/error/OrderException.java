package ro.dragomiralin.ecommerce.domain.common.error;

public class OrderException extends RuntimeException {
    public OrderException(String message) {
        super(message);
    }
}
