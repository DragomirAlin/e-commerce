package ro.dragomiralin.ecommerce.infra.api.rest.middleware;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ro.dragomiralin.ecommerce.domain.service.UserService;
import ro.dragomiralin.ecommerce.infra.api.rest.error.ServiceException;
import ro.dragomiralin.ecommerce.infra.persistence.entity.User;

import static ro.dragomiralin.ecommerce.infra.persistence.entity.User.Fields.*;

@Service
@AllArgsConstructor
public class UserResolver {
    private final UserService userService;
    private final ObjectMapper mapper;

    public User getUser() {
        Authentication springAuth = SecurityContextHolder.getContext().getAuthentication();
        var principal = mapper.convertValue(springAuth.getPrincipal(), ObjectNode.class);

        if (principal.isEmpty() || !principal.has(CLAIMS)) {
            throw new ServiceException("Could not get the claims from the principal");
        }

        var claims = mapper.convertValue(principal.get(CLAIMS), ObjectNode.class);

        if (claims.isEmpty() || !claims.has(EMAIL)) {
            throw new ServiceException("Could not get the email from the claims principal");
        }

        var email = claims.get(EMAIL).asText();
        var userOptional = userService.findByEmail(email);

        if (userOptional.isEmpty()) {
            var id = userService.create(User.builder()
                    .sub(claims.has(SUB) ? claims.get(SUB).asText() : null)
                    .email(email)
                    .build());

            userOptional = userService.findById(id);
        }

        return userOptional
                .orElseThrow(() -> new ServiceException("Could not find user with email " + email));
    }
}
