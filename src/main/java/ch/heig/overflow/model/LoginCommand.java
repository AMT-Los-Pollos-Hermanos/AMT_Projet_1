package ch.heig.overflow.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
public class LoginCommand {
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String password;
}
