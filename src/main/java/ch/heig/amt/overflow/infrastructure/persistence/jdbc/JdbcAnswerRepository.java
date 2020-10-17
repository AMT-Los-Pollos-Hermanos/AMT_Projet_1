package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;
import ch.heig.amt.overflow.domain.user.UserId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public void save(Answer entity) {
        try {
            // Check if the id is already in the DB
            PreparedStatement select = dataSource.getConnection().prepareStatement("SELECT COUNT(*) FROM answers WHERE id = ?");
            select.setString(1, entity.getId().toString());
            ResultSet rs = select.executeQuery();
            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }

            if (size == 0) {
                // Create answer
                PreparedStatement create = dataSource
                        .getConnection()
                        .prepareStatement("INSERT INTO answers (id, title, content, user_id, question_id) VALUES (?, ?, ?, ?)");
                int i = 1;
                create.setString(i++, entity.getId().toString());
                create.setString(i++, entity.getTitle());
                create.setString(i++, entity.getContent());
                create.setString(i++, entity.getAuthor().getId().toString());
                create.setString(i, entity.getQuestionId().toString());
                int rows = create.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Error while adding new answer to the database");
                }
            } else {
                // Update answer
                PreparedStatement create = dataSource
                        .getConnection()
                        .prepareStatement("UPDATE answers SET title = ?, content = ?, user_id = ?, question_id = ? WHERE id = ?");
                int i = 1;
                create.setString(i++, entity.getTitle());
                create.setString(i++, entity.getContent());
                create.setString(i++, entity.getAuthor().getId().toString());
                create.setString(i++, entity.getId().toString());
                create.setString(i, entity.getQuestionId().toString());
                int rows = create.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Error while updating answer in the database");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(AnswerId id) {
        try {
            PreparedStatement select = dataSource.getConnection().prepareStatement("DELETE FROM contents WHERE id = ?");
            select.setString(1, id.toString());
            int rows = select.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No answer deleted, answer with id '" + id.toString() + "' not found in database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error");
        }
    }

    @Override
    public Collection<Answer> findByQuestionId(QuestionId questionId) {
        List<Answer> answers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM answers " +
                    "INNER JOIN main_contents on answers.content_id = main_contents.content_id " +
                    "INNER JOIN contents on main_contents.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "WHERE question_id = ?";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, questionId.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answers.add(resulToAnswer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return answers;
    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        Answer answer = null;

        try {
            String sql = "SELECT * FROM answers " +
                    "INNER JOIN main_contents on answers.content_id = main_contents.content_id " +
                    "INNER JOIN contents on main_contents.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "WHERE answers.content_id = ?";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answer = resulToAnswer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }

        if (answer != null) {
            return Optional.of(answer);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Collection<Answer> findAll() {
        List<Answer> answers = new ArrayList<>();

        try {
            String sql = "SELECT * FROM answers " +
                    "INNER JOIN main_contents on answers.content_id = main_contents.content_id " +
                    "INNER JOIN contents on main_contents.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answers.add(resulToAnswer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return answers;
    }

    private Answer resulToAnswer(ResultSet rs) throws SQLException {
        Date updateAt = null;
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            if (rs.getString("updated_at") != null) {
                updateAt = utcFormat.parse(rs.getString("updated_at"));
            }
            return Answer.builder()
                    .id(new AnswerId(rs.getString("answers.content_id")))
                    .author(User.builder()
                            .id(new UserId(rs.getString("users.id")))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .encryptedPassword(rs.getString("password"))
                            .lastName(rs.getString("last_name"))
                            .firstName(rs.getString("first_name"))
                            .build())
                    .content(rs.getString("content"))
                    .createdAt(utcFormat.parse(rs.getString("created_at")))
                    .updatedAt(updateAt)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return null;
    }
}
