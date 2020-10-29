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
import java.sql.Connection;
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
            Connection con = dataSource.getConnection();
            PreparedStatement preparedStatement;

            preparedStatement = con.prepareStatement("SELECT COUNT(*) FROM answers WHERE content_id = ?");
            preparedStatement.setString(1, entity.getId().toString());
            ResultSet rs = preparedStatement.executeQuery();

            int size = 0;
            if (rs.next()) {
                size = rs.getInt(1);
            }

            con.setAutoCommit(false);
            if (size == 0) {
                // Create answer
                preparedStatement = con.prepareStatement("INSERT INTO contents (id, user_id, content) VALUES (?, ?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getContent());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("INSERT INTO main_contents (content_id) VALUES (?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("INSERT INTO answers (content_id, question_id) VALUES (?, ?);");
                preparedStatement.setString(1, entity.getId().toString());
                preparedStatement.setString(2, entity.getQuestionId().toString());
                preparedStatement.executeUpdate();
            } else {
                // Update answer
                preparedStatement = con.prepareStatement("UPDATE contents SET content = ?, user_id = ? WHERE contents.id = ?;");
                preparedStatement.setString(1, entity.getContent());
                preparedStatement.setString(2, entity.getAuthor().getId().toString());
                preparedStatement.setString(3, entity.getId().toString());
                preparedStatement.executeUpdate();

                preparedStatement = con.prepareStatement("UPDATE answers SET question_id = ? WHERE answers.content_id = ?;");
                preparedStatement.setString(1, entity.getQuestionId().toString());
                preparedStatement.setString(2, entity.getId().toString());
                preparedStatement.executeUpdate();
            }
            con.commit();

        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de l'ajout/mise à jour de la réponse dans la base de données");
        }
    }

    @Override
    public void remove(AnswerId id) {
        try {
            PreparedStatement preparedStatement = dataSource.getConnection().prepareStatement("DELETE FROM contents WHERE id = ?");
            preparedStatement.setString(1, id.toString());
            int rows = preparedStatement.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("Aucune réponse supprimée, la réponse avec l'ID '" + id.toString() + "' n'a pas été trouvée");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Problème lié à la base de données");
        }
    }

    @Override
    public Collection<Answer> findByQuestionId(QuestionId questionId) {
        List<Answer> answers = new ArrayList<>();

        try {
            String sql = getQuery("WHERE question_id = ?");

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, questionId.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answers.add(resulToAnswer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problème lié à la base de données");
        }
        return answers;
    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        Answer answer = null;

        try {
            String sql = getQuery("WHERE answers.content_id = ?");

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answer = resulToAnswer(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problème lié à la base de données");
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
            String sql = getQuery("");

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                answers.add(resulToAnswer(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problème lié à la base de données");
        }
        return answers;
    }

    private Answer resulToAnswer(ResultSet rs) {
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
                    .nbVotes(rs.getInt("nb_votes"))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Problème lié à la base de données");
        }
    }

    private String getQuery(String condition) {
        return "SELECT answers.content_id, content, created_at, updated_at, " +
                "users.id, username, email, password, last_name, first_name, " +
                "(COUNT(IF(state = 'UP', 1, NULL)) - COUNT(IF(state = 'DOWN', 1, NULL))) AS nb_votes " +
                "FROM answers " +
                "INNER JOIN main_contents on answers.content_id = main_contents.content_id " +
                "INNER JOIN contents on main_contents.content_id = contents.id " +
                "INNER JOIN users on contents.user_id = users.id " +
                "LEFT JOIN votes ON contents.id = votes.content_id " +
                condition + " " +
                "GROUP BY answers.content_id";
    }
}
