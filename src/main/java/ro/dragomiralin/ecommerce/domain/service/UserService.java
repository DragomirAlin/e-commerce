package ro.dragomiralin.ecommerce.domain.service;

import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

import java.util.Optional;

public interface UserService {

    long create(User user);

    Optional<User> findById(long id);

    Optional<User> findByEmail(String email);

    Optional<User> findBySub(String sub);
}
