package ch.heig.amt.overflow.domain.comment;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void testBuilder() {
        User u = User.builder().build();
        MainContentId id = new MainContentId();
        Comment c = Comment.builder()
                .author(u)
                .content("The content")
                .mainContentId(new MainContentId())
                .build();
        assertNotNull(c.getId());
        assertSame(u, c.getAuthor());
        assertSame(id, c.getMainContentId());
        assertEquals("The content", c.getContent());
        assertNull(c.getCreatedAt());
        assertNull(c.getUpdatedAt());
        assertEquals(0, c.getNbVotes());
    }

}