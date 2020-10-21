package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Builder
@Value
public class NewAnswerCommand {
    UserId authorId;

    QuestionId questionId;

    @Builder.Default
    String content = "No content";
}
