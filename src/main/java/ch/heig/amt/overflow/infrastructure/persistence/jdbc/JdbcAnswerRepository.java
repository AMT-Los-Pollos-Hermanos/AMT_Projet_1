package ch.heig.amt.overflow.infrastructure.persistence.jdbc;

import ch.heig.amt.overflow.domain.answer.Answer;
import ch.heig.amt.overflow.domain.answer.AnswerId;
import ch.heig.amt.overflow.domain.answer.IAnswerRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Named("JdbcAnswerRepository")
public class JdbcAnswerRepository implements IAnswerRepository {

    @Resource(lookup = "jdbc/OverflowDS")
    private DataSource dataSource;

    @Override
    public Collection<Answer> findByQuestionId(QuestionId questionId) {
        return null;
    }

    @Override
    public void save(Answer entity) {
        // TODO
    }

    @Override
    public void remove(AnswerId id) {
        // TODO
    }

    @Override
    public Optional<Answer> findById(AnswerId id) {
        // TODO
        return Optional.empty();
    }

    @Override
    public Collection<Answer> findAll() {
        // TODO
        return null;
    }
}
