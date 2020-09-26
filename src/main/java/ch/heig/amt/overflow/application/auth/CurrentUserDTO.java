package ch.heig.amt.overflow.application.auth;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {

    String username;
    String firstName;
    String lastName;
    String email;

}
