package ro.dragomiralin.ecommerce.controller.middleware;

import lombok.AllArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

        if ((springAuth.getPrincipal() instanceof KeycloakPrincipal)) {
            UserDTO userDTO = UserDTO.map(
                    KeycloakPrincipal.class.cast(springAuth.getPrincipal()).getKeycloakSecurityContext());

            return userService.findBySub(userDTO.getSub())
                    .map(userDTOMapper::toUserDTO)
                    .orElseGet(() -> {
                        var userDO = userDTOMapper.toUserDO(userDTO);
                        var id = userService.create(userDO);
                        return userDTOMapper.toUserDTO(userService.get(id));
                    });
        }

        // Testing use case
        if (springAuth instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) springAuth;
            return UserDTO.builder()
                    .sub(auth.getName())
                    .firstName(auth.getName())
                    .email("test@cc")
                    .build();
        }

        return null;
    }
}
