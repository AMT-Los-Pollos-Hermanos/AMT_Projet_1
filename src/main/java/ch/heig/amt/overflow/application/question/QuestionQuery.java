package ch.heig.amt.overflow.application.question;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QuestionQuery {

    @Builder.Default
    String search = "";

}
