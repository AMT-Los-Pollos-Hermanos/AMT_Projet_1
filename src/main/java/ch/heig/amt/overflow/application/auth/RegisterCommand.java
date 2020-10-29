/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.auth;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class RegisterCommand {

    String username;
    String firstName;
    String lastName;
    String email;
    String clearTextPassword;

}
