package ro.dragomiralin.ecommerce.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

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

    public static final class Fields {
        public static final String SUB = "sub";
        public static final String FIRST_NAME = "first_name";
        public static final String LAST_NAME = "last_name";
        public static final String EMAIL = "email";
        public static final String CLAIMS = "claims";
    }
}
