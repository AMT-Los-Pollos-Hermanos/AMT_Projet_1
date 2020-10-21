package ch.heig.amt.overflow.domain.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.status.VoteDown;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    @Test
    void testBuilder() {
        UserId u = new UserId();
        ContentId id = new ContentId();
        Vote v = Vote.builder()
                .contentId(id)
                .status(new VoteDown())
                .userId(u)
                .build();
        assertNotNull(v.getId());
        assertSame(id, v.getContentId());
        assertSame(u, v.getUserId());
        assertTrue(v.getStatus() instanceof VoteDown);
    }

}