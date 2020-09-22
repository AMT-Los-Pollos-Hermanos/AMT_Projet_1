package ch.heig.overflow.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginCommand {

    private String email;

    private String password;
}
