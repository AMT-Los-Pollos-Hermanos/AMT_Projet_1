package ch.heig.amt.overflow.infrastructure.security;

import ch.heig.amt.overflow.domain.IPasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptPasswordEncoder implements IPasswordEncoder {

    public String hash(String clearTextPassword) {
        return BCrypt.hashpw(clearTextPassword, BCrypt.gensalt());
    }

    public boolean verify(String clearTextPassword, String hash) {
        return BCrypt.checkpw(clearTextPassword, hash);
    }

}
