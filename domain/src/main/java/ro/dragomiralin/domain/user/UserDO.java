package ro.dragomiralin.domain.user;

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
}
