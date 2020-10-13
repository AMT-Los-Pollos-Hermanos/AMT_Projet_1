package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.domain.question.QuestionId;
import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewAnswerCommand {
    @Builder.Default
    String title = "No title";

    UserId authorId;

    QuestionId questionId;

    @Builder.Default
    String content = "No content";
}
