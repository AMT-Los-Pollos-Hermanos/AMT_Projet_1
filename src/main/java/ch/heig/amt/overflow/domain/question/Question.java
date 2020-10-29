/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Builder.Default
    private QuestionId id = new QuestionId();

    private String title;

    private String content;

    private User author;

    private Date createdAt;

    private Date updatedAt;

    @Builder.Default
    private int nbVotes = 0;

    @Override
    public Question deepClone() {
        return this.toBuilder().id(new QuestionId(id.toString())).build();
    }

}
