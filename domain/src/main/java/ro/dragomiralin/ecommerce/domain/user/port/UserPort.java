package ro.dragomiralin.ecommerce.domain.user.port;

import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.Optional;

public interface UserPort {

    Long create(UserDO user);

    Optional<UserDO> findById(Long id);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findBySub(String sub);
}
