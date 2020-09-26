package ch.heig.amt.overflow.application.question;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionQuery {

    @Builder.Default
    private String search = "";

}
