package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.answer.AnswersDTO;
import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.application.comment.CommentsDTO;
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
    @EqualsAndHashCode
    public static class QuestionDTO {
        private final QuestionId questionId;
        private final String title;
        private final String content;
        private final Date createdAt;
        private final UserDTO author;
        private final int nbVotes;

        @Setter
        private AnswersDTO answersDTO;

        @Setter
        private CommentsDTO commentsDTO;

        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }



    @Singular
    List<QuestionDTO> questions;

}
