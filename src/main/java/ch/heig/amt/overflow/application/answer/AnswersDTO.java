package ch.heig.amt.overflow.application.answer;

import ch.heig.amt.overflow.application.auth.UserDTO;
import ch.heig.amt.overflow.domain.answer.AnswerId;
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
public class AnswersDTO {

    @Builder
    @Value
    public static class AnswerDTO {
        AnswerId answerId;
        String content;
        Date createdAt;
        UserDTO author;

        public String formattedCreatedAt() {
            DateFormat format = new SimpleDateFormat("'Le 'dd.MM.yyyy' à 'HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("Europe/Zurich"));
            return format.format(createdAt);
        }

    }



    @Singular
    List<AnswerDTO> answers;

}
