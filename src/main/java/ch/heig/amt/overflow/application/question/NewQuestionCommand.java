package ch.heig.amt.overflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class NewQuestionCommand {

    @Builder.Default
    private String title = "No title";

    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String content = "No content";

}
