package ch.heig.amt.overflow.infrastructure.persistence.memory;

import ch.heig.amt.overflow.domain.IPasswordEncoder;
import ch.heig.amt.overflow.domain.user.IUserRepository;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public class InMemoryUserRepository extends InMemoryRepository<User, UserId> implements IUserRepository {

    public InMemoryUserRepository(IPasswordEncoder encoder) {
        save(User.builder()
                .firstName("Gil")
                .lastName("Balsiger")
                .email("gil.balsiger@heig-vd.ch")
                .username("gil")
                .encryptedPassword(encoder.hash("gil"))
                .build());

        save(User.builder()
                .firstName("Julien")
                .lastName("Béguin")
                .email("julien.beguin@heig-vd.ch")
                .username("julien")
                .encryptedPassword(encoder.hash("julien"))
                .build());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        if (username != null && !username.isEmpty()) {
            Collection<User> usersWithUsername = findAll().stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
            if (usersWithUsername.size() == 0) {
                return Optional.empty();
            } else {
                return Optional.of(usersWithUsername.stream().findFirst().orElse(null));
            }
        } else {
            return Optional.empty();
        }
    }

}
