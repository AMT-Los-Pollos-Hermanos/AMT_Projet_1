package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.ContentId;
import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;
import ch.heig.amt.overflow.domain.vote.IVoteRepository;
import ch.heig.amt.overflow.domain.vote.Vote;
import ch.heig.amt.overflow.domain.vote.VoteId;
import ch.heig.amt.overflow.domain.vote.status.VoteDown;
import ch.heig.amt.overflow.domain.vote.status.VoteUp;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String sql = "SELECT id, content_id, user_id, state FROM votes WHERE id = ?";

        Collection<Vote> votes = resultToVotes(sql);
        if (votes.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(votes.iterator().next());
        }
    }

    @Override
    public Collection<Vote> findAll() {
        String sql = "SELECT id, content_id, user_id, state FROM votes";
        return resultToVotes(sql);
    }

    @Override
    public Collection<Vote> findByUserId(UserId userId) {
        String sql = "SELECT id, content_id, user_id, state FROM votes WHERE user_id = ?";
        return resultToVotes(sql);
    }

    @Override
    public Collection<Vote> findByUserIdAndQuestionId(UserId userId, QuestionId questionId) {
        String sql = "SELECT votes.id, votes.content_id, votes.user_id, votes.state FROM comments " +
                "INNER JOIN main_contents on comments.main_content_id = main_contents.content_id " +
                "INNER JOIN votes ON votes.content_id = comments.content_id " +
                "WHERE main_content_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT votes.id, votes.content_id, votes.user_id, votes.state FROM answers " +
                "INNER JOIN votes ON votes.content_id = answers.content_id " +
                "WHERE answers.question_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT votes.id, votes.content_id, votes.user_id, votes.state FROM questions " +
                "INNER JOIN votes ON votes.content_id = questions.content_id " +
                "WHERE questions.content_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301' " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c' " +
                "UNION " +
                "SELECT votes.id, votes.content_id, votes.user_id, votes.state FROM comments " +
                "INNER JOIN main_contents on comments.main_content_id = main_contents.content_id " +
                "INNER JOIN votes ON votes.content_id = comments.content_id " +
                "WHERE main_content_id IN (SELECT answers.content_id FROM answers " +
                "                          WHERE answers.question_id = '73dbc27f-d54f-417c-b576-07f1c3cfd301') " +
                "AND votes.user_id = '54ce8647-8742-4500-8b2a-ca7eb345da0c'";

        return resultToVotes(sql);
    }

    @Override
    public Optional<Vote> findByUserIdAndContentId(UserId userId, ContentId contentId) {
        String sql = "SELECT id, content_id, user_id, state FROM votes WHERE user_id = ? AND content = ?";

        Collection<Vote> votes = resultToVotes(sql);
        if (votes.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(votes.iterator().next());
        }
    }

    private Collection<Vote> resultToVotes(String query) {
        List<Vote> votes = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                votes.add(
                        Vote.builder()
                        .id(new VoteId(rs.getString("id")))
                        .contentId(new ContentId(rs.getString("content_id")))
                        .userId(new UserId(rs.getString("user_id")))
                        .status(rs.getString("state").equals("UP") ? new VoteUp() : new VoteDown())
                        .build()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return votes;
    }

}
