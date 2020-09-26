package ch.heig.amt.overflow.application.auth;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RegisterCommand {

    String username;
    String firstName;
    String lastName;
    String email;
    String clearTextPassword;

}
