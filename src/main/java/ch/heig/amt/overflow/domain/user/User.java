package ch.heig.amt.overflow.domain.user;

import ch.heig.amt.overflow.application.ServiceRegistry;
import ch.heig.amt.overflow.domain.IEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder(toBuilder = true)
public class User implements IEntity<User, UserId> {

    private UserId id;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @EqualsAndHashCode.Exclude
    private String encryptedPassword;

    public boolean authenticate(String clearTextPassword) {
        return ServiceRegistry.getServiceRegistry().getPasswordEncoder().verify(clearTextPassword, encryptedPassword);
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

        public UserBuilder clearTextPassword(String clearTextPassword) {
            if(clearTextPassword == null || clearTextPassword.isEmpty()) {
                throw new IllegalArgumentException("Password is mandatory");
            } else {
                encryptedPassword = ServiceRegistry.getServiceRegistry().getPasswordEncoder().hash(clearTextPassword);
            }
            return this;
        }

        public User build() {
            if(id == null) {
                id = new UserId();
            }
            if(username == null || username.isEmpty()) {
                throw new IllegalArgumentException("Username is mandatory");
            }
            if(encryptedPassword == null || encryptedPassword.isEmpty()) {
                throw new IllegalArgumentException("Password is mandatory");
            }
            if(firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("First name is mandatory");
            }
            if(lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last name is mandatory");
            }
            if(email == null || email.isEmpty()) {
                throw new IllegalArgumentException("Email is mandatory");
            }

            return new User(id, username, email, firstName, lastName, encryptedPassword);
        }

    }

}
