package ro.dragomiralin.ecommerce.domain.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class UserDO {
    private Long id;
    private String sub;
    private String firstName;
    private String lastName;
    private String email;
}
