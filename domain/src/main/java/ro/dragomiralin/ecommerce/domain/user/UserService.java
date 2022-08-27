package ro.dragomiralin.ecommerce.domain.user;


import java.util.Optional;

public interface UserService {

    long create(UserDO userDO);

    Optional<UserDO> findById(long id);

    Optional<UserDO> findByEmail(String email);

    Optional<UserDO> findBySub(String sub);
}
