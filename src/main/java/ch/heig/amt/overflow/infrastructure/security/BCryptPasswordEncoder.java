package ch.heig.amt.overflow.infrastructure.security;

import ch.heig.amt.overflow.domain.IPasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

public class BCryptPasswordEncoder {

    public static String hash(String clearTextPassword) {
        return BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
    }

    public static boolean verify(String clearTextPassword, String hash) {
        return BCrypt.checkpw(clearTextPassword, hash);
    }

}
