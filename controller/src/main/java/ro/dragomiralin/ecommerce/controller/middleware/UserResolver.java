package ro.dragomiralin.ecommerce.controller.middleware;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.controller.mapper.UserDTOMapper;
import ro.dragomiralin.ecommerce.domain.user.UserService;


@Service
@AllArgsConstructor
public class UserResolver {
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;

    public UserDTO getUser() {
        Authentication springAuth = SecurityContextHolder.getContext().getAuthentication();

        if ((springAuth instanceof JwtAuthenticationToken)) {
            JwtAuthenticationToken jwt = (JwtAuthenticationToken) springAuth;
            var claims = jwt.getToken().getClaims();

            UserDTO user = UserDTO.from(claims);
            return userService.findBySub(user.getSub())
                    .map(userDTOMapper::toUserDTO)
                    .orElseGet(() -> {
                        var userDO = userDTOMapper.toUserDO(user);
                        var createdUser = userService.create(userDO);
                        return userDTOMapper.toUserDTO(createdUser);
                    });
        }

        // Testing use case
        if (springAuth instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) springAuth;
            return UserDTO.builder()
                    .id(1L)
                    .sub(auth.getName())
                    .firstName(auth.getName())
                    .email("test@cc")
                    .build();
        }

        return null;
    }
}
