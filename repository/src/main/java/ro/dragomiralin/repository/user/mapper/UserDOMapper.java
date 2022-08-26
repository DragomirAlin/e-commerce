package ro.dragomiralin.repository.user.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.domain.user.UserDO;
import ro.dragomiralin.repository.user.entity.User;

@Mapper(componentModel = "spring")
public interface UserDOMapper {

    User toUser(UserDO userDO);

    UserDO toUserDO(User user);
}
