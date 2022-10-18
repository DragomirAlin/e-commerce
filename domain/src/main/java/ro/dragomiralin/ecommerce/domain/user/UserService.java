package ro.dragomiralin.ecommerce.domain.user;


import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.Optional;

public interface UserService {

    Long create(UserDO userDO);

    UserDO get(Long id);

    Optional<UserDO> findById(Long id);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findBySub(String sub);
}
