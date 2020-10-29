/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserDTO {

    UserId id;
    String username;
    String firstName;
    String lastName;
    String email;

}
