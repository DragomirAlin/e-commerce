package ro.dragomiralin.ecommerce.domain.product.error;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
