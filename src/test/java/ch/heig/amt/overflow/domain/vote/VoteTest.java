/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.vote;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.user.UserId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class VoteTest {

    @Test
    void testBuilder() {
        UserId u = new UserId();
        ContentId id = new ContentId();
        Vote v = Vote.builder()
                .contentId(id)
                .status(VoteStatus.DOWN)
                .userId(u)
                .build();
        assertNotNull(v.getId());
        assertSame(id, v.getContentId());
        assertSame(u, v.getUserId());
        assertSame(v.getStatus(), VoteStatus.DOWN);
    }

}