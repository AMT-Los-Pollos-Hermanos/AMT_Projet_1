package ch.heig.amt.overflow.application.question;

import lombok.*;

import java.util.List;

@Builder
@Value
public class QuestionsDTO {

    @Builder
    @Value
    public static class QuestionDTO {
        String title;
        String content;
    }

    @Singular
    List<QuestionDTO> questions;

}
