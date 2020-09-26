package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.domain.IEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    @Builder.Default
    private QuestionId id = new QuestionId();

    private String title;

    private String content;

    private String author;

    @Override
    public Question deepClone() {
        return this.toBuilder().id(new QuestionId(id.toString())).build();
    }
}
