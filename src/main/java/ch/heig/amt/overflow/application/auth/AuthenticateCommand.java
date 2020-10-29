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
public class AuthenticateCommand {

    String username;
    String clearTextPassword;

}
