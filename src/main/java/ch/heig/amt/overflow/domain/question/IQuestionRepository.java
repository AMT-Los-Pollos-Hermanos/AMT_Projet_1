package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.IRepository;

import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    Collection<Question> find(QuestionQuery query);
}
