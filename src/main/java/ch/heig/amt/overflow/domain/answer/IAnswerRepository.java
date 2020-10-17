package ch.heig.amt.overflow.domain.answer;

import ch.heig.amt.overflow.domain.IRepository;
import ch.heig.amt.overflow.domain.question.QuestionId;

import java.util.Collection;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    Collection<Answer> findByQuestionId(QuestionId questionId);
}
