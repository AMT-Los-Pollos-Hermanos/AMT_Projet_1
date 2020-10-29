/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.user;

import ch.heig.amt.overflow.domain.IRepository;

import java.util.Optional;

public interface IUserRepository extends IRepository<User, UserId> {

    Optional<User> findByUsername(String username);

}
