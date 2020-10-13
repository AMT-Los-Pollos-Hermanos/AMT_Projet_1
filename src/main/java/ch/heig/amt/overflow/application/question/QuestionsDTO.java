package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.answer.AnswersDTO;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.question.QuestionId;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Builder
@Value
public class QuestionsDTO {

    @Builder
    @Value
    public static class QuestionDTO {
        QuestionId questionId;
        String title;
        String content;
        Date createdAt;
        UserDTO author;
        AnswersDTO answersDTO;

        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }



    @Singular
    List<QuestionDTO> questions;

}
