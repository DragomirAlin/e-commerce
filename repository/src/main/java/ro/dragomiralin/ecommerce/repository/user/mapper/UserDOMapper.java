package ro.dragomiralin.ecommerce.repository.user.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.domain.user.UserDO;
import ro.dragomiralin.ecommerce.repository.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserDOMapper {

    User toUser(UserDO userDO);

    UserDO toUserDO(User user);
}
