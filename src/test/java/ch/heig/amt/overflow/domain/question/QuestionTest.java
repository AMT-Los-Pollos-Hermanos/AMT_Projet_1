package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testBuilder() {
        User u = User.builder().build();
        Question q = Question.builder()
                .author(u)
                .title("Hello World")
                .content("The content")
                .build();
        assertNotNull(q.getId());
        assertSame(u, q.getAuthor());
        assertEquals("Hello World", q.getTitle());
        assertEquals("The content", q.getContent());
        assertNull(q.getCreatedAt());
        assertNull(q.getUpdatedAt());
    }

}