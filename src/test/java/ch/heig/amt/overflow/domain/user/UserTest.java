/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testBuilder() {
        User u = User.builder()
                .firstName("Gil")
                .lastName("Balsiger")
                .email("gil.balsiger@heig-vd.ch")
                .username("gil")
                .clearTextPassword("1234")
                .build();
        assertNotNull(u.getId());
        assertEquals("Gil", u.getFirstName());
        assertEquals("Balsiger", u.getLastName());
        assertEquals("gil.balsiger@heig-vd.ch", u.getEmail());
        assertEquals("gil", u.getUsername());
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