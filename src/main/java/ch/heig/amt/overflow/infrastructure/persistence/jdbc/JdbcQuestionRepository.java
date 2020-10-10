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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
            if(!query.getSearch().isEmpty()) {
                sql += " WHERE title = ?";
            }
            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            if(!query.getSearch().isEmpty()) {
                statement.setString(1, query.getSearch());
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                questions.add(Question.builder()
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
                        .build());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return questions;
    }

    @Override
    public void save(Question entity) {

    }

    @Override
    public void remove(QuestionId id) {

    }

    @Override
    public Optional<Question> findById(QuestionId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Question> findAll() {
        return find(QuestionQuery.builder().build());
    }

}
