package ch.heig.amt.overflow.domain.user;

import ch.heig.amt.overflow.domain.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {

    Optional<User> findByUsername(String username);

}
