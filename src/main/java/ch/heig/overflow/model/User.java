package ch.heig.overflow.model;

import lombok.Getter;
import lombok.Setter;

public class User {
    @Getter
    @Setter
    private int id;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;
}
