/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.message;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlashMessageTest {

    @Test
    void testDefaultValue() {
        FlashMessage message = FlashMessage.builder().message("Coucou").build();
        assertEquals("success", message.getType());
    }

    @Test
    void testSetters() {
        FlashMessage message = FlashMessage.builder().type("danger").message("Coucou").build();
        assertEquals("Coucou", message.getMessage());
        assertEquals("danger", message.getType());
    }

}