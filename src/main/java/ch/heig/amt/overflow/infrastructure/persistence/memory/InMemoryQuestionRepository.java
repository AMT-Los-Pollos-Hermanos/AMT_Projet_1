/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.infrastructure.persistence.memory;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.question.IQuestionRepository;
import ch.heig.amt.overflow.domain.question.Question;
import ch.heig.amt.overflow.domain.question.QuestionId;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import java.util.Collection;
import java.util.stream.Collectors;

@ApplicationScoped
@Named("InMemoryQuestionRepository")
@Deprecated
public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {

    public Collection<Question> find(QuestionQuery query) {
        if (query != null && !query.getSearch().isEmpty()) {
            return findAll().stream().filter(question -> question.getTitle().contains(query.getSearch())).collect(Collectors.toList());
        } else {
            return findAll();
        }
    }

}
