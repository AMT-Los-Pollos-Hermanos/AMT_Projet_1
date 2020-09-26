package ch.heig.amt.overflow.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {

    private String email;

    private String password;
}
