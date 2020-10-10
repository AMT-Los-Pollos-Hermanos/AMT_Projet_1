package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {

    UserId id;
    String username;
    String firstName;
    String lastName;
    String email;

}
