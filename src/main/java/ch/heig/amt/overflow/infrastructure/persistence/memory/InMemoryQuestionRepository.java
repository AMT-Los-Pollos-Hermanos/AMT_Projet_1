package ch.heig.amt.overflow.infrastructure.persistence.memory;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;

import java.util.Collection;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {

    public Collection<Question> find(QuestionQuery query) {
        if (query != null && !query.getSearch().isEmpty()) {
            return findAll().stream().filter(question -> question.getTitle().contains(query.getSearch())).collect(Collectors.toList());
        } else {
            return findAll();
        }
    }

}
