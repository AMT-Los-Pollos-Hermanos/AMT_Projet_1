package ch.heig.amt.overflow.domain.answer;

import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


@Data
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {

    @Builder.Default
    private AnswerId id = new AnswerId();

    private String content;

    private User author;

    private QuestionId questionId;

    private Date createdAt;

    private Date updatedAt;

    @Builder.Default
    private int nbVotes = 0;

    @Override
    public Answer deepClone() {
        return this.toBuilder().id(new AnswerId(id.toString())).build();
    }
}
