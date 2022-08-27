package ro.dragomiralin.ecommerce.domain.user;

import java.util.Optional;

public interface UserPort {

    long create(UserDO user);

    Optional<UserDO> findById(long id);

    Optional<UserDO> findByEmail(String email);
}
