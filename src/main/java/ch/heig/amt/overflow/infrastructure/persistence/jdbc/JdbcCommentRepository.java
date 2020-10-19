package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.comment.Comment;
import ch.heig.amt.overflow.domain.comment.CommentId;
import ch.heig.amt.overflow.domain.comment.ICommentRepository;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcCommentRepository")
public class JdbcCommentRepository implements ICommentRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public Collection<Comment> findByMainContentId(MainContentId mainContentId) {
        // TODO implement
        return null;
    }

    @Override
    public void save(Comment entity) {
        // TODO implement

    }

    @Override
    public void remove(CommentId id) {
        // TODO implement

    }

    @Override
    public Optional<Comment> findById(CommentId id) {
        // TODO implement
        return Optional.empty();
    }

    @Override
    public Collection<Comment> findAll() {
        // TODO implement
        return null;
    }
}
