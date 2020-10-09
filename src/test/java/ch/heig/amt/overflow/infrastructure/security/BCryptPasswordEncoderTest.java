package ch.heig.amt.overflow.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import static org.junit.jupiter.api.Assertions.*;

class BCryptPasswordEncoderTest {

    private BCryptPasswordEncoder encoder;

    @BeforeEach
    public void setUp() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testHash() {
        String hash = encoder.hash("1234");
        assertTrue(BCrypt.checkpw("1234", hash));
    }

    @Test
    public void testVerify() {
        String hash = BCrypt.hashpw("1234", BCrypt.gensalt());
        assertTrue(encoder.verify("1234", hash));
    }

}