package ch.heig.amt.overflow.infrastructure.security;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BCryptPasswordEncoderTest {

    @Test
    public void testHash() {
        String hash = BCryptPasswordEncoder.hash("1234");
        assertTrue(BCrypt.checkpw("1234", hash));
    }

    @Test
    public void testVerify() {
        String hash = BCrypt.hashpw("1234", BCrypt.gensalt());
        assertTrue(BCryptPasswordEncoder.verify("1234", hash));
    }

}