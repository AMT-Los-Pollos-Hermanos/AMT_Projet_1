package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.IVoteRepository;
import ch.heig.amt.overflow.domain.vote.Vote;
import ch.heig.amt.overflow.domain.vote.VoteId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.*;

@ApplicationScoped
@Named("JdbcVoteRepository")
public class JdbcVoteRepository implements IVoteRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public void save(Vote entity) {
        // TODO implement

    }

    @Override
    public void remove(VoteId id) {
        // TODO implement

    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        // TODO implement
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserId(UserId userId) {
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserIdAndQuestionId(UserId userId, QuestionId questionId) {
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserIdAndContentId(UserId userId, ContentId contentId) {
        // TODO implement
        return null;
    }
}
