package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.domain.user.UserId;
import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewQuestionCommand {

    @Builder.Default
    String title = "No title";

    UserId authorId;

    @Builder.Default
    String content = "No content";

}
