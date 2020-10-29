/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.user;

import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.infrastructure.security.BCryptPasswordEncoder;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {

    @Builder.Default
    private UserId id = new UserId();

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    // return true if password match with the encrypted one
    public boolean authenticate(String clearTextPassword) {
        return BCryptPasswordEncoder.verify(clearTextPassword, encryptedPassword);
    }

    @Override
    public UserId getId() {
        return this.id;
    }

    @Override
    public User deepClone() {
        return this.toBuilder()
                .id(new UserId(id.toString()))
                .build();
    }

    public static class UserBuilder {

        // hash password and set it
        public UserBuilder clearTextPassword(String clearTextPassword) {
            if(clearTextPassword == null || clearTextPassword.isEmpty()) {
                throw new IllegalArgumentException("Password is mandatory");
            } else {
                encryptedPassword = BCryptPasswordEncoder.hash(clearTextPassword);
            }
            return this;
        }

    }

}
