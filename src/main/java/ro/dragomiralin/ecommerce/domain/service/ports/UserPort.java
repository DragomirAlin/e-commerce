package ro.dragomiralin.ecommerce.domain.service.ports;

import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

import java.util.Optional;

public interface UserPort {

    long create(User user);

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);
}
