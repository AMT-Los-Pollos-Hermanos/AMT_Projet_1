package ch.heig.amt.overflow.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testAuthenticate() {
        User user1 = User.builder().clearTextPassword("1234").build();
        assertTrue(user1.authenticate("1234"));
        assertFalse(user1.authenticate("4321"));
        assertFalse(user1.authenticate(""));
        assertFalse(user1.authenticate(null));
    }

    @Test
    void testClearTextPassword() {
        User user1 = User.builder().clearTextPassword("1234").build();
        assertNotEquals("1234", user1.getEncryptedPassword());
    }

}