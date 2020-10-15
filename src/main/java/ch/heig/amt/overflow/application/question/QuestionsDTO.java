package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.answer.AnswersDTO;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.question.QuestionId;
import lombok.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Builder
@Value
public class QuestionsDTO {

    @Builder
    @Getter
    @RequiredArgsConstructor
    @EqualsAndHashCode
    public static class QuestionDTO {
        private QuestionId questionId;
        private String title;
        private String content;
        private Date createdAt;
        private UserDTO author;

        @Setter
        private AnswersDTO answersDTO;

        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }



    @Singular
    List<QuestionDTO> questions;

}
