/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.infrastructure.security;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder {

    public static String hash(String clearTextPassword) {
        return BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
    }

    public static boolean verify(String clearTextPassword, String hash) {
        return BCrypt.checkpw(clearTextPassword, hash);
    }

}
