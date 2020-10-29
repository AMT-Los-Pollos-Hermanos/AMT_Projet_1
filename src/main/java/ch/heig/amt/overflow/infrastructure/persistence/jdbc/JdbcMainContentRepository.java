package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.MainContentId;
import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@ApplicationScoped
public class JdbcMainContentRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    public Optional<Object> findById(MainContentId id) {
        try {
            String sql = "SELECT * FROM contents " +
                    "INNER JOIN main_contents ON main_contents.content_id = contents.id " +
                    "INNER JOIN users ON contents.user_id = users.id " +
                    "LEFT JOIN questions ON main_contents.content_id = questions.content_id " +
                    "LEFT JOIN answers ON main_contents.content_id = answers.content_id " +
                    "WHERE contents.id = ?";

            PreparedStatement statement = dataSource.getConnection().prepareStatement(sql);
            statement.setString(1, id.toString());
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                if (rs.getString("questions.content_id") != null) {
                    Question q = Question.builder()
                            .id(new QuestionId(rs.getString("questions.content_id")))
                            .author(User.builder()
                                    .firstName(rs.getString("first_name"))
                                    .lastName(rs.getString("last_name"))
                                    .build())
                            .content(rs.getString("content"))
                            .title(rs.getString("title"))
                            .build();
                    return Optional.of(q);
                } else {
                    Answer a = Answer.builder()
                            .id(new AnswerId(rs.getString("answers.content_id")))
                            .author(User.builder()
                                    .firstName(rs.getString("first_name"))
                                    .lastName(rs.getString("last_name"))
                                    .build())
                            .content(rs.getString("content"))
                            .questionId(new QuestionId(rs.getString("question_id")))
                            .build();
                    return Optional.of(a);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Problème lié à la base de données");
        }
        return Optional.empty();
    }

}
