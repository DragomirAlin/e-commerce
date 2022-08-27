package ro.dragomiralin.ecommerce.domain.user.error;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
