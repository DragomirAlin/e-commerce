package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private long id;
    private String sub;
    private String firstName;
    private String lastName;
    private String email;
    private boolean verifiedEmail;

    public static final class Fields {
        public static final String SUB = "sub";
        public static final String FIRST_NAME = "given_name";
        public static final String LAST_NAME = "family_name";
        public static final String EMAIL = "email";
        public static final String EMAIL_VERIFIED = "email_verified";
        public static final String CLAIMS = "claims";
    }

    /**
     * Build a UserDTO from Token Auth Claims
     *
     * @param claims
     * @return
     */
    public static UserDTO from(Map<String, Object> claims) {
        var user = new UserDTO();

        if (claims.containsKey(Fields.SUB)) {
            user.setSub((String) claims.get(Fields.SUB));
        }

        if (claims.containsKey(Fields.FIRST_NAME)) {
            user.setFirstName((String) claims.get(Fields.FIRST_NAME));
        }

        if (claims.containsKey(Fields.LAST_NAME)) {
            user.setLastName((String) claims.get(Fields.LAST_NAME));
        }

        if (claims.containsKey(Fields.EMAIL)) {
            user.setEmail((String) claims.get(Fields.EMAIL));
        }

        if (claims.containsKey(Fields.EMAIL_VERIFIED)) {
            user.setVerifiedEmail((boolean) claims.get(Fields.EMAIL_VERIFIED));
        }

        return user;
    }


}
