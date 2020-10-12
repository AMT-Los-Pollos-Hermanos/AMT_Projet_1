package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
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
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public Collection<Question> find(QuestionQuery query) {
        List<Question> questions = new ArrayList<>();

        try {
            String sql = "SELECT * FROM questions INNER JOIN users ON questions.user_id = users.id";
            if (!query.getSearch().isEmpty()) {
                sql += " WHERE LOWER(title) LIKE ? OR LOWER(first_name) LIKE ?";
            }
            sql += " ORDER BY created_at DESC";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            if (!query.getSearch().isEmpty()) {
                statement.setString(1, "%" + query.getSearch() + "%");
                statement.setString(2, "%" + query.getSearch() + "%");
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                questions.add(resultToQuestion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questions;
    }

    @Override
    public void save(Question entity) {
        try {
            PreparedStatement select = dataSource.getConnection().prepareStatement("SELECT COUNT(*) FROM questions WHERE id = ?");
            select.setString(1, entity.getId().toString());
            ResultSet rs = select.executeQuery();
            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }
            if (size == 0) {
                // Create question
                PreparedStatement create = dataSource
                        .getConnection()
                        .prepareStatement("INSERT INTO questions (id, title, content, user_id) VALUES (?, ?, ?, ?)");
                int i = 1;
                create.setString(i++, entity.getId().toString());
                create.setString(i++, entity.getTitle());
                create.setString(i++, entity.getContent());
                create.setString(i, entity.getAuthor().getId().toString());
                int rows = create.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Error while adding new question to the database");
                }
            } else {
                // Update user
                PreparedStatement create = dataSource
                        .getConnection()
                        .prepareStatement("UPDATE questions SET title = ?, content = ?, user_id = ? WHERE id = ?");
                int i = 1;
                create.setString(i++, entity.getTitle());
                create.setString(i++, entity.getContent());
                create.setString(i++, entity.getAuthor().getId().toString());
                create.setString(i, entity.getId().toString());
                int rows = create.executeUpdate();
                if (rows == 0) {
                    throw new RuntimeException("Error while updating user in the database");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(QuestionId id) {
        try {
            PreparedStatement select = dataSource.getConnection().prepareStatement("DELETE FROM questions WHERE id = ?");
            select.setString(1, id.toString());
            int rows = select.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No question deleted, question with id '" + id.toString() + "' not found in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        Question question = null;

        try {
            String sql = "SELECT * FROM questions INNER JOIN users ON questions.user_id = users.id WHERE questions.id = ?";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                question = resultToQuestion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (question != null) {
            return Optional.of(question);
        } else {
            return Optional.empty();
        }
    }

    private Question resultToQuestion(ResultSet rs) throws SQLException {
        Date updateAt = null;
        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            if (rs.getString("updated_at") != null) {
                updateAt = utcFormat.parse(rs.getString("updated_at"));
            }
            return Question.builder()
                    .id(new QuestionId(rs.getString("questions.id")))
                    .author(User.builder()
                            .id(new UserId(rs.getString("users.id")))
                            .username(rs.getString("username"))
                            .email(rs.getString("email"))
                            .encryptedPassword(rs.getString("password"))
                            .lastName(rs.getString("last_name"))
                            .firstName(rs.getString("first_name"))
                            .build())
                    .title(rs.getString("title"))
                    .content(rs.getString("content"))
                    .createdAt(utcFormat.parse(rs.getString("created_at")))
                    .updatedAt(updateAt)
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Collection<Question> findAll() {
        return find(QuestionQuery.builder().build());
    }

}
