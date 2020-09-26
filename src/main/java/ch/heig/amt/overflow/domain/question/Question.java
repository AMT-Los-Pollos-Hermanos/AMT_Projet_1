package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.domain.IEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity {

    @Builder.Default
    private QuestionId id = new QuestionId();

    private String title;

    private String content;

    private String author;

}
