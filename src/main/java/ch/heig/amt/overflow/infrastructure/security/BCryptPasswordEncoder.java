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
