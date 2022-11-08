package ro.dragomiralin.ecommerce.domain.user;


import ro.dragomiralin.ecommerce.domain.user.domain.UserDO;

import java.util.Optional;

public interface UserService {

    UserDO create(UserDO userDO);

    UserDO get(long id);

    Optional<UserDO> findById(long id);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findBySub(String sub);
}
