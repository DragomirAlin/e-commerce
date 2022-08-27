package ro.dragomiralin.ecommerce.controller.mapper;

import org.mapstruct.Mapper;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.domain.user.UserDO;

@Mapper(componentModel = "spring")
public interface UserDTOMapper {

    UserDTO toUserDTO(UserDO userDO);
}
