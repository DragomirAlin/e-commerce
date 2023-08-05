package ro.dragomiralin.ecommerce.domain.user.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDO {
    private long id;
    private String sub;
    private String firstName;
    private String lastName;
    private String email;

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public static class Fields {
        public static final String USER_ID = "userId";
        public static final String SUB = "sub";
        public static final String FIRST_NAME = "firstName";
        public static final String LAST_NAME = "lastName";
        public static final String EMAIL = "email";
    }

}
