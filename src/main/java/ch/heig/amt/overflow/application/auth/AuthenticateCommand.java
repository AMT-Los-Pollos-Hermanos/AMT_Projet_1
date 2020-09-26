package ch.heig.amt.overflow.application.auth;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AuthenticateCommand {

    String username;
    String clearTextPassword;

}
