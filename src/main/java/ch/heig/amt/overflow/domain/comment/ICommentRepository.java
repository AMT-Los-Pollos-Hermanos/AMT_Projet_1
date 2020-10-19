package ch.heig.amt.overflow.domain.comment;

import ch.heig.amt.overflow.domain.IRepository;
import ch.heig.amt.overflow.domain.MainContentId;

import java.util.Collection;

public interface ICommentRepository extends IRepository<Comment, CommentId> {
    Collection<Comment> findByMainContentId(MainContentId mainContentId);
}
