package ch.heig.amt.overflow.infrastructure.persistence.memory;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository implements IQuestionRepository {

    private final Map<QuestionId, Question> store = new ConcurrentHashMap<>();

    @Override
    public Collection<Question> find(QuestionQuery query) {
        if(query != null && !query.getSearch().equals("")) {
            return findAll().stream().filter(question -> question.getTitle().contains(query.getSearch())).collect(Collectors.toList());
        } else {
            return findAll();
        }
    }

    @Override
    public void save(Question question) {
        store.put(question.getId(), question);
    }

    @Override
    public void remove(QuestionId questionId) {
        store.remove(questionId);
    }

    @Override
    public Optional<Question> findById(QuestionId questionId) {
        Question existingQuestion = store.get(questionId);
        if (existingQuestion == null) {
            return Optional.empty();
        } else {
            return Optional.of(existingQuestion.toBuilder().build());
        }
    }

    @Override
    public Collection<Question> findAll() {
        return store.values().stream().map(question -> question.toBuilder().build()).collect(Collectors.toList());
    }
}
