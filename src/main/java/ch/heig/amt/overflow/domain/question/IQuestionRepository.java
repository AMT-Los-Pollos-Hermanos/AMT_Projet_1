/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.application.question.QuestionQuery;
import ch.heig.amt.overflow.domain.IRepository;

import java.util.Collection;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {

    Collection<Question> find(QuestionQuery query);

}
