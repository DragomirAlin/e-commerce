package ro.dragomiralin.ecommerce.controller.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.controller.dto.UserDTO;
import ro.dragomiralin.ecommerce.domain.common.error.ServiceException;
import ro.dragomiralin.ecommerce.controller.mapper.UserDTOMapper;
import ro.dragomiralin.ecommerce.domain.user.UserDO;
import ro.dragomiralin.ecommerce.domain.user.UserService;


@Service
@AllArgsConstructor
public class UserResolver {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final UserDTOMapper userDTOMapper;

    public UserDTO getUser() {
        Authentication springAuth = SecurityContextHolder.getContext().getAuthentication();
        var principal = objectMapper.convertValue(springAuth.getPrincipal(), ObjectNode.class);

        if (principal.isEmpty() || !principal.has(UserDTO.Fields.CLAIMS)) {
            throw new ServiceException("Could not get the claims from the principal");
        }

        var claims = objectMapper.convertValue(principal.get(UserDTO.Fields.CLAIMS), ObjectNode.class);

        if (claims.isEmpty() || !claims.has(UserDTO.Fields.EMAIL)) {
            throw new ServiceException("Could not get the email from the claims principal");
        }

        var email = claims.get(UserDTO.Fields.EMAIL).asText();
        var userOptional = userService.findByEmail(email);

        if (userOptional.isEmpty()) {
            var id = userService.create(UserDO.builder()
                    .sub(claims.has(UserDTO.Fields.SUB) ? claims.get(UserDTO.Fields.SUB).asText() : null)
                    .email(email)
                    .build());

            userOptional = userService.findById(id);
        }

        return userOptional
                .map(userDTOMapper::toUserDTO)
                .orElseThrow(() -> new ServiceException("Could not find user with email " + email));
    }
}
