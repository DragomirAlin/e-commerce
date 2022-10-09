package ro.dragomiralin.ecommerce.domain.common.error;

public class ShoppingCartItemException extends RuntimeException {

    public ShoppingCartItemException(String message) {
        super(message);
    }
}
