/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.answer;

import ch.heig.amt.overflow.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    @Test
    void testBuilder() {
        User u = User.builder().build();
        Answer a = Answer.builder()
                .author(u)
                .content("The content")
                .build();
        assertNotNull(a.getId());
        assertSame(u, a.getAuthor());
        assertEquals("The content", a.getContent());
        assertNull(a.getCreatedAt());
        assertNull(a.getUpdatedAt());
        assertEquals(0, a.getNbVotes());
    }

}