package ch.heig.amt.overflow.application.question;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class NewQuestionCommand {

    @Builder.Default
    String title = "No title";

    @Builder.Default
    String author = "Anonymous";

    @Builder.Default
    String content = "No content";

}
