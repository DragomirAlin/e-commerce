package ro.dragomiralin.ecommerce.controller.dto;

import lombok.Builder;

import java.util.Map;

@Builder
public record UserDTO(
        long id,
        String sub,
        String firstName,
        String lastName,
        String email,
        boolean verifiedEmail) {


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
        var builder = new UserDTOBuilder();

        if (claims.containsKey(Fields.SUB)) {
            builder.sub((String) claims.get(Fields.SUB));
        }

        if (claims.containsKey(Fields.FIRST_NAME)) {
            builder.firstName((String) claims.get(Fields.FIRST_NAME));
        }

        if (claims.containsKey(Fields.LAST_NAME)) {
            builder.lastName((String) claims.get(Fields.LAST_NAME));
        }

        if (claims.containsKey(Fields.EMAIL)) {
            builder.email((String) claims.get(Fields.EMAIL));
        }

        if (claims.containsKey(Fields.EMAIL_VERIFIED)) {
            builder.verifiedEmail((boolean) claims.get(Fields.EMAIL_VERIFIED));
        }

        return builder.build();
    }


}
