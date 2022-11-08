package ro.dragomiralin.ecommerce.domain.user.port;

import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.Optional;

public interface UserPort {

    long create(UserDO user);

    Optional<UserDO> findById(long id);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findBySub(String sub);
}
