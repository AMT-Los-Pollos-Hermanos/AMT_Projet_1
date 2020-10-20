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
        String sql = "INSERT INTO votes (id, content_id, user_id, state) VALUES (?, ?, ?, ?)";
        // TODO implement

    }

    @Override
    public void remove(VoteId id) {
        String sql = "DELETE FROM votes WHERE content_id = ? AND user_id = ?";
        // TODO implement

    }

    @Override
    public Optional<Vote> findById(VoteId id) {
        String sql = "SELECT * FROM votes WHERE id = ?";
        // TODO implement
        return Optional.empty();
    }

    @Override
    public Collection<Vote> findAll() {
        String sql = "SELECT * FROM votes";
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserId(UserId userId) {
        String sql = "SELECT * FROM votes WHERE user_id = ?";
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserIdAndQuestionId(UserId userId, QuestionId questionId) {
        String sql = "SELECT main_content_id AS qid, comments.content_id as cid, votes.state FROM comments " +
                "INNER JOIN main_contents on comments.main_content_id = main_contents.content_id " +
                "INNER JOIN votes ON votes.content_id = comments.content_id " +
                "WHERE main_content_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT answers.question_id AS qid, answers.content_id as cid, votes.state FROM answers " +
                "INNER JOIN votes ON votes.content_id = answers.content_id " +
                "WHERE answers.question_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT questions.content_id AS qid, questions.content_id as cid, votes.state FROM questions " +
                "INNER JOIN votes ON votes.content_id = questions.content_id " +
                "WHERE questions.content_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT main_content_id AS qid, comments.content_id as cid, votes.state FROM comments " +
                "INNER JOIN main_contents on comments.main_content_id = main_contents.content_id " +
                "INNER JOIN votes ON votes.content_id = comments.content_id " +
                "WHERE main_content_id IN (SELECT answers.content_id FROM answers " +
                "                          WHERE answers.question_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301') " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c'";
        // TODO implement
        return null;
    }

    @Override
    public Collection<Vote> findByUserIdAndContentId(UserId userId, ContentId contentId) {
        String sql = "SELECT * FROM votes WHERE user_id = ? AND content = ?";
        // TODO implement
        return new ArrayList<>();
    }
}
