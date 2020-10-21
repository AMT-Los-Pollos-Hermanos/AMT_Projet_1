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
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@ApplicationScoped
@Named("JdbcQuestionRepository")
public class JdbcQuestionRepository implements IQuestionRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public Collection<Question> find(QuestionQuery query) {
        List<Question> questions = new ArrayList<>();

        try {
            String sql = "SELECT questions.content_id, title, content, created_at, updated_at, " +
                    "users.id, username, email, password, last_name, first_name, " +
                    "(COUNT(IF(state = 'UP', 1, NULL)) - COUNT(IF(state = 'DOWN', 1, NULL))) AS nb_votes " +
                    "FROM questions " +
                    "INNER JOIN main_contents on questions.content_id = main_contents.content_id " +
                    "INNER JOIN contents on main_contents.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "LEFT JOIN votes ON contents.id = votes.content_id";
            if (!query.getSearch().isEmpty()) {
                sql += " WHERE LOWER(title) LIKE ? OR LOWER(first_name) LIKE ?";
            }
            sql += " GROUP BY questions.content_id";
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
            e.printStackTrace(); // TODO handle SQL exception
        }
        return questions;
    }

    @Override
    public void save(Question entity) {
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement;

            preparedStatement = con.prepareStatement("SELECT COUNT(*) FROM questions WHERE content_id = ?");
            preparedStatement.setString(1, entity.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }

            con.setAutoCommit(false);
            if (size == 0) {
                // Create question
                preparedStatement = con.prepareStatement("INSERT INTO contents (id, user_id, content) VALUES (?, ?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getContent());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("INSERT INTO main_contents (content_id) VALUES (?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("INSERT INTO questions (content_id, title) VALUES (?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getTitle());
                preparedStatement.executeUpdate();
            } else {
                // Update question
                preparedStatement = con.prepareStatement("UPDATE contents SET content = ?, user_id = ? WHERE contents.id = ?;");
                preparedStatement.setString(1, entity.getContent());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getId().toString());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("UPDATE questions SET title = ? WHERE questions.content_id = ?;");
                preparedStatement.setString(1, entity.getTitle());
                preparedStatement.setString(2, entity.getId().toString());
                preparedStatement.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Error while adding/updating question to the database");
        }
    }

    @Override
    public void remove(QuestionId id) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM contents WHERE id = ?");
            preparedStatement.setString(1, id.toString());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No question deleted, question with id '" + id.toString() + "' not found in database");
            }
        } catch (SQLException e) {
            throw new RuntimeException("SQL error");
        }
    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        Question question = null;

        try {
            String sql = "SELECT questions.content_id, title, content, created_at, updated_at, " +
                    "users.id, username, email, password, last_name, first_name, " +
                    "(COUNT(IF(state = 'UP', 1, NULL)) - COUNT(IF(state = 'DOWN', 1, NULL))) AS nb_votes " +
                    "FROM questions " +
                    "INNER JOIN main_contents on questions.content_id = main_contents.content_id " +
                    "INNER JOIN contents on main_contents.content_id = contents.id " +
                    "INNER JOIN users on contents.user_id = users.id " +
                    "LEFT JOIN votes ON contents.id = votes.content_id " +
                    "WHERE questions.content_id = ? " +
                    "GROUP BY questions.content_id";
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                question = resultToQuestion(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // TODO handle SQL exception
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
                    .id(new QuestionId(rs.getString("questions.content_id")))
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
                    .nbVotes(rs.getInt("nb_votes"))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace(); // TODO handle SQL exception
        }
        return null;
    }

    @Override
    public Collection<Question> findAll() {
        return find(QuestionQuery.builder().build());
    }

}
