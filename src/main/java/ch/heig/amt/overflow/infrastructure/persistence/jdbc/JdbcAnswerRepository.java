package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public Collection<Answer> findByQuestionId(QuestionId questionId) {
        String sql = "SELECT * FROM answers INNER JOIN users ON answers.user_id = users.id WHERE answers.question_id = ?";
        // TODO
        return null;
    }

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
            PreparedStatement select = dataSource.getConnection().prepareStatement("DELETE FROM answers WHERE id = ?");
            select.setString(1, id.toString());
            int rows = select.executeUpdate();
            if (rows == 0) {
                throw new RuntimeException("No answer deleted, answer with id '" + id.toString() + "' not found in database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        String sql = "SELECT * FROM answers INNER JOIN users ON answers.user_id = users.id WHERE answers.id = ?";
        // TODO
        return Optional.empty();
    }

    @Override
    public Collection<Answer> findAll() {
        String sql = "SELECT * FROM answers INNER JOIN users ON answers.user_id = users.id";
        // TODO
        return null;
    }
}
