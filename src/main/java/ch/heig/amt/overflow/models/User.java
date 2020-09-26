package ch.heig.amt.overflow.models;

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
