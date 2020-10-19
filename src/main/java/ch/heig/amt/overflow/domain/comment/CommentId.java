package ch.heig.amt.overflow.domain.comment;

import ch.heig.amt.overflow.domain.ContentId;

import java.util.UUID;

public class CommentId extends ContentId {
    public CommentId() {
        super();
    }

    public CommentId(String id) {
        super(id);
    }

    public CommentId(UUID id) {
        super(id);
    }
}
